package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;

import java.time.LocalDateTime;
import java.util.*;


public class Controller implements Initializable {
    //nastaveni ktere vyrobky
    @FXML
    public CheckBox check1;
    @FXML
    public CheckBox check2;
    @FXML
    public CheckBox check3;
    @FXML
    public CheckBox check4;
    @FXML
    public CheckBox check5;

    @FXML
    public Button btnSTART;

    @FXML
    public ProgressBar progress;

    @FXML
    public TextField blv1;
    @FXML
    public TextField blv2;
    @FXML
    public TextField blv3;

    @FXML
    public TextArea logmain;

    public static ObservableList<String> prubehList = FXCollections.observableArrayList();

    @FXML
    public TextField tdv1;
    @FXML
    public TextField ipv1;
    @FXML
    public TextField tdv2;
    @FXML
    public TextField tdv3;
    @FXML
    public TextField tdv4;
    @FXML
    public TextField tdv5;
    @FXML
    public TextField ip2;
    @FXML
    public TextField ipv3;
    @FXML
    public TextField ipv4;
    @FXML
    public TextField ipv5;
    @FXML
    public TextField dv1;
    @FXML
    public TextField dv2;
    @FXML
    public TextField dv3;
    @FXML
    public TextField dv4;
    @FXML
    public TextField dv5;
    @FXML
    public TextField blv4;
    @FXML
    public TextField blv5;
    @FXML
    public Accordion accordPane;
    @FXML
    public TitledPane firstAccordPane;

    public String getPrubehList() {
        StringBuilder ss = new StringBuilder();
        prubehList.forEach(hovno -> ss.append(hovno).append("\n"));
        return ss.toString();
    }

    public static synchronized void addPrubehList(String s) {
        prubehList.add(0,LocalDateTime.now().toLocalDate() + " - " + LocalDateTime.now().toLocalTime() + " -> " + s);
    }

    //vyrobky instance
    Vyrobky v1, v2, v3, v4, v5;

    //pomocne pocitadlo
    private long pocitejMne = 0;

    //co se stane pred spustenim
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accordPane.setExpandedPane(firstAccordPane);
        v1 = new Vyrobky("Vyrobek1",
                Utils.getSchwiftyBejbe(30000, 35000),
                13000,
                Useky.BACKLOG,
                Arrays.asList(Instance.s_1, Instance.s_2, Instance.s_3));
        v2 = new Vyrobky("Vyrobek2",
                Utils.getSchwiftyBejbe(20000, 25000),
                14000,
                Useky.BACKLOG,
                Arrays.asList(Instance.s_1, Instance.s_4, Instance.s_5));
        v3 = new Vyrobky("Vyrobek3",
                Utils.getSchwiftyBejbe(45000, 50000),
                2000,
                Useky.BACKLOG,
                Arrays.asList(Instance.s_2, Instance.s_4, Instance.s_6));
        v4 = new Vyrobky("Vyrobek4",
                Utils.getSchwiftyBejbe(23000, 30000),
                15000,
                Useky.BACKLOG,
                Arrays.asList(Instance.s_3, Instance.s_5, Instance.s_6));
        v5 = new Vyrobky("Vyrobek5",
                Utils.getSchwiftyBejbe(36000, 41000),
                12000,
                Useky.BACKLOG,
                Arrays.asList(Instance.s_1, Instance.s_6, Instance.s_7));

        check1.setText(v1.textForPane());
        check2.setText(v2.textForPane());
        check3.setText(v3.textForPane());
        check4.setText(v4.textForPane());
        check5.setText(v5.textForPane());

         prubehList.addListener((ListChangeListener<String>)change ->{
             while (change.next()){
                 if(change.wasAdded()){
                     logmain.setText(getPrubehList());

                     pomocnicek(v1, tdv1, blv1, ipv1, dv1);
                     pomocnicek(v2, tdv2, blv2, ip2, dv2);
                     pomocnicek(v3, tdv3, blv3, ipv3, dv3);
                     pomocnicek(v4, tdv4, blv4, ipv4, dv4);
                     pomocnicek(v5, tdv5, blv5, ipv5, dv5);

                     long ciselko = prubehList.stream()
                             .filter(ss -> ss.contains("###############"))
                             .count();
                     if(pocitejMne == ciselko) {
                         Platform.runLater(() -> {
                             progress.setOpacity(0);
                             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                             alert.setHeaderText("Výroba dokončena.");
                             alert.show();
                         });
                     }

                 }
             }
         });

    }

    /**
     * btn kterym se zapne cely proces prvni checkne ktere vyrobky se maji vyrabet,
     * zalozi jejich instance a zavola metodu run
     * @param actionEvent
     */
    public void makeSomeNoise(ActionEvent actionEvent) {
        if (!check1.isSelected()
                && !check2.isSelected()
                && !check3.isSelected()
                && !check4.isSelected()
                && !check5.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Musíš vybrat alespoň jeden výrobek");
            alert.show();
        } else {
            progress.setOpacity(1);
            if (check1.isSelected()) {
                pocitejMne += 1;
                v1.start();
            }
            if (check2.isSelected()) {
                pocitejMne += 1;
                v2.start();
            }
            if (check3.isSelected()) {
                pocitejMne += 1;
                v3.start();
            }
            if (check4.isSelected()) {
                pocitejMne += 1;
                v4.start();
            }
            if (check5.isSelected()) {
                pocitejMne += 1;
                v5.start();
            }
            btnSTART.setDisable(true);
        }
    }

    public void pomocnicek(Vyrobky vvv, TextField jedna, TextField dva, TextField tri, TextField ctyri) {
        switch (vvv.getMomentalniUsek()){
            case TO_DO:
                jedna.setOpacity(1);
                dva.setOpacity(0);
                tri.setOpacity(0);
                ctyri.setOpacity(0);
                break;
            case IN_PROGRESS:
                jedna.setOpacity(0);
                dva.setOpacity(0);
                tri.setOpacity(1);
                ctyri.setOpacity(0);
                break;
            case DONE:
                jedna.setOpacity(0);
                dva.setOpacity(0);
                tri.setOpacity(0);
                ctyri.setOpacity(1);
                break;
        }
    }
}

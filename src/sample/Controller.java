package sample;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;

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

    public String getPrubehList() {
        StringBuilder ss = new StringBuilder();
        prubehList.forEach(hovno -> ss.append(hovno).append("\n"));
        return ss.toString();
    }

    public static synchronized void addPrubehList(String s) {
        prubehList.add(s);
        System.out.println(s);
    }

    //vyrobky instance
    Vyrobky v1, v2, v3, v4, v5;

    //co se stane pred spustenim
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                     switch (v1.getMomentalniUsek()){
                         case TO_DO:
                             tdv1.setOpacity(1);
                             blv1.setOpacity(0);
                             ipv1.setOpacity(0);
                             dv1.setOpacity(0);
                             break;
                         case IN_PROGRESS:
                             tdv1.setOpacity(0);
                             blv1.setOpacity(0);
                             ipv1.setOpacity(1);
                             dv1.setOpacity(0);
                             break;
                         case DONE:
                             tdv1.setOpacity(0);
                             blv1.setOpacity(0);
                             ipv1.setOpacity(0);
                             dv1.setOpacity(1);
                             break;
                     }
                     switch (v2.getMomentalniUsek()){
                         case TO_DO:
                             tdv2.setOpacity(1);
                             blv2.setOpacity(0);
                             ip2.setOpacity(0);
                             dv2.setOpacity(0);
                             break;
                         case IN_PROGRESS:
                             tdv2.setOpacity(0);
                             blv2.setOpacity(0);
                             ip2.setOpacity(1);
                             dv2.setOpacity(0);
                             break;
                         case DONE:
                             tdv2.setOpacity(0);
                             blv2.setOpacity(0);
                             ip2.setOpacity(0);
                             dv2.setOpacity(1);
                             break;
                     }
                     switch (v3.getMomentalniUsek()){
                         case TO_DO:
                             tdv3.setOpacity(1);
                             blv3.setOpacity(0);
                             ipv3.setOpacity(0);
                             dv3.setOpacity(0);
                             break;
                         case IN_PROGRESS:
                             tdv3.setOpacity(0);
                             blv3.setOpacity(0);
                             ipv3.setOpacity(1);
                             dv3.setOpacity(0);
                             break;
                         case DONE:
                             tdv3.setOpacity(0);
                             blv3.setOpacity(0);
                             ipv3.setOpacity(0);
                             dv3.setOpacity(1);
                             break;
                     }
                     switch (v4.getMomentalniUsek()){
                         case TO_DO:
                             tdv4.setOpacity(1);
                             blv4.setOpacity(0);
                             ipv4.setOpacity(0);
                             dv4.setOpacity(0);
                             break;
                         case IN_PROGRESS:
                             tdv4.setOpacity(0);
                             blv4.setOpacity(0);
                             ipv4.setOpacity(1);
                             dv4.setOpacity(0);
                             break;
                         case DONE:
                             tdv4.setOpacity(0);
                             blv4.setOpacity(0);
                             ipv4.setOpacity(0);
                             dv4.setOpacity(1);
                             break;
                     }
                     switch (v5.getMomentalniUsek()){
                         case TO_DO:
                             tdv5.setOpacity(1);
                             blv5.setOpacity(0);
                             ipv5.setOpacity(0);
                             dv5.setOpacity(0);
                             break;
                         case IN_PROGRESS:
                             tdv5.setOpacity(0);
                             blv5.setOpacity(0);
                             ipv5.setOpacity(1);
                             dv5.setOpacity(0);
                             break;
                         case DONE:
                             tdv5.setOpacity(0);
                             blv5.setOpacity(0);
                             ipv5.setOpacity(0);
                             dv5.setOpacity(1);
                             break;
                     }

                     if((v1.getMomentalniUsek() == Useky.DONE || v1.getMomentalniUsek() == Useky.BACKLOG) &&
                             (v2.getMomentalniUsek() == Useky.DONE || v2.getMomentalniUsek() == Useky.BACKLOG) &&
                     (v3.getMomentalniUsek() == Useky.DONE || v3.getMomentalniUsek() == Useky.BACKLOG) &&
                     (v4.getMomentalniUsek() == Useky.DONE || v4.getMomentalniUsek() == Useky.BACKLOG) &&
                     (v5.getMomentalniUsek() == Useky.DONE || v5.getMomentalniUsek() == Useky.BACKLOG)){
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                         alert.setHeaderText("Výroba dokončena");
                         progress.setOpacity(0);
                         alert.show();
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
        if (check1.isSelected() == false && check2.isSelected() == false
            && check3.isSelected() == false
            && check4.isSelected() == false
            && check5.isSelected() == false){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Musíš vybrat alespoň jeden výrobek");
            alert.show();
        } else {
            progress.setOpacity(1);
            if (check1.isSelected()) {
                v1.start();
            }
            if (check2.isSelected()) {
                v2.start();
            }
            if (check3.isSelected()) {
                v3.start();
            }
            if (check4.isSelected()) {
                v4.start();
            }
            if (check5.isSelected()) {
                v5.start();
            }
            btnSTART.setDisable(true);
        }
    }
}

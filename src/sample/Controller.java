package sample;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import javafx.concurrent.*;

import javax.swing.*;
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


    public TextField blv1;
    public TextField blv2;
    public TextField blv3;

    @FXML
    public TextArea logmain;

    private volatile Service<String> backgroundThread;

    public static ObservableList<String> prubehList = FXCollections.observableArrayList();

    public String getPrubehList() {
        StringBuilder ss = new StringBuilder();
        prubehList.forEach(hovno -> ss.append(hovno).append("\n"));
        return ss.toString();
    }

    public static void addPrubehList(String s) {
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

package sample;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Controller implements Initializable {
    //jednotlive pejny
    @FXML
    public BorderPane mainPane;


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

    //netreba komentovat?
    @FXML
    public Button btnSTART;

    //pro ucel logu
    @FXML
    public TextArea txtLogis;
    @FXML
    public ProgressBar procesBar;
    @FXML
    public Text procesProcenta;

    //seznamy pro vyrobky
    @FXML
    public ListView listviewone;

    //vyrobky instance
    Vyrobky v1, v2, v3, v4, v5;

    //co se stane pred spustenim
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        v1 = new Vyrobky("Vyrobek1",
                7000,
                4000,
                Useky.BACKLOG,
                Arrays.asList(Instance.s_1, Instance.s_2, Instance.s_3));
        v2 = new Vyrobky("Vyrobek2",
                20000,
                5000,
                Useky.BACKLOG,
                Arrays.asList(Instance.s_1, Instance.s_4, Instance.s_5));
        v3 = new Vyrobky("Vyrobek3",
                10000,
                4500,
                Useky.BACKLOG,
                Arrays.asList(Instance.s_2, Instance.s_4, Instance.s_6));
        v4 = new Vyrobky("Vyrobek4",
                2000,
                8000,
                Useky.BACKLOG,
                Arrays.asList(Instance.s_3, Instance.s_5, Instance.s_6));
        v5 = new Vyrobky("Vyrobek5",
                2000,
                2000,
                Useky.BACKLOG,
                Arrays.asList(Instance.s_1, Instance.s_6, Instance.s_7));

        check1.setText(v1.textForPane());
        check2.setText(v2.textForPane());
        check3.setText(v3.textForPane());
        check4.setText(v4.textForPane());
        check5.setText(v5.textForPane());
    }

    /**
     * btn kterym se zapne cely proces prvni checkne ktere vyrobky se maji vyrabet,
     * zalozi jejich instance a zavola metodu run
     * @param actionEvent
     */
    public void makeSomeNoise(ActionEvent actionEvent) {
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

    public void toListView() {

    }

    /**
     * Jenom napad ze by zobrazila treba alert se stavem jednotlivych surovin
     * @param actionEvent
     */
    public void menuSklad(ActionEvent actionEvent) {
    }

    /**
     * Zastavi proces vyroby, killThemAll
     * @param actionEvent
     */
    public void menuStop(ActionEvent actionEvent) {
    }

    /**
     * Zavre appku
     * @param actionEvent
     */
    public void menuClose(ActionEvent actionEvent) {
    }


}

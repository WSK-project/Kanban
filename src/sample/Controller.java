package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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

    //co se stane pred spustenim
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Utils.letsRoll();
    }

    /**
     * btn kterym se zapne cely proces prvni checkne ktere vyrobky se maji vyrabek,
     * zalozi jejich instance a zavola metodu run
     * @param actionEvent
     */
    public void makeSomeNoise(ActionEvent actionEvent) {
        Vyrobky v1 = new Vyrobky("Vyrobek1", 10000, 4500, Useky.BACKLOG, null);
        Vyrobky v2 = new Vyrobky("Vyrobek2", 10000, 4500, Useky.BACKLOG, null);
        Vyrobky v3 = new Vyrobky("Vyrobek3", 10000, 4500, Useky.BACKLOG, null);
        Vyrobky v4 = new Vyrobky("Vyrobek4", 10000, 4500, Useky.BACKLOG, null);
        v1.start();
        v2.start();
        v3.start();
        v4.start();
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

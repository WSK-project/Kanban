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
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //jednotlive pejny
    @FXML
    public BorderPane mainPane;
    @FXML
    public AnchorPane anchorTD;
    @FXML
    public AnchorPane anchorIP;
    @FXML
    public AnchorPane anchorDN;

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

    }

    //btn start
    public void makeSomeNoise(ActionEvent actionEvent) {
    }

    public void menuSklad(ActionEvent actionEvent) {
    }

    public void menuStop(ActionEvent actionEvent) {
    }

    public void menuClose(ActionEvent actionEvent) {
    }
}

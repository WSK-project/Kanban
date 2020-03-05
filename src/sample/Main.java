package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    // Magic. Do not touch.
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(899);
        primaryStage.setMinWidth(1541);
        primaryStage.setTitle("Dejv je nejlepsi");
        primaryStage.setScene(new Scene(root, 1541, 899));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

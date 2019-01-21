package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("CS61b StyleChecker");

        Scene scene = new Scene(root, 720, 360);

        primaryStage.setScene(scene);

        scene.getStylesheets().add(Main.class.getResource("modena_dark.css").toExternalForm());

        primaryStage.show();

        Controller.setSTAGE(primaryStage);
        Controller.setSCENE(scene);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

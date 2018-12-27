package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import java.awt.*;

public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public static void setSTAGE(Stage STAGE) {
        Controller.STAGE = STAGE;
    }

    public static Stage STAGE;

    @FXML
    private TextField textField1;


    public TreeView getViewField() {
        return viewField;
    }

    @FXML
    private TreeView viewField;





    @FXML
    public void selectPath(ActionEvent event) {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select path");

        File f = directoryChooser.showDialog(STAGE);
        String path = f.getAbsolutePath();

        if (path.toCharArray()[path.length() - 1] != '\\') {
            path += "\\";
        }

        textField1.setText(path);

        System.out.println("Path = " + path);

        btnCheckPressed(event);
    }


    @FXML
    public void btnCheckPressed(ActionEvent event) {
        System.out.println("Check clicked");
        ProcessLauncher pl = new ProcessLauncher();
        pl.launch(textField1.getText());

        TreeItem<String> item = pl.getRootTreeNode();
        item.setExpanded(true);
        viewField.setRoot(item);
        viewField.setShowRoot(false);



    }


}
package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;


public class Controller implements Initializable {

    private static Stage STAGE;
    private static Scene SCENE;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private TreeView viewField;

    @FXML
    private Label bottomLabel;

    @FXML
    private TextField textField1;

    public static void setSTAGE(Stage stage) {
        Controller.STAGE = stage;
    }

    public static void setSCENE(Scene SCENE) {
        Controller.SCENE = SCENE;
    }

    public TreeView getViewField() {
        return viewField;
    }

    @FXML
    public void selectPath(ActionEvent event) {

        String defaultPath = "C:\\";

        Preferences node = Preferences.userRoot().node("CS61bSC");
        defaultPath = node.get("path", "C:\\");

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(defaultPath));
        directoryChooser.setTitle("Select path");

        File f = directoryChooser.showDialog(STAGE);
        String path = f.getAbsolutePath();

        if (path.toCharArray()[path.length() - 1] != '\\') {
            path += "\\";
        }

        node.put("path", path);
        textField1.setText(path);

        System.out.println("Check clicked");
        System.out.println("Path = " + path);

        btnCheckPressed(new ActionEvent());
    }


    @FXML
    public void btnCheckPressed(ActionEvent event) {

        System.out.println("Check clicked");

        ProcessLauncher pl = new ProcessLauncher();
        StringParser p = pl.launch(textField1.getText());

        TreeItem<String> item = p.getRootTreeNode();
        item.setExpanded(true);

        viewField.setRoot(item);
        viewField.setShowRoot(false);

        String labelText = "Errors found: "+p.getTotalErrorsCount()+", shown: "+p.getParserErrorsCount();

        if(p.getParserErrorsCount()!=p.getTotalErrorsCount()){
            System.out.println("Not all errors shown!");
            labelText+=" Not all errors are shown!";
//            bottomLabel.setTextFill(Color.RED);
        }

        bottomLabel.setText(labelText);
    }


}

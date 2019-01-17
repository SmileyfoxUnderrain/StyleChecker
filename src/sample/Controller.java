package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;


public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public static void setSTAGE(Stage stage) {
        Controller.STAGE = stage;
    }

    private static Stage STAGE;

    @FXML
    private TextField textField1;


    public TreeView getViewField() {
        return viewField;
    }

    @FXML
    private TreeView viewField;


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

        System.out.println("Path = " + path);

        btnCheckPressed(event);
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

        if(p.getParserErrorsCount()!=p.getTotalErrorsCount()){
            System.out.println("Not all errors shown!");
        }






    }


}

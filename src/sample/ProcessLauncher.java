package sample;

import javafx.scene.control.TreeItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessLauncher {

    private TreeItem<String> rootTreeNode;

    public TreeItem<String> getRootTreeNode() {
        return rootTreeNode;
    }

    public void launch(String path) {
        try {
            //Process process = new ProcessBuilder(System.getenv("windir")+"\\System32\\mstsc.exe","/v:"+tf.getText()).start();
            Process process = new ProcessBuilder(
                    "java"
                    , "-jar"
                    , "\"d:\\Education\\Java\\checkstyle\\checkstyle-6.15-all.jar\""
                    , "-c"
                    , "\"d:\\Education\\Java\\checkstyle\\61b_checks.xml\""
                    , path
            ).start();

            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "Cp1251");
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringParser p = new StringParser();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                p.parse(line);
            }
            rootTreeNode = p.getRootTreeNode();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

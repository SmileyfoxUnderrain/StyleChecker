package sample;

import javafx.scene.control.TreeItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessLauncher {

    private TreeItem<String> rootTreeNode;
    private StringParser parser;

    public TreeItem<String> getRootTreeNode() {
        return rootTreeNode;
    }

    public StringParser launch(String path) {
        try {
            Process process = new ProcessBuilder(
                    "java",
                    "-jar",
                    "\"d:\\Education\\Java\\checkstyle\\checkstyle-6.15-all.jar\"",
                    "-c",
                    "\"d:\\Education\\Java\\checkstyle\\61b_checks.xml\"",
                    path
            ).start();

            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "Cp1251");
            BufferedReader br = new BufferedReader(isr);
            String line;
            parser = new StringParser();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                parser.parse(line);
            }
//            rootTreeNode = parser.getRootTreeNode();



        } catch (IOException e) {
            e.printStackTrace();
        }
        return parser;
    }

}

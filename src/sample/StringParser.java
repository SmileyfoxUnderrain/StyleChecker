package sample;

import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringParser {

    private TreeItem<String> rootTreeNode = new TreeItem<>("Root");
    private int totalErrorsCount;
    private int parserErrorsCount;
    private boolean assignedRoot = false;

    public TreeItem<String> getRootTreeNode() {
        rootTreeNode.setExpanded(true);
        return rootTreeNode;
    }
    public int getTotalErrorsCount(){
        return totalErrorsCount;
    }

    public int getParserErrorsCount() {
        return parserErrorsCount;
    }

    public int containsString(TreeItem<String> node, String str) {

        int pos = -1;

        System.out.println("ContainsString invoked");
        System.out.println("Got node: " + node.toString());
        System.out.println("node children size: " + node.getChildren().size());

        for (int i = 0; i < node.getChildren().size(); i++) {
            System.out.println("Checking if exist: "
                    + node.getChildren().get(i).getValue()
                    + ", " + str);
            if (node.getChildren().get(i).getValue().equals(str)) {
                System.out.println("Equals");
                return pos = i;
            }
        }
        return pos;
    }

    public boolean parse(String line) {
        if (line.startsWith("[ERROR]")) {

            parserErrorsCount++;
            int level = 0;

            String[] splittedLine = line.split("\\\\", 0);

            List<String> splittedLineList = Arrays.asList(splittedLine);
            int listSize = splittedLineList.size()-1;
            String lastLine = splittedLineList.get(listSize);
            String leftpart = lastLine.substring(0,lastLine.indexOf(":"));
            String rightpart = lastLine.substring(lastLine.indexOf(":")+1);

            ArrayList<String> splittedLineArrayList = new ArrayList<>();
            splittedLineArrayList.addAll(splittedLineList);

            splittedLineArrayList.remove(listSize);
            splittedLineArrayList.add(leftpart);
            splittedLineArrayList.add(rightpart);

            TreeItem<String> currentNode;
            TreeItem<String> parentNode = rootTreeNode;

            for (String retval : splittedLineArrayList) {

                if (retval.contains("[ERROR]")) {
                    retval = retval.substring(8);
                }

                if (!retval.equals("..")) {
                    if (level < splittedLineArrayList.size() - 1) {

                        System.out.println("parent node: " + parentNode.toString());
                        int pos = containsString(parentNode, retval);

                        if (pos == -1) {

                            currentNode = new TreeItem<>(retval);
                            parentNode.getChildren().add(currentNode);
                            System.out.println("current node " + currentNode.toString()
                                    + " added to " + parentNode.toString());
                            currentNode.setExpanded(true);
                            parentNode = currentNode;
                        } else {
                            parentNode = parentNode.getChildren().get(pos);
                            System.out.println("Existing node set as parent:"
                                    + parentNode.toString());
                        }

                    } else if (level == splittedLineArrayList.size() - 1) {

                        System.out.println("Error: " + retval);
                        System.out.println("Adding an error to parent node "
                                + parentNode.toString());
                        parentNode.getChildren().add(new TreeItem<>(retval));
                    }
                }
                level++;
            }
        }else if(line.startsWith("Checkstyle ends with")){
            String[] words = line.split(" ");
            totalErrorsCount = Integer.parseInt(words[3]);
            System.out.println("found "+totalErrorsCount+"error(s) by Checkstyle");
        }
        return true;
    }
}

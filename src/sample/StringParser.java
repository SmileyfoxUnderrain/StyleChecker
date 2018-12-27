package sample;

import javafx.scene.control.TreeItem;

public class StringParser {

    private TreeItem<String> rootTreeNode = new TreeItem<>("Root");
    private boolean assignedRoot = false;

    public TreeItem<String> getRootTreeNode() {
        rootTreeNode.setExpanded(true);
        return rootTreeNode;
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

            int level = 0;
            String[] splittedLine = line.split("\\\\", 0);
            TreeItem<String> currentNode;
            TreeItem<String> parentNode = rootTreeNode;

            for (String retval : splittedLine) {

                if (retval.contains("[ERROR]")) retval = retval.substring(8, retval.length());

                if (!retval.equals("..")) {
                    if (level < splittedLine.length - 1) {

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
                            System.out.println("Existing node set as parent:" + parentNode.toString());
                        }

                    } else if (level == splittedLine.length - 1) {

                        System.out.println("Error: " + retval);
                        System.out.println("Adding an error to parent node " + parentNode.toString());
                        parentNode.getChildren().add(new TreeItem<>(retval));
                    }
                }
                level++;
            }
        }
        return true;
    }
}

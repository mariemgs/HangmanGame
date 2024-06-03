package tott.pendu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class BSTInterface {
    public void gobackconfig(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("configuration.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Configuartion");
        window.setScene(new Scene(parent, 800, 650));
        window.show();
    }

    private BinarySearchTree bst = new BinarySearchTree();
    private BSTNode racine;


    @FXML
    private AnchorPane treePane;

    @FXML
    public void initialize() {
        // Load tree data and display the tree
        System.out.println("test3");
            this.racine = new BSTNode('\0');
            bst.construireArbreDepuisFichier("C:\\Users\\user\\IdeaProjects\\Pendu\\src\\main\\java\\tott\\pendu\\dictarbre.txt");
             // Assuming a method to get the root node
        this.racine=bst.getRacine();
            displayTree(racine);
    }

    public void displayTree(BSTNode root) {



        if (root == null) {
            // Handle case where there is no tree to display
            return;
        }
        // Clear the pane before redrawing the tree
        treePane.getChildren().clear();
        // Begin the recursion from the middle of the AnchorPane
        displayNode(root, treePane.getPrefWidth() /1.5, 40, treePane.getPrefWidth() / 4,true);
    }



    private void displayNode(BSTNode node, double x, double y, double horizontalGap,boolean first) {
        // Avoid drawing a node if it is null
        if (node == null) {
            return;
        }
        if(first==true){
            // Recursively draw the left child if it exists
            if (node.gauche != null) {


                displayNode(node.gauche, x , y, horizontalGap ,false);
            }
            // Recursively draw the right child if it exists
            if (node.droit != null) {
                displayNode(node.droit, x , y, horizontalGap ,false);

            }
        }else{
            System.out.println(node.lettre);
            // Create a visual representation (circle) of the current node
            Circle circle = new Circle(x, y, 20);
            circle.setFill(Color.DODGERBLUE);

            // Add the visual node to the pane
            treePane.getChildren().add(circle);

            // Create a text node to display the node.lettre value inside the circle
            Text text = new Text(String.valueOf(node.lettre));
            text.setX(x - 5); // Adjust the position of the text inside the circle
            text.setY(y + 5); // Adjust the position of the text inside the circle

            // Add the text node to the pane
            treePane.getChildren().add(text);

            // Calculate the new position for the child nodes
            double childX = x + horizontalGap;
            double childY = y + 100; // Using a vertical gap of 100 as an example

            // Recursively draw the left child if it exists
            if (node.gauche != null) {
                Line lineToLeft = new Line(x, y+15, x - horizontalGap, childY);
                treePane.getChildren().add(lineToLeft);
                displayNode(node.gauche, x - horizontalGap, childY, horizontalGap / 2,false);
            }

            // Recursively draw the right child if it exists
            if (node.droit != null) {
                Line lineToRight = new Line(x, y+15, childX, childY);
                treePane.getChildren().add(lineToRight);
                displayNode(node.droit, childX, childY, horizontalGap / 2,false);
            }
        }

    }

}

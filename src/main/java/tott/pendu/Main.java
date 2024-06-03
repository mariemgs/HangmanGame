package tott.pendu;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       Parent root = FXMLLoader.load(getClass().getResource("hangman.fxml"));
        primaryStage.setTitle("HangMan Game");
        primaryStage.setScene(new Scene(root, 800, 650));
        primaryStage.show();

        //launchBSTInterface();

    }
    // Method to launch the BSTInterface
    /*private void launchBSTInterface() {
        // Create an instance of BSTInterface
        BSTInterface bstInterface = new BSTInterface();

        // Create a new stage for BSTInterface
        Stage bstStage = new Stage();
        bstStage.setTitle("Binary Search Tree Interface");

        // Call the start method of BSTInterface with the new stage
        try {
            bstInterface.start(bstStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        launch(args);
    }
}

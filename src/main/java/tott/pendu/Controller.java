package tott.pendu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {
    public void changeScene(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Mode.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Hangman Game");
        window.setScene(new Scene(parent, 800, 650));
        window.show();
    }
    public void changeScene2(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("configuration.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Configuartion");
        window.setScene(new Scene(parent, 800, 650));
        window.show();
    }


}

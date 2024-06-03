package tott.pendu;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller3 {
    public void changeScene3(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("hangman.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Menu");
        window.setScene(new Scene(parent, 800, 650));
        window.show();
    }

    public void showtree(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BSTInterface.fxml"));
        AnchorPane root = loader.load(); // This also initializes the controller

        // Stage window instance acquired from the event source
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Binary Search Tree Visualization");

        // Set up the scene with appropriate dimensions
        Scene scene = new Scene(root, 800, 650);
        window.setScene(scene);
        window.show();
        // Get the controller associated with the FXML
        BSTInterface controller = loader.getController();

        // The initialize method of the controller should have already been called
        // due to loading the FXML, which in turn calls displayTree or similar visual setup
        // If you need to manually call a method to display the tree, do so here
         //controller.initialize();

    }





    @FXML
    TextField Addinput;
    @FXML
    TextField Addhint;

    @FXML
    TextField searchWordField;
    @FXML
    Label resultLabel;
    @FXML
    TextField deleteWordField;
    private BinarySearchTree bst = new BinarySearchTree();
    private TextArea treeDisplay = new TextArea();
    public void Add() {
        String userInput = Addinput.getText().toUpperCase();
        String userhint = Addhint.getText().toUpperCase();

        bst.ajouterMot(userInput);
        try {
            bst.ajouterMotAuFichier(userInput,userhint);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


        System.out.println(userInput);
    }


    public void Delete() {
        String wordToDelete = deleteWordField.getText().toUpperCase();
        try {
            bst.supprimerMot(wordToDelete);
            System.out.println("le suis la");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        deleteWordField.clear();
        refreshTreeDisplay();

    }

    public void Search() {
        String wordToSearch = searchWordField.getText().toUpperCase().trim();
        refreshTreeDisplay();
        if (!wordToSearch.isEmpty()) {
            boolean existe = bst.motExiste(wordToSearch);
            if (existe) {
                resultLabel.setText("Le mot '" + wordToSearch + "' existe dans le dictionnaire.");
            } else {
                resultLabel.setText("Le mot '" + wordToSearch + "' n'existe pas dans le dictionnaire.");
            }
            searchWordField.clear();
        } else {
            resultLabel.setText("Veuillez entrer un mot à rechercher.");
        }
    }
    private void refreshTreeDisplay() {


        treeDisplay.clear();

        bst.afficher();

    }
    }

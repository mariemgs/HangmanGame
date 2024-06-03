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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller2 {

    public void changeScene4(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("hangman.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Menu");
        window.setScene(new Scene(parent, 800, 650));
        window.show();
    }

    public void pause()  {
        game.setVisible(true);
    }

    public void oncontinue()  { game.setVisible(false);}

    public void exit() {
        System.exit(0);
    }




    @FXML
    ImageView img;
    Image image2 = new Image(getClass().getResourceAsStream("images/2.jpg"));
    Image image3 = new Image(getClass().getResourceAsStream("images/3.jpg"));
    Image image4 = new Image(getClass().getResourceAsStream("images/4.jpg"));
    Image image5 = new Image(getClass().getResourceAsStream("images/5.jpg"));
    Image image6 = new Image(getClass().getResourceAsStream("images/6.jpg"));
    Image image7 = new Image(getClass().getResourceAsStream("images/7.jpg"));
    Image win = new Image(getClass().getResourceAsStream("images/win.jpg"));

    @FXML
    TextField tf1;
    @FXML
    TextField tf2;
    @FXML
    TextField tf3;
    @FXML
    TextField tf4;
    @FXML
    TextField tf5;
    @FXML
    TextField tf6;
    @FXML
    TextField tf7;
    @FXML
    TextField tf8;
    @FXML
    TextField input;
    @FXML
    Label hint;
    @FXML
    Label letter_count;
    @FXML
    Label hint_label;
    @FXML
    AnchorPane game;
    private BinarySearchTree bst1 = new BinarySearchTree();
    private TextArea treeDisplay1 = new TextArea();

    List<String> words = new ArrayList<>();
    List<String> hints = new ArrayList<>();
    int random;
    int letter_size;
    private BSTNode racine;
    int count;
    String hint_str;
    public Controller2() {






        readWordsFromFile();
        count =0;
        this.racine = new BSTNode('\0');
        random = new Random().nextInt(words.size());
        String word_hint = words.get(random);

        String[] split = word_hint.split(" ", 2);
        String word = split[0];


        bst1.construireArbreDepuisFichier("C:\\Users\\user\\IdeaProjects\\Pendu\\src\\main\\java\\tott\\pendu\\dictarbre.txt");
        letter_size = word.length();
    }

    private void readWordsFromFile() {
        refreshTreeDisplay();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\IdeaProjects\\Pendu\\src\\main\\java\\tott\\pendu\\dictarbre.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(" ", 2);
                if (parts.length == 2) {
                    if (parts[0].length() <= 5) { // Ensure that at least one part is available and its length is exactly 5
                        words.add(parts[0]);
                        hints.add(parts[1]);
                    }

                   }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        setHint();
        input.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String inputChar = event.getCharacter();
            // Check if the input is a single letter (upper or lower case) and if the length is not already 1
            if (!isValidInput(inputChar) || input.getText().length() >= 1) {
                event.consume(); // Consume the event to prevent non-valid input or input length greater than 1
            }
        });
    }


    private boolean isValidInput(String inputChar) {
        // Check if the input is a single letter (upper or lower case)
        return inputChar.length() == 1 && inputChar.matches("[a-zA-Z]");
    }

    public void setHint() {


        hint.setText(hints.get(random));
        letter_count.setText(words.get(random).length() + " Letters");

        if(letter_size==7){
            tf8.setVisible(false);
        }
        if(letter_size==6){
            tf7.setVisible(false);
            tf8.setVisible(false);
        }
        if(letter_size==5){
            tf6.setVisible(false);
            tf7.setVisible(false);
            tf8.setVisible(false);
        }
        if(letter_size==4){
            tf5.setVisible(false);
            tf6.setVisible(false);
            tf7.setVisible(false);
            tf8.setVisible(false);
        }
    }


    public void CheckInput() {
        String userInput = input.getText().toUpperCase();
        String targetWord = words.get(random);
        if (life > 0) {
            if (count != targetWord.length()) {
                // Controller2 should not be creating a new instance of itself within BinaryTree
                List<Integer> positions = bst1.findcaracterposistion2(targetWord, userInput);
                for (int index : positions) {
                    setLetter(index, userInput);
                    count++;
                }

                if (positions.isEmpty()) {
                    setImage();
                    // Life logic should be updated here as well.
                    System.out.println("La lettre " + userInput + " n'existe pas dans le mot.");
                }

                // Clear input after processing
                input.clear();
                if  (count == targetWord.length()){
                    img.setImage(win);
                }
            }
        }

    }
    public void setLetter(int index,String str){
        if(index==0)
            tf1.setText(str);
        else if(index==1)
            tf2.setText(str);
        else if(index==2)
            tf3.setText(str);
        else if(index==3)
            tf4.setText(str);
        else if(index==4)
            tf5.setText(str);
        else if(index==5)
            tf6.setText(str);
        else if(index==6)
            tf7.setText(str);
        else if(index==7)
            tf8.setText(str);
    }

    int life=6;
    public void setImage() {
        if (life > 0) {

            if (life == 6)
                img.setImage(image2);

            else if (life == 5)
                img.setImage(image3);
            else if (life == 4)
                img.setImage(image4);
            else if (life == 3)
                img.setImage(image5);
            else if (life == 2)
                img.setImage(image6);
            else if (life == 1) {
                img.setImage(image7);

            }

            life--;
        }
    }



    private void refreshTreeDisplay() {
        treeDisplay1.clear();
        bst1.afficher();
    }

    public void changeScene(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("gameScene.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Hangman Game");
        window.setScene(new Scene(parent, 800, 650));
        window.show();
    }
}

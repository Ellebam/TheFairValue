package GUIElements;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;


public class SearchBar extends HBox{
    SearchBar searchBar;
    TextField searchTextField;
    Button submitButton;

    public SearchBar() {
        searchBar = this;

        TextField searchTextField = new TextField();
        this.searchTextField = searchTextField;
        searchTextField.setPromptText("Ticker Symbol");
        searchTextField.setFocusTraversable(false);
        searchTextField.setId("searchTextField");

        StackPane layout = new StackPane();
        layout.getChildren().addAll(searchTextField);
        layout.getStylesheets().add(this.getClass().getResource(
                "searchTextFieldIcon").toExternalForm());

        Button submitButton;
        try {
            FileInputStream arrowIconInput = new FileInputStream("src/GUIElements/blue-and-white-arrow-logo-png-clip-art-thumbnail.jpg");
            Image arrowIconImage = new Image(arrowIconInput);
            ImageView arrowImageView = new ImageView(arrowIconImage);
            arrowImageView.fitHeightProperty().setValue(30);
            arrowImageView.fitWidthProperty().setValue(26);

            submitButton = new Button("", arrowImageView);
        }catch (Exception ex) {
            submitButton = new Button("search");
            ex.printStackTrace();
        }

        this.submitButton = submitButton;


        searchBar.getChildren().add(layout);
        searchBar.getChildren().add(submitButton);
        searchBar.setSpacing(10);


    }
}

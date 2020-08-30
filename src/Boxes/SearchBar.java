package Boxes;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class SearchBar extends HBox {
    SearchBar searchBar;
    TextField searchTextField;

    public SearchBar(){
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

        searchBar.getChildren().add(layout);


    }
}

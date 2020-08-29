package Boxes;

import javafx.scene.layout.HBox;

import java.awt.*;

public class SearchBar extends HBox {
    SearchBar searchBar;
    TextField searchTextField;

    public SearchBar(){
        searchBar = this;
        TextField searchTextFiled = new TextField();
        this.searchTextField = searchTextFiled;
    }
}

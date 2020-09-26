package GUIElements;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import sample.Main;

import java.io.FileInputStream;

/**
 * Class representing the SearchBar including the SubmitButton inside the OpeningVBox
 */
public class SearchBar extends HBox{
    SearchBar searchBar;
    TextField searchTextField;
    Button submitButton;

    /**
     * Constructor will build a TextField for user input as well as a custom button for submitting the input. A StackPane
     * is laid over the TextField to add a small magnifier icon showing a tooltip explaining which symbols are valid.
     */
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

        String tooltipText = "Only U.S. stock ticker symbols are valid for data retrieval!";
        Tooltip tooltip = new Tooltip(tooltipText );
        tooltip.setWrapText(true);
        tooltip.setMaxWidth(350);
        Main.fastenTooltipStartTiming(tooltip);
        Tooltip.install(searchTextField,tooltip);

        Button submitButton;
        try {

            Image arrowIconImage = new Image(getClass().getResourceAsStream("/GUIElements/blue-and-white-arrow-logo-png-clip-art-thumbnail.jpg"));
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

    public TextField getSearchTextField() {
        return searchTextField;
    }

    public Button getSubmitButton() {
        return submitButton;
    }


}

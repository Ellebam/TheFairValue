package GUIBoxes;

import Controllers.ClientManager;
import Controllers.DataContainerManager;
import Controllers.KeyManager;
import GUIElements.SearchBar;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class OpeningVBox extends VBox {

    OpeningVBox openingVBox;
    Label HeaderLabel;
    SearchBar searchBar;
    Button demoButton;
    DataContainerManager dataContainerManager;


    public OpeningVBox() {
        openingVBox = this;


        String labelText = "Welcome to The Fair Value. " +
                "Please enter a stock ticker symbol of the company that should be analyzed." +
                "(e.g. MSFT for Microsoft Corp.)";
        Label HeaderLabel = new Label(labelText);
        this.HeaderLabel = HeaderLabel;
        HeaderLabel.setWrapText(true);
        HeaderLabel.setMaxWidth(600);
        HeaderLabel.setAlignment(Pos.CENTER);
        HeaderLabel.setTextAlignment(TextAlignment.CENTER);
        Font labelFont = new Font("Verdana", 20);
        HeaderLabel.setFont(labelFont);

        SearchBar searchBar = new SearchBar();
        searchBar.setAlignment(Pos.CENTER);
        this.searchBar = searchBar;

        Button demoButton = new Button("Try Demo");
        demoButton.setStyle("-fx-text-fill: #00BFFF; -fx-font-size: 18;");
        this.demoButton = demoButton;


        openingVBox.getChildren().add(HeaderLabel);
        openingVBox.getChildren().add(searchBar);
        openingVBox.getChildren().add(demoButton);
        openingVBox.setAlignment(Pos.CENTER);
        openingVBox.setSpacing(20);

      


    }

    public void loadData(String symbol) {
        try {
            KeyManager keyManager = new KeyManager();
            System.out.println(keyManager.getKey());
            ClientManager ClientManager = new ClientManager("JNJ", keyManager.getKey());
            DataContainerManager dataContainerManager = new DataContainerManager(ClientManager);
            this.dataContainerManager = dataContainerManager;
        } catch (Exception ex) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Error while loading Data!");
            errorAlert.show();

        }
    }
}

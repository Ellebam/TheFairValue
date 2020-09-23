package GUIBoxes;

import Controllers.ClientManager;
import Controllers.DataContainerManager;
import Controllers.KeyManager;
import GUIElements.SearchBar;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import sample.Main;




public class OpeningVBox extends VBox {

    OpeningVBox openingVBox;
    Label HeaderLabel;
    SearchBar searchBar;
    Button demoButton;
    DataContainerManager dataContainerManager;
    Label progressLabel;




    public OpeningVBox() {
        openingVBox = this;


        String labelText = "Welcome to The Fair Value. " +
                "Please enter a stock ticker symbol of the company that should be analyzed." +
                "\n"+"(e.g. MSFT for Microsoft Corp.)";
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
        searchBar.getSubmitButton().setOnAction(value->{
            progressLabel.setVisible(true);

            new Thread() {
                public void run() {
                    try {
                        loadData(searchBar.getSearchTextField().getText());

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Main.getSceneController().setSceneContent(new AnalysisStackpane(dataContainerManager));
                            }
                        });

                    }catch (Exception ex){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                ex.printStackTrace();

                                Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Error while loading Data!");
                                errorAlert.show();
                                progressLabel.setVisible(false);
                            }
                        });
                    }
                }
            }.start();
        });

        try {
            Image image = new Image(getClass().getResourceAsStream("/GUIElements/Rolling-1s-200px.gif"));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(25);
            imageView.setFitWidth(25);
            progressLabel = new Label("Loading data..",imageView);
            progressLabel.setVisible(false);
        }catch (Exception ex){ex.printStackTrace();}


        Button demoButton = new Button("Try Demo");
        demoButton.setStyle("-fx-text-fill: #00BFFF; -fx-font-size: 18;");
        this.demoButton = demoButton;



        demoButton.setOnAction(value -> {

            progressLabel.setVisible(true);

            new Thread() {
                public void run() {
                    try {
                        loadDemoData();

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Main.getSceneController().setSceneContent(new AnalysisStackpane(dataContainerManager));

                            }
                        });
                    }catch (Exception ex){
                        ex.printStackTrace();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Error while loading Data!");
                                errorAlert.show();
                                progressLabel.setVisible(false);
                            }
                        });

                    }



                }
            }.start();
        });



        openingVBox.getChildren().addAll(HeaderLabel,searchBar,progressLabel,demoButton);

        openingVBox.setAlignment(Pos.CENTER);
        openingVBox.setSpacing(20);




    }

    public void loadData(String symbol) throws Exception {

        KeyManager keyManager = new KeyManager();
        System.out.println(keyManager.getKey());
        ClientManager ClientManager = new ClientManager(symbol, keyManager.getKey());
        DataContainerManager dataContainerManager = new DataContainerManager(ClientManager);
        this.dataContainerManager = dataContainerManager;

    }

    public void loadDemoData() throws Exception {

        KeyManager keyManager = new KeyManager();
        System.out.println(keyManager.getKey());
        ClientManager ClientManager = new ClientManager("JNJ", keyManager.getKey());
        DataContainerManager dataContainerManager = new DataContainerManager(ClientManager);
        this.dataContainerManager = dataContainerManager;



    }
}




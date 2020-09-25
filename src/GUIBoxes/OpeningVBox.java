package GUIBoxes;

import Controllers.ClientManager;
import Controllers.DataContainerManager;
import Controllers.KeyManager;
import GUIElements.SearchBar;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import sample.Main;


/**
 * Class representing the opening pane of the application. User can insert a stock ticker symbol and search for a companies
 * data for visualization or use the demo button to fetch sample data from a predefined company.
 */
public class OpeningVBox extends VBox {

    OpeningVBox openingVBox;
    Label HeaderLabel;
    SearchBar searchBar;
    Button demoButton;
    DataContainerManager dataContainerManager;
    Label progressLabel;


    /**
     * The constructor will build a TextField and two buttons for the user to search data with. One will take the the
     * input from the TextField (Inside SearchBar object) and use it to build all needed Data inside the DataContainerManager object with the
     * ClientManager. The other button ("demoButton") will use a predefined ticker symbol to build the same data types and
     * display them in the AnalysisStackPane onbject.
     */
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


        //SearchBar object used for text input
        SearchBar searchBar = new SearchBar();
        searchBar.setAlignment(Pos.CENTER);
        this.searchBar = searchBar;

        // setting up a pseudo loading animation
        try {
            Image image = new Image(getClass().getResourceAsStream("/GUIElements/Rolling-1s-200px.gif"));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(25);
            imageView.setFitWidth(25);
            progressLabel = new Label("Loading data..",imageView);
            progressLabel.setVisible(false);
        }catch (Exception ex){ex.printStackTrace();}

        /*The SubmitButton of the searchbar will trigger a thread which will handle data loading and the updating of
        * the UI. The buttons action starts with making a "loading" label visible to inform the user that data is being loaded.
        * afterwards the actual thread is started with the loadData() method for building the DataContainerManager object.
        * A new runnable is started which will be invoked when data loading has been finished to refresh the page and
        * display the built AnalysisStackPane with all data visualized.  */
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



        /* Building of a demo button for fast usage of the application without ticker symbol input. The behaviour of the
        * Button and the outcome are the same as for the SubmitButton of the SearchBar but with a predefined ticker symbol */
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


    /**
     * Method for building of new ClientManager and DataContainerManager objects. It will then set the current
     * DataContainerManager to the newly created one.
     * @param symbol String for determining which company should be analyzed
     * @throws Exception must be catched at ui level to show error while loading data message
     */
    public void loadData(String symbol) throws Exception {

        KeyManager keyManager = new KeyManager();
        System.out.println(keyManager.getKey());
        ClientManager ClientManager = new ClientManager(symbol, keyManager.getKey());
        DataContainerManager dataContainerManager = new DataContainerManager(ClientManager);
        this.dataContainerManager = dataContainerManager;

    }

    /**
     * same as loadData() except with predefined ticker symbol
     * @throws Exception
     */
    public void loadDemoData() throws Exception {

        KeyManager keyManager = new KeyManager();
        System.out.println(keyManager.getKey());
        ClientManager ClientManager = new ClientManager("JNJ", keyManager.getKey());
        DataContainerManager dataContainerManager = new DataContainerManager(ClientManager);
        this.dataContainerManager = dataContainerManager;



    }
}




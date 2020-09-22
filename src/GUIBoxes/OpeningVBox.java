package GUIBoxes;

import Controllers.ClientManager;
import Controllers.DataContainerManager;
import Controllers.KeyManager;
import GUIElements.SearchBar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import sample.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;


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
            loadData(searchBar.getSearchTextField().getText());
            Main.getSceneController().setSceneContent(new AnalysisStackpane(dataContainerManager));
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



        demoButton.setOnAction(value ->{
        Task task1 = new Task() {
            @Override
            protected Object call() throws Exception {
                progressLabel.setVisible(true);
                Thread.sleep(20000);


                Main.getSceneController().setSceneContent(new AnalysisStackpane(dataContainerManager));
                Main.getSceneController().getStage().show();



                return null;
            }
        };

        Task task2 = new Task() {
            @Override
            protected Object call() throws Exception {
                loadDemoData();

                return null;
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread (task2);

        thread1.start();
        thread2.start();


        });




        openingVBox.getChildren().addAll(HeaderLabel,searchBar,progressLabel,demoButton);

        openingVBox.setAlignment(Pos.CENTER);
        openingVBox.setSpacing(20);

      


    }

    public void loadData(String symbol) {
        try {
            KeyManager keyManager = new KeyManager();
            System.out.println(keyManager.getKey());
            ClientManager ClientManager = new ClientManager(symbol, keyManager.getKey());
            DataContainerManager dataContainerManager = new DataContainerManager(ClientManager);
            this.dataContainerManager = dataContainerManager;
        } catch (Exception ex) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Error while loading Data!");
            errorAlert.show();

        }
    }

    public void loadDemoData() {
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

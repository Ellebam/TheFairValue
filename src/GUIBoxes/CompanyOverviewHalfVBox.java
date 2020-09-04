package GUIBoxes;

import Controllers.DataContainerManager;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CompanyOverviewHalfVBox extends VBox {
    CompanyOverviewHalfVBox companyOverviewHalfVBox;
    String companyName;
    String companyDescription;
    String companySector;

    public CompanyOverviewHalfVBox (DataContainerManager dataContainerManager){
        companyOverviewHalfVBox = this;


        companyName = dataContainerManager.getCompanyOverviewData().getName();
        Label companyNameLabel = new Label(companyName);
        companyNameLabel.setFont(new Font("Verdana",20));

        companySector = "Industry: "+dataContainerManager.getCompanyOverviewData().getSector();
        Label companySectorLabel = new Label(companySector);
        companySectorLabel.setFont(new Font("Verdana",16));




        companyDescription = dataContainerManager.getCompanyOverviewData().getDescription();
        Label companyDescriptionLabel = new Label(companyDescription);
        companyDescriptionLabel.setMaxWidth(700);

        companyDescriptionLabel.setWrapText(true);
        companyDescriptionLabel.setFont(new Font("Verdana",14));
        ScrollPane descriptionScrollPane = new ScrollPane();
        descriptionScrollPane.setContent(companyDescriptionLabel);
        descriptionScrollPane.pannableProperty().set(true);
        descriptionScrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        descriptionScrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        descriptionScrollPane.setMaxWidth(750);
        descriptionScrollPane.setMinHeight(200);



        
        HistoricalStockPriceTabPane historicalStockPriceTapPane = new HistoricalStockPriceTabPane(dataContainerManager);

        companyOverviewHalfVBox.getChildren().add(companyNameLabel);
        companyOverviewHalfVBox.getChildren().add(companySectorLabel);
        companyOverviewHalfVBox.getChildren().add(descriptionScrollPane);
        companyOverviewHalfVBox.getChildren().add(historicalStockPriceTapPane);

    }
}

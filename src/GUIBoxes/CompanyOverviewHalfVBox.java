package GUIBoxes;

import Controllers.DataContainerManager;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


/**
 * This class represents the left side of the CompanyOverview-Display.
 */
public class CompanyOverviewHalfVBox extends VBox {
    CompanyOverviewHalfVBox companyOverviewHalfVBox;
    String companyName;
    String companyDescription;
    String companySector;

    /**
     * Constructor for CompanyOverviewHalfVBox. It will build all GUI components while getting the
     * needed Data from its dataContainerManaer object. It will also build new HistoricalStockPriceTabPane
     * showing historical stock prices.
     * @param dataContainerManager Object containing all data
     */
    public CompanyOverviewHalfVBox (DataContainerManager dataContainerManager){
        companyOverviewHalfVBox = this;


        companyName = dataContainerManager.getCompanyOverviewData().getName();
        Label companyNameLabel = new Label(companyName);
        companyNameLabel.setFont(new Font("Verdana",20));

        companySector = "Industry: "+dataContainerManager.getCompanyOverviewData().getIndustry();
        Label companySectorLabel = new Label(companySector);
        companySectorLabel.setFont(new Font("Verdana",16));




        companyDescription = dataContainerManager.getCompanyOverviewData().getDescription();
        Label companyDescriptionLabel = new Label(companyDescription);


        companyDescriptionLabel.setWrapText(true);
        companyDescriptionLabel.setFont(new Font("Verdana",14));

        ScrollPane descriptionScrollPane = new ScrollPane();
        descriptionScrollPane.setContent(companyDescriptionLabel);
        descriptionScrollPane.pannableProperty().set(true);
        descriptionScrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        descriptionScrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        descriptionScrollPane.setPrefHeight(200);
        descriptionScrollPane.setFitToWidth(true);




        
        HistoricalStockPriceTabPane historicalStockPriceTapPane = new HistoricalStockPriceTabPane(dataContainerManager);




        companyOverviewHalfVBox.getLocalToSceneTransform();
        companyOverviewHalfVBox.getChildren().add(companyNameLabel);
        companyOverviewHalfVBox.getChildren().add(companySectorLabel);
        companyOverviewHalfVBox.getChildren().add(descriptionScrollPane);
        companyOverviewHalfVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));

        companyOverviewHalfVBox.getChildren().add(historicalStockPriceTapPane);


    }
}

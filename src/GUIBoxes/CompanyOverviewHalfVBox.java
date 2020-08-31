package GUIBoxes;

import Controllers.DataContainerManager;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CompanyOverviewHalfVBox extends VBox {
    CompanyOverviewHalfVBox companyOverviewHalfVBox;
    String companyName;
    String companyDescription;

    public CompanyOverviewHalfVBox (DataContainerManager dataContainerManager){
        companyOverviewHalfVBox = this;


        companyName = dataContainerManager.getCompanyOverviewData().getName();
        Label companyNameLabel = new Label(companyName);

        companyNameLabel.setFont(new Font("Amble.CN",20));




        companyDescription = dataContainerManager.getCompanyOverviewData().getDescription();
        Label companyDescriptionLabel = new Label(companyDescription);
        companyDescriptionLabel.setWrapText(true);
        companyDescriptionLabel.setMaxWidth(800);
        companyDescriptionLabel.setFont(new Font("Amble.CN",14));

        companyOverviewHalfVBox.getChildren().add(companyNameLabel);
        companyOverviewHalfVBox.getChildren().add(companyDescriptionLabel);
    }
}

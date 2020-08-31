package GUIBoxes;

import Controllers.DataContainerManager;
import javafx.scene.layout.HBox;

public class CompanyOverviewHBox extends HBox {
    CompanyOverviewHBox companyOverviewHBox;
    CompanyOverviewHalfVBox companyOverviewHalfVBox;
    CompanyOverviewTableView companyOverviewTableView;

    public CompanyOverviewHBox (DataContainerManager dataContainerManager){
        companyOverviewHBox = this;


        companyOverviewHalfVBox = new CompanyOverviewHalfVBox(dataContainerManager);


        companyOverviewTableView = new CompanyOverviewTableView(dataContainerManager);

        companyOverviewHBox.getChildren().add(companyOverviewHalfVBox);
        companyOverviewHBox.getChildren().add(companyOverviewTableView);
        companyOverviewHBox.setSpacing(15);
    }
}

package GUIBoxes;

import Controllers.DataContainerManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

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
        companyOverviewHalfVBox.setFillWidth(true);
        companyOverviewHBox.setHgrow(companyOverviewHalfVBox, Priority.ALWAYS);
        companyOverviewHBox.setPadding(new Insets(10));
        companyOverviewHBox.setSpacing(10);


        companyOverviewHBox.setAlignment(Pos.BASELINE_CENTER);
    }
}

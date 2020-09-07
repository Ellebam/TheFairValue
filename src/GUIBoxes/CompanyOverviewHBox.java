package GUIBoxes;

import Controllers.DataContainerManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * This Class combines all components for the building of the Company Overview display.
 */
public class CompanyOverviewHBox extends HBox {
    CompanyOverviewHBox companyOverviewHBox;
    CompanyOverviewHalfVBox companyOverviewHalfVBox;
    CompanyOverviewTableView companyOverviewTableView;


    /**
     * Constructor for the CompanyOverviewHBox instatianting the GUI components and rendering all data
     * @param dataContainerManager Object containing all data
     */
    public CompanyOverviewHBox (DataContainerManager dataContainerManager){
        companyOverviewHBox = this;

        //left part of the whole display
        companyOverviewHalfVBox = new CompanyOverviewHalfVBox(dataContainerManager);
        companyOverviewHalfVBox.setFillWidth(true);

        //right part of the whole display
        companyOverviewTableView = new CompanyOverviewTableView(dataContainerManager);

        companyOverviewHBox.getChildren().add(companyOverviewHalfVBox);
        companyOverviewHBox.getChildren().add(companyOverviewTableView);
        companyOverviewHBox.setHgrow(companyOverviewHalfVBox, Priority.ALWAYS);
        companyOverviewHBox.setPadding(new Insets(10));
        companyOverviewHBox.setSpacing(10);
        companyOverviewHBox.setAlignment(Pos.BASELINE_CENTER);
    }

    public CompanyOverviewHalfVBox getCompanyOverviewHalfVBox() {
        return companyOverviewHalfVBox;
    }

    public void setCompanyOverviewHalfVBox(CompanyOverviewHalfVBox companyOverviewHalfVBox) {
        this.companyOverviewHalfVBox = companyOverviewHalfVBox;
    }

    public CompanyOverviewTableView getCompanyOverviewTableView() {
        return companyOverviewTableView;
    }

    public void setCompanyOverviewTableView(CompanyOverviewTableView companyOverviewTableView) {
        this.companyOverviewTableView = companyOverviewTableView;
    }
}

package GUIBoxes;

import Controllers.DataContainerManager;
import Data.FinancialDataObject;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CompanyOverviewTableView extends TableView {
    CompanyOverviewTableView companyOverviewTableView;
    DataContainerManager dataContainerManager;

    public CompanyOverviewTableView (DataContainerManager dataContainerManager){
        companyOverviewTableView = this;
        this.dataContainerManager = dataContainerManager;

        TableColumn<String, FinancialDataObject> itemColumn = new TableColumn<>("Item");
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn<Double,FinancialDataObject> valueColumn = new TableColumn<>("Value");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));




        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getMarketCap());
        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getEBITDA());
        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getPrice2EarningsRatio());
        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getEarningsPerShare());

        companyOverviewTableView.getColumns().add(itemColumn);
        companyOverviewTableView.getColumns().add(valueColumn);

        companyOverviewTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }
}

package GUIBoxes;

import Controllers.DataContainerManager;
import Controllers.DataExtractor;
import Data.FinancialDataObject;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CompanyOverviewTableViewTwo extends TableView {
    CompanyOverviewTableViewTwo companyOverviewTableViewTwo;
    DataContainerManager dataContainerManager;

    public CompanyOverviewTableViewTwo(DataContainerManager dataContainerManager){
        companyOverviewTableViewTwo = this;
        this.dataContainerManager = dataContainerManager;

        TableColumn<FinancialDataObject, String> itemColumn = new TableColumn<>("Item");
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("name"));




        TableColumn<FinancialDataObject, Double> valueColumn = new TableColumn<>("Value");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));





        companyOverviewTableViewTwo.getItems().add(dataContainerManager.getCompanyOverviewData().getSector());

        companyOverviewTableViewTwo.getItems().add(dataContainerManager.getCompanyOverviewData().getPrice2EarningsRatio());

        companyOverviewTableViewTwo.getItems().add(dataContainerManager.getCompanyOverviewData().getEarningsPerShare());
        companyOverviewTableViewTwo.getItems().add(dataContainerManager.getCompanyOverviewData().getDividendsPerShare());
        companyOverviewTableViewTwo.getItems().add(dataContainerManager.getCompanyOverviewData().getDividendYield());
        companyOverviewTableViewTwo.getItems().add(dataContainerManager.getCompanyOverviewData().getFiftytwoWeekHigh());
        companyOverviewTableViewTwo.getItems().add(dataContainerManager.getCompanyOverviewData().getBookValue());

        companyOverviewTableViewTwo.getColumns().add(itemColumn);
        companyOverviewTableViewTwo.getColumns().add(valueColumn);

        companyOverviewTableViewTwo.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }
}

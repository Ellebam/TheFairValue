package GUIBoxes;

import Controllers.DataContainerManager;
import Controllers.DataExtractor;
import Data.FinancialDataObject;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CompanyOverviewTableView extends TableView {
    CompanyOverviewTableView companyOverviewTableView;
    DataContainerManager dataContainerManager;

    public CompanyOverviewTableView(DataContainerManager dataContainerManager){
        companyOverviewTableView = this;
        this.dataContainerManager = dataContainerManager;

        TableColumn<FinancialDataObject, String> itemColumn = new TableColumn<>("Item");
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("name"));




        TableColumn<FinancialDataObject, Double> valueColumn = new TableColumn<>("Value");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        valueColumn.setCellFactory(column ->{
            return new TableCell<FinancialDataObject, Double>(){
                @Override
                protected void updateItem(Double value, boolean empty){
                    super.updateItem(value,empty);

                    if (value==null||empty) {
                        setText(null);
                        setStyle("");
                    }else{
                        setText(DataExtractor.formatNumbers(value));
                    }
                }
            };

        });





        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getMarketCap());
        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getEBITDA());
        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getPrice2EarningsRatio());
        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getSharesOutstanding());
        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getEarningsPerShare());
        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getDividendsPerShare());
        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getDividendYield());
        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getFiftytwoWeekHigh());
        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getBookValue());

        companyOverviewTableView.getColumns().add(itemColumn);
        companyOverviewTableView.getColumns().add(valueColumn);

        companyOverviewTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        companyOverviewTableView.setMinWidth(200);



    }
}

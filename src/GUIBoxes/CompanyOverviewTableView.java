package GUIBoxes;

import Controllers.DataContainerManager;
import Controllers.DataExtractor;
import Data.FinancialDataObject;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * This Class represents the right side of the Company Overview display showing important key figures
 * in bullet point style.
 */
public class CompanyOverviewTableView extends TableView {
    CompanyOverviewTableView companyOverviewTableView;
    DataContainerManager dataContainerManager;

    /**
     * This Constructor will build the TableView of the Company Overview by selecting certain instance variables
     * of the CompanyOverviewData Object inside the given DataContainerManager object and rendering them.
     * All of the key values are rendered in a formatted state.
     * @param dataContainerManager Object containing all data
     */
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
                        /* the rendered Text is formatted by the DataExtractors method formatNumbers.
                        * This will ensure, that values over 1000 are shown abbreviated (e.g. 1k instead of 1000 */
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
        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getFiftytwoWeekLow());
        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getBookValue());
        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getAnalystTargetPrice());
        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getPrice2EarningsRatio());
        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getPercentInsiders());
        companyOverviewTableView.getItems().add(dataContainerManager.getCompanyOverviewData().getPercentInstitutions());
        companyOverviewTableView.getColumns().add(itemColumn);
        companyOverviewTableView.getColumns().add(valueColumn);


        companyOverviewTableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        companyOverviewTableView.setMinWidth(250);
        companyOverviewTableView.setPrefHeight(800);




    }
}

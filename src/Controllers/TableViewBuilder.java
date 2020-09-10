package Controllers;

import Data.FinancialDataObject;
import com.sun.xml.internal.fastinfoset.tools.FI_DOM_Or_XML_DOM_SAX_SAXEvent;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class TableViewBuilder {



    /**
     * This Constructor will build the TableView of the Company Overview by selecting certain instance variables
     * of the CompanyOverviewData Object inside the given DataContainerManager object and rendering them.
     * All of the key values are rendered in a formatted state.
     * @param dataContainerManager Object containing all data
     */
    public static TableView buildCompanyOverviewTableview(DataContainerManager dataContainerManager){
        TableView companyOverviewTableView  = new TableView();


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

        return companyOverviewTableView;

    }

    public static HBox  buildAnalysisTableViewBox (ArrayList<ArrayList<FinancialDataObject>> baseList){
        HBox tableViewBox = new HBox();

        for (ArrayList<FinancialDataObject> dataList :baseList){
        TableView analysisTableView = new TableView();

        TableColumn<FinancialDataObject, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));





            TableColumn<FinancialDataObject, Double> valueColumn = new TableColumn<>(dataList.get(0).getName());
            valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
            valueColumn.setCellFactory(column -> {
                return new TableCell<FinancialDataObject, Double>() {
                    @Override
                    protected void updateItem(Double value, boolean empty) {
                        super.updateItem(value, empty);

                        if (value == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {

                           setText(DataExtractor.formatNumbers(value));
                        }
                    }
                };

            });


            analysisTableView.getColumns().add(dateColumn);
            analysisTableView.getColumns().add(valueColumn);
            for (FinancialDataObject dataPoint : dataList){
                analysisTableView.getItems().add(dataPoint);
            }

            tableViewBox.getChildren().add(analysisTableView);
            analysisTableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        }








    tableViewBox.setAlignment(Pos.BASELINE_CENTER);
    return tableViewBox;
    }
}

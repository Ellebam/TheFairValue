package GUIBoxes;

import Controllers.DataContainerManager;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * This class manages all Analysis components embedded in all this objects Tabs.
 */
public class AnalysisTabPane extends TabPane {
    AnalysisTabPane analysisTabPane;
    DataContainerManager dataContainerManager;
    Tab overviewTab;
    Tab fundamentalTab;
    Tab equitiesTab;
    Tab evaluationTab;

    /**
     * Constructor for the main Analysis display. It will construct all the Tabs being the containers
     * for all display boxes and link them.
     * @param dataContainerManager Object containing all data
     */
    public AnalysisTabPane (DataContainerManager dataContainerManager){
        analysisTabPane = this;
        this.dataContainerManager = dataContainerManager;

        overviewTab = new Tab("Company Overview");
        fundamentalTab = new Tab("Fundamentals");
        equitiesTab = new Tab("Equities Analysis");
        evaluationTab = new Tab("Evaluation");

        analysisTabPane.getTabs().add(overviewTab);
        analysisTabPane.getTabs().add(fundamentalTab);
        analysisTabPane.getTabs().add(equitiesTab);
        analysisTabPane.getTabs().add(evaluationTab);
        analysisTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        analysisTabPane.setStyle("-fx-font-size:18;");


        CompanyOverviewHBox companyOverviewHBox = new CompanyOverviewHBox(dataContainerManager);
        FundamentalsVBox fundamentalsVBox = new FundamentalsVBox(dataContainerManager);
        EquitiesVBox equitiesVBox = new EquitiesVBox(dataContainerManager);
        EvaluationVBox evaluationVBox = new EvaluationVBox(dataContainerManager);


        overviewTab.setContent(companyOverviewHBox);
        fundamentalTab.setContent(fundamentalsVBox);
        equitiesTab.setContent(equitiesVBox);
        evaluationTab.setContent(evaluationVBox);

    }

}

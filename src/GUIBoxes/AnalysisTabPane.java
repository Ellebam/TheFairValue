package GUIBoxes;

import Controllers.DataContainerManager;
import Controllers.DataExtractor;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class AnalysisTabPane extends TabPane {
    AnalysisTabPane analysisTabPane;
    DataContainerManager dataContainerManager;
    Tab overviewTab;
    Tab fundamentalTab;
    Tab fairValueTab;
    Tab equitiesTab;
    Tab evaluationTab;

    public AnalysisTabPane (){
        analysisTabPane = this;

        overviewTab = new Tab("Company Overview");
        fundamentalTab = new Tab("Fundamentals");
        fairValueTab = new Tab("Fair Value");
        equitiesTab = new Tab("Equities Analysis");
        evaluationTab = new Tab("Evaluation");

        analysisTabPane.getTabs().add(overviewTab);
        analysisTabPane.getTabs().add(fundamentalTab);
        analysisTabPane.getTabs().add(fairValueTab);
        analysisTabPane.getTabs().add(equitiesTab);
        analysisTabPane.getTabs().add(evaluationTab);
        analysisTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        analysisTabPane.setStyle("-fx-font-size:18;");

    }

}

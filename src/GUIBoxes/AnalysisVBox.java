package GUIBoxes;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class AnalysisVBox extends VBox {
    AnalysisVBox analysisVBox;
    AnalysisTabPane analysisTabPane;
    String symbol;

    public AnalysisVBox(AnalysisTabPane analysisTabPane){
        analysisVBox = this;
        this.analysisTabPane = analysisTabPane;

        analysisVBox.getChildren().add(analysisTabPane);


    }
}

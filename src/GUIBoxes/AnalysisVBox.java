package GUIBoxes;


import Controllers.DataContainerManager;
import javafx.scene.layout.VBox;


/**
 * This Class represents the Analysis view which is built when the user commits a companies ticker symbol
 *
 */
public class AnalysisVBox extends VBox {
    AnalysisVBox analysisVBox;
    AnalysisTabPane analysisTabPane;


    /**
     * The AnalysisVBox is the container for the shown display of all Analysis Data. Its built by the
     * newly created AnalysisTabPane which needs to be initialized in this constructor
     * @param dataContainerManager Data used for Company Analysis
     */
    public AnalysisVBox(DataContainerManager dataContainerManager ){
        analysisVBox = this;
        AnalysisTabPane analysisTabPane = new AnalysisTabPane(dataContainerManager);
        this.analysisTabPane = analysisTabPane;

        analysisVBox.getChildren().add(analysisTabPane);


    }
}

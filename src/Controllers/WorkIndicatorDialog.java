package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.util.function.Consumer;
import java.util.function.ToIntFunction;

public class WorkIndicatorDialog<P> {

    private Task animationWorker;
    private Task<Integer> taskWorker;

    private final ProgressIndicator progressIndicator = new ProgressIndicator(ProgressIndicator.INDETERMINATE_PROGRESS);
    private final Stage dialog = new Stage(StageStyle.UNDECORATED);
    private final Label label = new Label();
    private final Group root = new Group();
    private final Scene scene = new Scene(root,330,120, Color.WHITE);
    private final BorderPane mainPane = new BorderPane();
    private final VBox vBox = new VBox();

    public ObservableList<Integer> resultNotifocationList = FXCollections.observableArrayList();

    public Integer resultValue;

    public WorkIndicatorDialog(Window owner, String label){
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(owner);
        dialog.setResizable(false);
        this.label.setText(label);
    }

    public void addTaskEndNotification(Consumer<Integer> consumer) {
        resultNotifocationList.addListener((ListChangeListener<? super Integer>) n -> {
            resultNotifocationList.clear();
            consumer.accept(resultValue);
        });
    }

    private void setupDialog(){
        root.getChildren().add(mainPane);
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinSize(330,120);
        vBox.getChildren().addAll(label, progressIndicator);
        mainPane.setTop(vBox);
        dialog.setScene(scene);

        dialog.setOnHidden(event->{ //insert result value, mainly observable list
             });
        dialog.show();
    }

    private void setupAnimationThread(){
        progressIndicator.setProgress(0);
        progressIndicator.progressProperty().unbind();
        progressIndicator.progressProperty().bind(animationWorker.progressProperty());

        new Thread(animationWorker).start();
    }

    private void setUpWorkerThread(P parameter, ToIntFunction<P> function){
        taskWorker = new Task<Integer>(){
            @Override
            public Integer call(){
                return function.applyAsInt(parameter);

            }
        };

        EventHandler<WorkerStateEvent> eventHandler = event ->{
            animationWorker.cancel(true);
            progressIndicator.progressProperty().unbind();
            dialog.close();
            try{
                resultValue = taskWorker.get();
                resultNotifocationList.add(resultValue);
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        };
        taskWorker.setOnSucceeded(eventHandler);
        taskWorker.setOnFailed(eventHandler);

        new Thread(taskWorker).start();
    }

    public Integer getResultValue(){
        return  resultValue;
    }
        public void exec(P parameter, ToIntFunction func){
        setupDialog();
        setupAnimationThread();
        setUpWorkerThread(parameter,func);
    }
}

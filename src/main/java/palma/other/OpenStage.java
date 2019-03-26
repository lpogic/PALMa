package palma.other;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class OpenStage implements PaneHost {
    private static final String fxmlPrePath = "/palma/";
    private static final String fxmlPostPath = ".fxml";

    private Stage stage;
    private StageHost stageHost;
    private Object id;
    private Map<String, OpenPane> panes;
    private Deque<OpenPane> runPanes;

    public OpenStage(StageHost stageHost, Object id){this(stageHost,id,new Stage());}

    public OpenStage(StageHost stageHost, Object id, Stage stage) {
        this.stageHost = stageHost;
        this.id = id;
        this.stage = stage;
        panes = new HashMap<>();
        runPanes = new ArrayDeque<>();
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private OpenPane loadPane(String key) {
        String fxmlPath = fxmlPrePath + key + fxmlPostPath;
        FXMLLoader loader = stageHost.getPaneLoader(fxmlPath);
        Pane pane;
        try{
            pane = loader.load();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd aplikacji");
            alert.setHeaderText("Błąd podczas ładowania widoku: " + key);
            alert.setContentText("Sprawdź resources");
            alert.showAndWait();
            throw new ExceptionInInitializerError("Pane load fail");
        }
        PaneController controller = loader.getController();
        controller.setHosts(stageHost,this);
        controller.init();
        return new OpenPane(pane,controller);
    }

    @Override
    public OpenPane openPane(String key){return openPane(key,true,true);}

    @Override
    public OpenPane openPane(String key, boolean read, boolean save){
        OpenPane paneData = read ? panes.get(key) : null;
        if(paneData == null){
            paneData = loadPane(key);
            if(save)panes.put(key,paneData);
        }
        return paneData;
    }

    @Override
    public void setMainPane(String key){setMainPane(openPane(key),true);}

    @Override
    public void setMainPane(OpenPane openPane, boolean stackRecord){
        if(stackRecord)runPanes.push(openPane);
        stage.setScene(new Scene(openPane.getPane()));
    }

    @Override
    public void popPane(){
        runPanes.pop();
        if(runPanes.isEmpty())stageHost.collectStage(id);
        else setMainPane(runPanes.peek(),false);
    }
}

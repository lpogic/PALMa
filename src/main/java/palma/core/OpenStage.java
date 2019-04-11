package palma.core;

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
    private static final String fxmlPrePath = "/palma/fxml/";
    private static final String fxmlPostPath = ".fxml";

    private Stage stage;
    private StageHost stageHost;
    private Object id;
    private Map<String, OpenPane> panes;
    private Deque<OpenPane> mainPaneStack;

    public OpenStage(StageHost stageHost, Object id){this(stageHost,id,new Stage());}

    public OpenStage(StageHost stageHost, Object id, Stage stage) {
        this.stageHost = stageHost;
        this.id = id;
        this.stage = stage;
        panes = new HashMap<>();
        mainPaneStack = new ArrayDeque<>();
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    private OpenPane loadPane(String key, PaneModel model) {
        String fxmlPath = fxmlPrePath + key + fxmlPostPath;
        FXMLLoader loader = new FXMLLoader(stageHost.getResource(fxmlPath));
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
        controller.init(model);
        return new OpenPane(pane,controller);
    }

    @Override
    public OpenPane openPane(String key){return openPane(key, null,true,true);}

    @Override
    public OpenPane openPane(String key, PaneModel model){
        return openPane(key, model, true, true);
    }

    @Override
    public OpenPane openPane(String key, boolean read, boolean save){
        return openPane(key, null, read, save);
    }

    @Override
    public OpenPane openPane(String key, PaneModel model, boolean read, boolean save){
        OpenPane paneData = read ? panes.get(key) : null;
        if(paneData == null){
            paneData = loadPane(key,model);
            if(save)panes.put(key,paneData);
        }
        return paneData;
    }

    @Override
    public void setMainPane(String key){setMainPane(openPane(key),true);}

    @Override
    public void setMainPane(OpenPane openPane, boolean stackRecord){
        if(stackRecord)mainPaneStack.push(openPane);
        stage.setScene(new Scene(openPane.getPane()));
    }

    @Override
    public void popMainPane(){
        mainPaneStack.pop();
        if(mainPaneStack.isEmpty())stageHost.collectStage(id);
        else setMainPane(mainPaneStack.peek(),false);
    }
}

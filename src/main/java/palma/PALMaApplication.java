package palma;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import palma.core.OpenStage;
import palma.core.StageHost;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PALMaApplication extends Application implements StageHost {

    private Map<Object, OpenStage> stages;
    private Stage primaryStage;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() {
        stages = new HashMap<>();
    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openStage(primaryStage).setMainPane("welcome");
        primaryStage.setTitle("PALMa");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    @Override
    public void stop() {

    }

    public OpenStage openStage(Object key){
        OpenStage openStage = stages.get(key);
        if(openStage != null)return openStage;
        openStage = key instanceof Stage ?
                new OpenStage(this,key,(Stage)key) :
                new OpenStage(this,key);
        stages.put(key,openStage);
        return openStage;
    }

    @Override
    public void collectStage(Object key) {
        OpenStage openStage = stages.remove(key);
        if(openStage != null) {
            if (openStage.getStage() == primaryStage) exitDialog();
            else openStage.getStage().close();
        }
    }

    @Override
    public URL getResource(String forPath){
        return getClass().getResource(forPath);
    }

    public void exitDialog(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Kończenie pracy");
        alert.setHeaderText("Czy napewno chcesz wyjść z programu?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            Platform.exit();
        }
    }

    public OpenStage popUpStage(String paneKey, Object ... bundle){
        OpenStage openStage = openStage(new Stage());
        openStage.openPane(paneKey).getController().open(bundle);
        openStage.setMainPane(paneKey);
        openStage.getStage().show();
        return openStage;
    }
}


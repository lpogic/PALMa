package palma.core.pane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import palma.core.scene.OpenScene;

import java.net.URL;

public class OpenPane extends OpenSceneScion {

    private Parent parent;
    private OpenController controller;

    public OpenPane(Object id, OpenScene openScene) {
        super(id,openScene);
    }

    public Parent getParent(){
        if(parent == null)load();
        return parent;
    }

    public void show(){
        scene().openPane(getId(),true);
        scene().show();
    }

    public void show(String fxml){
        load(fxml);
        show();
    }

    public void dress(){
        if(controller == null)load();
        controller.dress();
    }

    public void load(){
        if (getId() instanceof String) {
            load((String) getId());
        } else throw new NullPointerException("Cannot determine pane source");
    }

    public void load(String fxml) {
        URL fxmlUrl = root().getFxmlResource(fxml);
        System.out.println(fxmlUrl.getFile());
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent parent;
        try{
            parent = loader.load();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd aplikacji");
            alert.setHeaderText("Błąd podczas ładowania widoku");
            alert.setContentText("Sprawdź plik: " + fxmlUrl.getFile());
            alert.showAndWait();
            throw new ExceptionInInitializerError("Pane load fail");
        }
        OpenController controller = loader.getController();
        if(controller == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd aplikacji");
            alert.setHeaderText("Błąd podczas ładowania kontrolera");
            alert.setContentText("Sprawdź plik: " + fxmlUrl.getFile());
            alert.showAndWait();
            throw new ExceptionInInitializerError("Pane load fail");
        }
        this.parent = parent;
        controller.setLineage(this);
        controller.employ();
        this.controller = controller;
    }
}

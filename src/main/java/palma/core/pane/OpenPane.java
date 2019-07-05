package palma.core.pane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import palma.core.scene.OpenScene;

import java.net.URL;

/**
 * Rozszerzona klasa Parent. Umozliwia dostep do przodkow i potomkow, oraz pozwala zaladowac pliki fxml
 */
public class OpenPane extends OpenSceneScion {

    private Parent parent;
    private OpenController controller;

    public OpenPane(Object id, OpenScene openScene) {
        super(id,openScene);
    }

    /**
     * Wczytuje widok, jesli jeszcze nie zostal wczytany, odswieza kontroler i zwraca obiekt parent
     * @return Zaladowany obiekt Parent, gwarancja not-null
     */
    public Parent getParent(){
        if(parent == null)load();
        controller.dress();
        return parent;
    }

    /**
     * Wyswietla widok o nazwie zgodnej z id
     */
    public void show(){
        scene().openPane(getId(),true);
        scene().show();
    }

    /**
     * Wyswietla widok o podanej nazwie
     * @param fxml
     */
    public void show(String fxml){
        load(fxml);
        show();
    }

    /**
     * Warunkowo laduje widok, a nastepnie odswieza kontroler
     */
    public void dress(){
        if(controller == null)load();
        controller.dress();
    }

    /**
     * Laduje widok na postawie id
     */
    public void load(){
        if (getId() instanceof String) {
            load((String) getId());
        } else throw new NullPointerException("Cannot determine pane source");
    }

    /**
     * Laduje widok i inicjalizuje kontroler
     * @param fxml
     */
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
            e.printStackTrace();
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

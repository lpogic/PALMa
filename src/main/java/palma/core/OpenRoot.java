package palma.core;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import palma.core.shop.OpenDealer;
import palma.core.shop.Shop;
import palma.core.stage.OpenStage;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Szablon aplikacji. Tworzy i przechowuje wszystkie sceny. Udostepnia globalny sklep i zasoby
 */
public abstract class OpenRoot extends Application {
    public final String fxmlPrePath = "/palma/fxml/";
    public final String fxmlPostPath = ".fxml";

    private Map<Object, OpenStage> stages;
    private Shop shop;
    private Stage primaryStage;

    @Override
    public void init() {
        stages = new HashMap<>();
        shop = new Shop();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        employ(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Metoda inicjalizacyjna wywolywana automatycznie. W trakcie jej wywolania istnieje juz obiekt primaryStage oraz shop
     * @param primaryStage
     */
    public abstract void employ(Stage primaryStage);

    /**
     * Tworzy lub pobiera scene o podanym kluczu
     * @param key
     * @return
     */
    public OpenStage openStage(Object key){
        OpenStage openStage = stages.get(key);
        if(openStage == null) {
            openStage = key instanceof Stage ?
                    new OpenStage(key, this, (Stage) key) :
                    new OpenStage(key, this, new Stage());
            stages.put(key, openStage);
        }
        return openStage;
    }

    /**
     * Zamyka scene
     * @param key
     * @return
     */
    public boolean collectStage(Object key) {
        OpenStage openStage = stages.get(key);
        if(openStage != null) {
            if (openStage.getStage() == primaryStage){
                return exitDialog();
            }
            else openStage.getStage().close();
        }
        return true;
    }

    /**
     * Zwraca zasob dla podanej sciezki w postaci URL
     * @param forPath
     * @return
     */
    public URL getResource(String forPath){
        return getClass().getResource(forPath);
    }

    /**
     * Zwraca zasob fxml znajdujacy sie w domyslnym katalogu dla plikow fxml
     * @param fxml
     * @return
     */
    public URL getFxmlResource(String fxml){return getResource(fxmlPrePath + fxml + fxmlPostPath);}

    public boolean exitDialog(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konczenie pracy");
        alert.setHeaderText("Czy napewno chcesz wyjsc z programu?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            Platform.exit();
            return true;
        }
        return false;
    }

    /**
     * Wyswietla nowe okno z widokiem okreslonym w fxml
     * @param fxml
     */
    public void popUpStage(String fxml){
        openStage(new Stage()).openScene(fxml).show();
    }

    /**
     * Zwraca sklep
     * @return
     */
    public Shop getShop() {
        return shop;
    }

    /**
     * Inicjalizuje sprzedawcę
     * @param openDealer
     * @return
     */
    public OpenDealer openDealer(OpenDealer openDealer){
        openDealer.setRoot(this);
        openDealer.employ();
        return openDealer;
    }
}

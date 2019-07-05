package palma.core.scene;

import javafx.scene.Scene;
import palma.core.pane.OpenPane;
import palma.core.stage.OpenStage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Rozszerzona klasa Scene. Pozwala na dostep do rodowodu i przechowuje zaladowane widoki
 */
public class OpenScene extends OpenStageScion {

    private Scene scene;
    private Map<Object, OpenPane> loadedPanes;
    private Set<String> styles;
    private OpenPane primaryPane;

    public OpenScene(Object id, OpenStage openStage) {
        super(id, openStage);
        loadedPanes = new HashMap<>();
        styles = new HashSet<>();
    }

    /**
     * Laduje widok na podstawie id i wyswietla
     */
    public void show(){
        stage().openScene(getId(),true);
        stage().show();
    }

    /**
     * Przygotowuje obiekt i obiekty potomne do wyswietlenia
     */
    public void dress(){
        if(primaryPane == null)openPane(getId(),true);
        int i = 0;
        while (i != loadedPanes.size()) {
            i = loadedPanes.size();
            for (OpenPane openPane : loadedPanes.values()) {
                openPane.dress();
            }
        }
        if(scene == null){
            scene = new Scene(primaryPane.getParent());
            styles.forEach(s -> scene.getStylesheets().add(s));
        }
    }

    /**
     * Zwraca obiekt Scene
     * @return
     */
    public Scene getScene(){
        return scene;
    }

    /**
     * Zwraca stworzony lub pobrany obiekt OpenPane z id identycznym jak wlasny
     * @return
     */
    public OpenPane openPane(){
        return openPane(getId(),false);
    }

    /**
     * Zwraca stworzony lub pobrany obiekt OpenPane z wybranym id
     * @return
     */
    public OpenPane openPane(Object openPaneId){
        return openPane(openPaneId,false);
    }

    /**
     * Zwraca stworzony lub pobrany obiekt OpenPane z wybranym id i warunkowo ustwia go jako glowny
     * @return
     */
    public OpenPane openPane(Object openPaneId, boolean setAsPrimary){
        OpenPane openPane = loadedPanes.get(openPaneId);
        if(openPane == null){
            openPane = new OpenPane(openPaneId,this);
            loadedPanes.put(openPaneId,openPane);
        }
        if(setAsPrimary){
            primaryPane = openPane;
            scene = null;
        }
        return openPane;
    }

    /**
     * Dodaje karte stylu do listy kart stylu
     * @param stylePath
     */
    public void openStyle(String stylePath){
        if(scene != null){
            scene.getStylesheets().add(stylePath);
        }
        styles.add(stylePath);
    }

    /**
     * Dodaje karte stylu do listy kart stylu nie konczac zdania
     * @param stylePath
     */
    public OpenScene openStyleAnd(String stylePath){
        openStyle(stylePath);
        return this;
    }
}

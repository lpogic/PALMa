package palma.core.stage;

import javafx.application.Platform;
import javafx.stage.Stage;
import palma.core.OpenRoot;
import palma.core.scene.OpenScene;

import java.util.*;

/**
 * Rozszerzona klasa Stage. Oferuje dostep do rodowodu. Przechowuje historie scen. Pozwala na tworzenie prostych
 * kolejek watkow oczekujacych na zamkniecie okna.
 */
public class OpenStage extends OpenRootScion {

    private Stage stage;
    private OpenScene primaryScene;
    private Map<Object, OpenScene> loadedScenes;
    private Stack<OpenScene> pastScenes;
    private Deque<Object> hideTickets;

    public OpenStage(Object id, OpenRoot openRoot, Stage stage) {
        super(id, openRoot);
        this.stage = stage;
        loadedScenes = new HashMap<>();
        pastScenes = new Stack<>();
        hideTickets = new ArrayDeque<>();
        stage.setOnHiding((we)->{
            while (hideTickets.size() > 0){
                Platform.exitNestedEventLoop(hideTickets.remove(),null);
            }
        });
    }

    /**
     * Zwraca obiekt stage
     * @return
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Odswieza potomkow i wyswietla, jesli niewidoczny lub przenosi na wierzch jesli widoczny
     */
    public void show(){
        if(primaryScene == null)openScene(getId(),true);
        primaryScene.dress();
        stage.setScene(primaryScene.getScene());

        if (stage.isShowing())
            stage.toFront();
        else stage.show();
    }

    /**
     * Wyswietla scene i ustawia watek w kolejce zamkniecia
     */
    public void showAndWait(){
        show();
        Object o = new Object();
        hideTickets.addFirst(o);
        Platform.enterNestedEventLoop(o);
    }

    /**
     * Zamyka scene i usowa z pamieci
     */
    public void close(){
        root().collectStage(getId());
    }

    /**
     * Otwiera scene z domyslnym id
     * @return
     */
    public OpenScene openScene(){return openScene(getId(),false);}

    /**
     * Otwiera scene z podanym id
     * @param openSceneId
     * @return
     */
    public OpenScene openScene(Object openSceneId){
        return openScene(openSceneId,false);
    }

    /**
     * Otwiera scene z podanym id i warunkowo ustawia ja jako glowna
     * @param openSceneId
     * @param setAsPrimary
     * @return
     */
    public OpenScene openScene(Object openSceneId, boolean setAsPrimary){
        OpenScene openScene = loadedScenes.get(openSceneId);
        if(openScene == null){
            openScene = new OpenScene(openSceneId,this);
            loadedScenes.put(openSceneId,openScene);
        }
        if(setAsPrimary){
            if(primaryScene != null && openScene != primaryScene)
                pastScenes.push(primaryScene);
            primaryScene = openScene;
        }
        return openScene;
    }

    /**
     * Umozliwia powrot do poprzedniej sceny lub zamkniecie gdy historia scen jest pusta
     */
    public void popOpenScene(){
        if(pastScenes.isEmpty()){
            root().collectStage(getId());
        } else {
            primaryScene = pastScenes.pop();
            openScene(primaryScene.getId(),true);
        }
    }
}

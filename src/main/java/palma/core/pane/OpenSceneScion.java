package palma.core.pane;

import palma.core.OpenRoot;
import palma.core.scene.OpenScene;
import palma.core.shop.Shop;
import palma.core.stage.OpenStage;

/**
 * Potomek OpenScene
 */
public abstract class OpenSceneScion {

    private Object id;
    private OpenScene scene;

    OpenSceneScion(Object id, OpenScene openScene) {
        this(id);
        setLineage(openScene);
    }

    OpenSceneScion(Object id) {
        this.id = id;
    }

    void setLineage(OpenScene openScene){
        this.scene = openScene;
    }

    public Object getId() {
        return id;
    }

    protected void setId(Object id) {
        this.id = id;
    }

    public OpenRoot root() {
        return scene.root();
    }

    public OpenStage stage(){
        return scene.stage();
    }

    public OpenScene scene(){
        return scene;
    }

    protected Shop shop(){return root().getShop();}

}

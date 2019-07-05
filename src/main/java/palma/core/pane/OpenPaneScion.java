package palma.core.pane;

import palma.core.OpenRoot;
import palma.core.scene.OpenScene;
import palma.core.shop.Shop;
import palma.core.stage.OpenStage;

/**
 * Potomek OpenPane
 */
public abstract class OpenPaneScion {

    private OpenPane pane;

    OpenPaneScion(OpenPane openPane) {
        this();
        setLineage(openPane);
    }

    OpenPaneScion() {}

    void setLineage(OpenPane openPane){this.pane = openPane;}

    public OpenRoot root() {
        return pane.root();
    }

    public OpenStage stage(){
        return pane.stage();
    }

    public OpenScene scene(){
        return pane.scene();
    }

    public OpenPane pane(){
        return pane;
    }

    protected Shop shop(){return root().getShop();}
}

package palma.core.scene;

import palma.core.OpenRoot;
import palma.core.shop.Shop;
import palma.core.stage.OpenStage;

public abstract class OpenStageScion {

    private Object id;
    private OpenStage stage;

    OpenStageScion(Object id, OpenStage openStage) {
        this(id);
        setLineage(openStage);
    }

    OpenStageScion(Object id) {
        this.id = id;
    }

    void setLineage(OpenStage openStage){
        this.stage = openStage;
    }

    public Object getId() {
        return id;
    }

    protected void setId(Object id) {
        this.id = id;
    }

    public OpenRoot root() {
        return stage.root();
    }

    public OpenStage stage(){
        return stage;
    }

    protected Shop shop() {
        return root().getShop();
    }

}

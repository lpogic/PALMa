package palma.core.shop;

import palma.core.OpenRoot;

public abstract class OpenDealer {

    private OpenRoot openRoot;

    public OpenDealer(OpenRoot openRoot) {
        this.openRoot = openRoot;
    }

    public abstract void employ();

    protected OpenRoot root(){
        return openRoot;
    }

    protected Shop shop(){
        return openRoot.getShop();
    }
}

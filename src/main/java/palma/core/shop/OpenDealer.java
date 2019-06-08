package palma.core.shop;

import palma.core.OpenRoot;

public abstract class OpenDealer implements Dealer {

    private OpenRoot openRoot;

    public OpenDealer() {
    }

    public OpenDealer(OpenRoot openRoot) {
        this.openRoot = openRoot;
    }

    public void employ(){
        employ(openRoot.getShop());
    }

    protected OpenRoot root(){
        return openRoot;
    }

    public void setRoot(
            OpenRoot openRoot){this.openRoot = openRoot;
    }

    public Shop shop(){
        return openRoot.getShop();
    }
}

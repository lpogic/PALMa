package palma.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import palma.core.pane.OpenController;
import palma.core.shop.contract.Contract;
import palma.dealer.LogicConnectionDesignDealer;

public class LogicConnectionDesignController extends OpenController {

    public static final Contract<Object> update = Contract.forObject();

    @FXML
    private VBox box;

    @Override
    protected void employ() {
        shop().offer(update,()->{
            box.getChildren().setAll(shop().deal(LogicConnectionDesignDealer.getConnectionRows));
            return null;
        });
    }

    @Override
    protected void dress() {
        shop().deal(update);
    }


}

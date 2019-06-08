package palma.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import palma.core.pane.OpenController;
import palma.dealer.LogicFunctionDesignDealer;

public class LogicFunctionDesignController extends OpenController {

    @FXML
    private FlowPane flow;

    @Override
    protected void employ() {

    }

    @Override
    protected void dress() {
        flow.getChildren().setAll(shop().deal(LogicFunctionDesignDealer.getFunctionButtons));
    }
}

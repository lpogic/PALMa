package palma.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import palma.core.pane.OpenController;
import palma.core.shop.contract.Contract;
import palma.dealer.LogicConnectionDesignDealer;
import palma.dealer.LogicDesignDealer;

public class LogicConnectionDesignController extends OpenController {

    public static final Contract<Boolean> update = Contract.forService();

    @FXML
    private VBox leftBox;

    @FXML
    private VBox rightBox;

    @FXML
    private VBox centerBox;

    @FXML
    private Label label;

    @Override
    protected void employ() {
        shop().offer(update,()->{
            leftBox.getChildren().setAll(shop().deal(LogicConnectionDesignDealer.getInputRows));
            rightBox.getChildren().setAll(shop().deal(LogicConnectionDesignDealer.getOutputRows));
            label.setText(shop().deal(LogicDesignDealer.selectedDevice).getId());
            return true;
        });
    }

    @Override
    protected void dress() {
        shop().deal(update);
    }


}

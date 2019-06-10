package palma.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import palma.core.pane.OpenController;
import palma.core.shop.contract.Contract;
import palma.dealer.LogicDeviceEditorDealer;
import palma.dealer.LogicDesignDealer;

public class LogicDeviceEditorController extends OpenController {

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
            leftBox.getChildren().setAll(shop().deal(LogicDeviceEditorDealer.getInputRows));
            centerBox.getChildren().setAll(shop().deal(LogicDeviceEditorDealer.getParameterRows));
            rightBox.getChildren().setAll(shop().deal(LogicDeviceEditorDealer.getOutputRows));
            label.setText(shop().deal(LogicDesignDealer.selectedDevice).getName());
            return true;
        });
    }

    @Override
    protected void dress() {
        shop().deal(update);
    }


}

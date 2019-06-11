package palma.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import palma.core.pane.OpenController;
import palma.core.shop.contract.Contract;
import palma.dealer.LogicDeviceEditorDealer;
import palma.dealer.LogicDesignDealer;
import palma.model.logic.builder.device.DeviceAdapter;

import java.util.Optional;

public class LogicDeviceEditorController extends OpenController {

    public static final Contract<Boolean> update = Contract.forService();


    @FXML
    private VBox leftBox;

    @FXML
    private VBox centerBox;

    @FXML
    private VBox rightBox;

    @FXML
    private Label name;

    @FXML
    private Label type;

    private DeviceAdapter device;

    @Override
    protected void employ() {
        shop().offer(update,()->{
            leftBox.getChildren().setAll(shop().deal(LogicDeviceEditorDealer.getInputRows));
            centerBox.getChildren().setAll(shop().deal(LogicDeviceEditorDealer.getParameterRows));
            rightBox.getChildren().setAll(shop().deal(LogicDeviceEditorDealer.getOutputRows));
            name.setText(device.getName());
            type.setText(device.getDefaultName());
            return true;
        });
    }

    @Override
    protected void dress() {
        device = shop().deal(LogicDesignDealer.selectedDevice);
        shop().deal(update);
    }

    @FXML
    void changeName() {
        TextInputDialog dialog = new TextInputDialog(name.getText());
        dialog.setTitle(name.getText());
        dialog.setHeaderText("Zmiana nazwy urzadzenia " + name.getText());
        dialog.setContentText("Prosze wprowadzic nowa nazwe:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(n -> {
            device.setName(n);
            shop().deal(update);
            shop().deal(LogicDesignController.updateLeftPane);
        });
    }

    @FXML
    void delete() {
        if(shop().deal(LogicDesignDealer.deleteSelected)) {
            shop().deal(LogicDesignController.setCenterDevicePicker);
        }
    }

}

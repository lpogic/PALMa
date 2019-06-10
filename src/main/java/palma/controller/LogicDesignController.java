package palma.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import palma.core.pane.OpenController;
import palma.core.shop.contract.Contract;
import palma.dealer.LogicDesignDealer;

public class LogicDesignController extends OpenController {

    public static final Contract<Boolean> updateLeftPane = Contract.forService();
    public static final Contract<Boolean> setCenterEditor = Contract.forService();
    public static final Contract<Boolean> setCenterDevicePicker = Contract.forService();
    public static final Contract<Boolean> setCenterFunctionPicker = Contract.forService();

    @FXML
    private BorderPane border;

    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane titledDevicePane;

    @FXML
    private TitledPane titledFunctionPane;

    @FXML
    private VBox devices;

    @FXML
    private VBox functions;


    @Override
    public void employ(){
        shop().offer(updateLeftPane, ()->{
            devices.getChildren().setAll(shop().deal(LogicDesignDealer.getDeviceButtons));
            functions.getChildren().setAll(shop().deal(LogicDesignDealer.getFunctionButtons));
            return true;
        });

        shop().offer(setCenterEditor, ()->{
            border.setCenter(scene().openPane("logic-device-editor").getParent());
            return true;
        });

        shop().offer(setCenterDevicePicker, this::setCenterDevicePicker);
        shop().offer(setCenterFunctionPicker, this::setCenterFunctionPicker);

        scene().openPane("logic-device-editor").load();
        scene().openPane("logic-device-picker").load();
    }

    @Override
    protected void dress() {
        shop().deal(updateLeftPane);
    }

    @FXML
    public boolean setCenterDevicePicker(){
        shop().deal(LogicDevicePickerController.setFlowDevices);
        border.setCenter(scene().openPane("logic-device-picker").getParent());
        accordion.setExpandedPane(titledDevicePane);
        return true;
    }

    @FXML
    public boolean setCenterFunctionPicker(){
        shop().deal(LogicDevicePickerController.setFlowFunctions);
        border.setCenter(scene().openPane("logic-device-picker").getParent());
        accordion.setExpandedPane(titledFunctionPane);
        return true;
    }
}

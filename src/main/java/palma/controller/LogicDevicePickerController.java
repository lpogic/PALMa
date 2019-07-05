package palma.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import palma.core.pane.OpenController;
import palma.core.shop.contract.Contract;
import palma.dealer.LogicDevicePickerDealer;

/**
 * Kontroler ekranu wyboru urzadzenia. Domyslnie jest czescia ekranu projektowania logiki
 */
public class LogicDevicePickerController extends OpenController {

    public static final Contract<Boolean> setFlowDevices = Contract.forService();
    public static final Contract<Boolean> setFlowFunctions = Contract.forService();

    @FXML
    private FlowPane flow;

    @Override
    protected void employ() {
        shop().offer(setFlowDevices,this::setFlowDevices);
        shop().offer(setFlowFunctions,this::setFlowFunctions);
    }

    @Override
    protected void dress() {

    }

    public boolean setFlowDevices(){
        flow.getChildren().setAll(shop().deal(LogicDevicePickerDealer.getDeviceButtons));
        return true;
    }

    public boolean setFlowFunctions(){
        flow.getChildren().setAll(shop().deal(LogicDevicePickerDealer.getFunctionButtons));
        return true;
    }
}

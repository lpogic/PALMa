package palma.dealer;

import javafx.scene.control.Button;
import palma.controller.LogicDesignController;
import palma.core.shop.OpenDealer;
import palma.core.shop.Shop;
import palma.core.shop.contract.Contract;
import palma.core.shop.contract.stamp.Stamp;
import palma.model.logic.builder.*;

import java.util.ArrayList;
import java.util.List;

public class LogicDesignDealer extends OpenDealer {

    public static final Contract<DeviceAdapter> selectedDevice = Contract.forObjectOf(DeviceAdapter.class);

    public static final Contract<Boolean> addCreatedToList = Contract.forService();
    public static final Contract<DeviceAdapterCase> getDevices = Contract.forObjectOf(DeviceAdapterCase.class);
    public static final Contract<List<Button>> getDeviceButtons = Contract.forListOf(Button.class);
    public static final Contract<List<Button>> getFunctionButtons = Contract.forListOf(Button.class);

    private DeviceAdapterCase devices;

    public LogicDesignDealer() {
        devices = new DeviceAdapterCase();
    }

    @Override
    public void employ(Shop shop) {

        shop.deliver(getDevices,devices);
        shop.offer(getDeviceButtons,()->getDeviceButtons(true));
        shop.offer(getFunctionButtons,()->getDeviceButtons(false));

        shop.offer(addCreatedToList,()->{
            if(shop().order(LogicFunctionDesignDealer.createdDevice)){
                devices.add(shop().deal(LogicFunctionDesignDealer.createdDevice));
                return shop().deal(LogicDesignController.updateLeftPane);
            }
            return false;
        });
    }

    private List<Button> getDeviceButtons(boolean graphical){
        List<Button> buttons = new ArrayList<>();

        for(DeviceAdapter it : devices){
            if(it.isGraphical() == graphical) {
                Button button = new Button(it.getId());
                button.setPrefWidth(137.0);
                button.setOnAction(e -> {
                    shop().deliver(selectedDevice, it);
                    shop().deal(LogicDesignController.setCenterConnections);
                });
                buttons.add(button);
            }
        }

        return buttons;
    }

}

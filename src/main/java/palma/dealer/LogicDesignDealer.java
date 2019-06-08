package palma.dealer;

import javafx.scene.control.Button;
import palma.controller.LogicConnectionDesignController;
import palma.controller.LogicDesignController;
import palma.core.OpenRoot;
import palma.core.shop.OpenDealer;
import palma.core.shop.Shop;
import palma.core.shop.contract.Contract;
import palma.core.shop.contract.stamp.Stamp;
import palma.model.logic.builder.*;

import java.util.ArrayList;
import java.util.List;

public class LogicDesignDealer extends OpenDealer {

    public static final Contract<DeviceAdapter> selectedDevice = Contract.forObjectOf(DeviceAdapter.class, Stamp.WARRANTY);

    public static final Contract<Object> addCreatedToList = Contract.forObject();
    public static final Contract<DeviceAdapterCase> getDevices = Contract.forClass(DeviceAdapterCase.class, Stamp.WARRANTY);
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
                System.out.println("Device added");
                System.out.println(devices.size());
                shop().deal(LogicDesignController.updateLeftPane);
            }
            return null;
        });
    }

    private List<Button> getDeviceButtons(boolean graphical){
        List<Button> buttons = new ArrayList<>();

        for(DeviceAdapter it : devices){
            if(it.isGraphical() == graphical) {
                Button button = new Button(it.getLabel());
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

package palma.dealer;

import javafx.scene.control.Button;
import palma.core.shop.OpenDealer;
import palma.core.shop.Shop;
import palma.core.shop.contract.Contract;
import palma.model.logic.builder.*;
import palma.model.logic.builder.device.AndGateDevice;
import palma.model.logic.builder.device.ButtonDevice;
import palma.model.logic.builder.device.DeviceAdapter;
import palma.model.logic.builder.device.DeviceAdapterCase;

import java.util.ArrayList;
import java.util.List;

public class LogicDevicePickerDealer extends OpenDealer {

    public static final Contract<List<Button>> getDeviceButtons = Contract.forListOf(Button.class);
    public static final Contract<List<Button>> getFunctionButtons = Contract.forListOf(Button.class);
    public static final Contract<DeviceAdapter> createdDevice = Contract.forObjectOf(DeviceAdapter.class);

    public LogicDevicePickerDealer() {

    }

    @Override
    public void employ(Shop shop) {
        shop.offer(getDeviceButtons, this::getDeviceButtons);
        shop.offer(getFunctionButtons, this::getFunctionButtons);
    }

    private List<Button> getDeviceButtons(){
        List<Button> buttons = new ArrayList<>();

        Button buttonButton = new Button(ButtonDevice.defaultName);
        buttonButton.setOnAction(e->{
            ButtonDevice buttonDevice = new ButtonDevice();
            buttonDevice.setName(IdProvider.getFreeDeviceName(shop().deal(LogicDesignDealer.getDevices),ButtonDevice.defaultName));
            shop().deliver(LogicDesignDealer.selectedDevice, buttonDevice);
            shop().deal(LogicDesignDealer.addSelected);
        });
        buttons.add(buttonButton);

        return buttons;
    }

    private List<Button> getFunctionButtons(){
        List<Button> buttons = new ArrayList<>();

        Button andGateButton = new Button(AndGateDevice.defaultName);
        andGateButton.setOnAction(e->{
            AndGateDevice andGateDevice = new AndGateDevice();
            andGateDevice.setName(IdProvider.getFreeDeviceName(shop().deal(LogicDesignDealer.getDevices),AndGateDevice.defaultName));
            shop().deliver(LogicDesignDealer.selectedDevice, andGateDevice);
            shop().deal(LogicDesignDealer.addSelected);
        });
        buttons.add(andGateButton);

        return buttons;
    }
}

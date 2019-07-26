package palma.dealer;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.util.Pair;
import palma.controller.FXMLController;
import palma.core.shop.OpenDealer;
import palma.core.shop.Shop;
import palma.core.shop.contract.Contract;
import palma.model.graphic.LogicGraphicConnected;
import palma.model.graphic.MyButton;
import palma.model.graphic.MyCircle;
import palma.model.graphic.SelectableNode;
import palma.model.logic.builder.*;
import palma.model.logic.builder.device.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handlarz wyboru urzadzenia
 */
public class LogicDevicePickerDealer extends OpenDealer {

    /** Zwroc przyciski urzadzen */
    public static final Contract<List<Button>> getDeviceButtons = Contract.forListOf(Button.class);
    /** Zwroc przyciski funkcji */
    public static final Contract<List<Button>> getFunctionButtons = Contract.forListOf(Button.class);

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
            MyButton button = new MyButton(20.0f, 20.0f);
            button.setOnMouseDragged(s -> {
                button.setLayoutX(s.getSceneX() - 70);
                button.setLayoutY(s.getSceneY() - 45);
            });
            button.defaultName = buttonDevice.getName();
            FXMLController.referenceGrafikaPublic.getChildren().add(button);

            LogicGraphicConnected.listDeviceConnected.add(new Pair<SelectableNode, DeviceAdapter>(button,buttonDevice));
        });
        buttons.add(buttonButton);

        Button lampButton = new Button(LampDevice.defaultName);
        lampButton.setOnAction(e->{
            LampDevice lampDevice = new LampDevice();
            lampDevice.setName(IdProvider.getFreeDeviceName(shop().deal(LogicDesignDealer.getDevices),LampDevice.defaultName));
            shop().deliver(LogicDesignDealer.selectedDevice, lampDevice);
            shop().deal(LogicDesignDealer.addSelected);

            MyCircle circle = new MyCircle(30.0f, 30.0f, 10.f);
            circle.setOnMouseDragged(s -> {
                circle.setLayoutX(s.getSceneX() - 90);
                circle.setLayoutY(s.getSceneY() - 60);
            });
            circle.defaultName = lampDevice.getName();
            FXMLController.referenceGrafikaPublic.getChildren().add(circle);

            LogicGraphicConnected.listDeviceConnected.add(new Pair<SelectableNode, DeviceAdapter>(circle,lampDevice));
        });
        buttons.add(lampButton);

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

        Button orGateButton = new Button(OrGateDevice.defaultName);
        orGateButton.setOnAction(e->{
            OrGateDevice orGateDevice = new OrGateDevice();
            orGateDevice.setName(IdProvider.getFreeDeviceName(shop().deal(LogicDesignDealer.getDevices),OrGateDevice.defaultName));
            shop().deliver(LogicDesignDealer.selectedDevice, orGateDevice);
            shop().deal(LogicDesignDealer.addSelected);
        });
        buttons.add(orGateButton);

        Button notGateButton = new Button(NotGateDevice.defaultName);
        notGateButton.setOnAction(e->{
            NotGateDevice notGateDevice = new NotGateDevice();
            notGateDevice.setName(IdProvider.getFreeDeviceName(shop().deal(LogicDesignDealer.getDevices),NotGateDevice.defaultName));
            shop().deliver(LogicDesignDealer.selectedDevice, notGateDevice);
            shop().deal(LogicDesignDealer.addSelected);
        });
        buttons.add(notGateButton);

        Button tonButton = new Button(OnDelayTimerDevice.defaultName);
        tonButton.setOnAction(e->{
            OnDelayTimerDevice tonDevice = new OnDelayTimerDevice();
            tonDevice.setName(IdProvider.getFreeDeviceName(shop().deal(LogicDesignDealer.getDevices),OnDelayTimerDevice.defaultName));
            shop().deliver(LogicDesignDealer.selectedDevice, tonDevice);
            shop().deal(LogicDesignDealer.addSelected);
        });
        buttons.add(tonButton);

        return buttons;
    }
}

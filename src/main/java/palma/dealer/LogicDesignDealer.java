package palma.dealer;

import javafx.scene.control.Button;
import palma.controller.LogicDesignController;
import palma.core.shop.OpenDealer;
import palma.core.shop.Shop;
import palma.core.shop.contract.Contract;
import palma.model.logic.builder.device.DeviceAdapter;
import palma.model.logic.builder.device.DeviceAdapterCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Handlarz projektu logiki
 */
public class LogicDesignDealer extends OpenDealer {

    /** Wybrane urzadzenie */
    public static final Contract<DeviceAdapter> selectedDevice = Contract.forObjectOf(DeviceAdapter.class);

    /** Dodaj wybrane do listy urzadzen */
    public static final Contract<Boolean> addSelected = Contract.forService();
    /** Usun wybrane z listy urzadzen */
    public static final Contract<Boolean> deleteSelected = Contract.forService();
    /** Zwroc urzadzenia */
    public static final Contract<DeviceAdapterCase> getDevices = Contract.forObjectOf(DeviceAdapterCase.class);
    /** Zwroc przyciski dla urzadzen */
    public static final Contract<List<Button>> getDeviceButtons = Contract.forListOf(Button.class);
    /** Zwroc przyciski dla funkcji */
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

        shop.offer(addSelected,()->{
            if(shop().order(selectedDevice)){
                if(devices.add(shop().deal(selectedDevice)))
                    return shop().deal(LogicDesignController.updateLeftPane);
            }
            return false;
        });

        shop.offer(deleteSelected,()->{
            if(shop().order(selectedDevice)){
                if(devices.remove(shop().deal(selectedDevice)))
                    return shop().deal(LogicDesignController.updateLeftPane);
            }
            return false;
        });
    }

    private List<Button> getDeviceButtons(boolean graphical){
        List<Button> buttons = new ArrayList<>();

        for(DeviceAdapter it : devices){
            if(it.isGraphical() == graphical) {
                Button button = new Button(it.getName());
                button.setPrefWidth(147.0);
                button.setOnAction(e -> {
                    shop().deliver(selectedDevice, it);
                    shop().deal(LogicDesignController.setCenterEditor);
                });
                buttons.add(button);
            }
        }

        return buttons;
    }

}

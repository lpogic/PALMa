package palma.dealer;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import palma.core.OpenRoot;
import palma.core.shop.OpenDealer;
import palma.core.shop.Shop;
import palma.core.shop.contract.Contract;
import palma.model.logic.builder.*;

import java.util.ArrayList;
import java.util.List;

public class LogicFunctionDesignDealer extends OpenDealer {

    public static final Contract<List<Button>> getFunctionButtons = Contract.forListOf(Button.class);
    public static final Contract<DeviceAdapter> createdDevice = Contract.forObjectOf(DeviceAdapter.class);

    public LogicFunctionDesignDealer() {

    }

    @Override
    public void employ(Shop shop) {
        shop.offer(getFunctionButtons, this::getFunctionButtons);
    }

    private List<Button> getFunctionButtons(){
        List<Button> buttons = new ArrayList<>();

        Button andGateButton = new Button(AndGateDevice.label);
        andGateButton.setOnAction(e->{
            shop().deliver(createdDevice, new AndGateDevice());
            shop().deal(LogicDesignDealer.addCreatedToList);
        });
        buttons.add(andGateButton);

        return buttons;
    }
}

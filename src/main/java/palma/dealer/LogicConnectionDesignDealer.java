package palma.dealer;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import palma.controller.LogicConnectionDesignController;
import palma.core.shop.OpenDealer;
import palma.core.shop.Shop;
import palma.core.shop.contract.Contract;
import palma.model.logic.builder.*;

import java.util.ArrayList;
import java.util.List;

public class LogicConnectionDesignDealer extends OpenDealer {

    public static final Contract<List<HBox>> getConnectionRows = Contract.forListOf(HBox.class);

    private DeviceAdapterCase devices;

    @Override
    public void employ(Shop shop) {
        shop.offer(getConnectionRows,()->{
            devices = shop().deal(LogicDesignDealer.getDevices, devices);
            return getConnectionRows(shop().deal(LogicDesignDealer.selectedDevice));
        });
    }

    public List<HBox> getConnectionRows(DeviceAdapter device){
        List<HBox> box = new ArrayList<>();

        for(Input it : device.getInputs()){
            box.add(createInputRow(it));
        }

        for(Output it : device.getOutputs()){
            box.add(createOutputRow(it));
        }

        return box;
    }

    private HBox createInputRow(Input input){
        Label label = new Label(input.getName() + "  <--");
        label.setFont(Font.font("System", FontWeight.EXTRA_BOLD, 18));

        ComboBox<DeviceAdapter> deviceCombo = new ComboBox<>();
        ComboBox<Output> pinCombo = new ComboBox<>();

        deviceCombo.getItems().setAll(devices);
        deviceCombo.getItems().add(null);

        //defaults
        if(input.getOutput() != null){
            deviceCombo.getSelectionModel().select(input.getOutput().getOwner());
            pinCombo.getItems().setAll(input.getOutput().getOwner().getOutputs());
            pinCombo.getSelectionModel().select(input.getOutput());
        } else pinCombo.setDisable(true);

        deviceCombo.getSelectionModel().selectedItemProperty().addListener((e,o,n)->{
            pinCombo.getSelectionModel().select(null);
            if(n != null)pinCombo.getItems().setAll(n.getOutputs());
            pinCombo.setDisable(n == null);
        });
        pinCombo.getSelectionModel().selectedItemProperty().addListener((e,o,n)->{
            input.connect(n);
            shop().deal(LogicConnectionDesignController.update);
        });

        return new HBox(label, deviceCombo, pinCombo);
    }

    private HBox createOutputRow(Output output){
        Label label = new Label(output.getName() + "  -->");
        label.setFont(Font.font("System", FontWeight.EXTRA_BOLD, 18));

        VBox inputs = new VBox();
        for(Input it : output.getInputs()){
            ComboBox<DeviceAdapter> deviceCombo = new ComboBox<>();
            ComboBox<Input> pinCombo = new ComboBox<>();

            pinCombo.getItems().setAll(it.getOwner().getInputs());
            pinCombo.getSelectionModel().select(it);
            pinCombo.getSelectionModel().selectedItemProperty().addListener((e,o,n)->{
                o.connect(null);
                n.connect(output);
                shop().deal(LogicConnectionDesignController.update);
            });
            deviceCombo.getItems().setAll(devices.getBy(d->!d.getInputs().isEmpty()));
            deviceCombo.getSelectionModel().select(it.getOwner());
            deviceCombo.getSelectionModel().selectedItemProperty().addListener((e,o,n)->{
                it.connect(null);
                if(n != null)pinCombo.getItems().setAll(n.getInputs());
                pinCombo.setDisable(n == null);
            });
            inputs.getChildren().add(new HBox(deviceCombo, pinCombo));
        }
        ComboBox<DeviceAdapter> deviceCombo = new ComboBox<>();
        ComboBox<Input> pinCombo = new ComboBox<>();

        pinCombo.getSelectionModel().selectedItemProperty().addListener((e,o,n)->{
            n.connect(output);
            shop().deal(LogicConnectionDesignController.update);
        });
        deviceCombo.getItems().setAll(devices.getBy(d->!d.getInputs().isEmpty()));
        deviceCombo.getSelectionModel().selectedItemProperty().addListener((e,o,n)->{
            if(n != null)pinCombo.getItems().setAll(n.getInputs());
            pinCombo.setDisable(n == null);
        });
        inputs.getChildren().add(new HBox(deviceCombo, pinCombo));

        return new HBox(label, inputs);
    }
}

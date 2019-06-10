package palma.dealer;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import palma.controller.LogicDeviceEditorController;
import palma.core.shop.OpenDealer;
import palma.core.shop.Shop;
import palma.core.shop.contract.Contract;
import palma.model.logic.builder.*;
import palma.model.logic.builder.device.DeviceAdapter;
import palma.model.logic.builder.device.DeviceAdapterCase;

import java.util.List;
import java.util.stream.Collectors;

public class LogicDeviceEditorDealer extends OpenDealer {

    public static final Contract<List<GridPane>> getInputRows = Contract.forListOf(GridPane.class);
    public static final Contract<List<GridPane>> getOutputRows = Contract.forListOf(GridPane.class);
    public static final Contract<List<GridPane>> getParameterRows = Contract.forListOf(GridPane.class);

    private DeviceAdapterCase devices;

    @Override
    public void employ(Shop shop) {
        shop.offer(getInputRows,()->{
            devices = shop().deal(LogicDesignDealer.getDevices, devices);
            return getInputRows(shop().deal(LogicDesignDealer.selectedDevice));
        });

        shop.offer(getOutputRows,()->{
            devices = shop().deal(LogicDesignDealer.getDevices, devices);
            return getOutputRows(shop().deal(LogicDesignDealer.selectedDevice));
        });

        shop.offer(getParameterRows,()->{
            devices = shop().deal(LogicDesignDealer.getDevices, devices);
            return getParameterRows(shop().deal(LogicDesignDealer.selectedDevice));
        });
    }

    private List<GridPane> getInputRows(DeviceAdapter device){
        return device.getInputs().stream().map(this::createInputRow).collect(Collectors.toList());
    }

    private List<GridPane> getOutputRows(DeviceAdapter device){
        return device.getOutputs().stream().map(this::createOutputRow).collect(Collectors.toList());
    }

    private List<GridPane> getParameterRows(DeviceAdapter device){
        return device.getParameters().stream().map(this::createParameterRow).collect(Collectors.toList());
    }

    private GridPane createInputRow(Input input){
        Label label = new Label("--> " + input.getName());
        label.setFont(Font.font("System", FontWeight.BOLD, 16));

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
            shop().deal(LogicDeviceEditorController.update);
        });

        GridPane grid = new GridPane();
        grid.add(new HBox(deviceCombo, pinCombo),0,0);
        grid.add(label,1,0);
        return grid;
    }

    private GridPane createOutputRow(Output output){
        Label label = new Label(output.getName() + "  -->");
        label.setFont(Font.font("System", FontWeight.BOLD, 16));

        VBox inputs = new VBox();
        for(Input it : output.getInputs()){
            ComboBox<DeviceAdapter> deviceCombo = new ComboBox<>();
            ComboBox<Input> pinCombo = new ComboBox<>();

            pinCombo.getItems().setAll(it.getOwner().getInputs());
            pinCombo.getSelectionModel().select(it);
            pinCombo.getSelectionModel().selectedItemProperty().addListener((e,o,n)->{
                o.connect(null);
                n.connect(output);
                shop().deal(LogicDeviceEditorController.update);
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
            shop().deal(LogicDeviceEditorController.update);
        });
        deviceCombo.getItems().setAll(devices.getBy(d->!d.getInputs().isEmpty()));
        deviceCombo.getSelectionModel().selectedItemProperty().addListener((e,o,n)->{
            if(n != null)pinCombo.getItems().setAll(n.getInputs());
            pinCombo.setDisable(n == null);
        });
        inputs.getChildren().add(new HBox(deviceCombo, pinCombo));

        GridPane grid = new GridPane();
        grid.add(label,0,0);
        grid.add(inputs,1,0);
        return grid;
    }

    private GridPane createParameterRow(Parameter parameter){
        Label label = new Label(parameter.getName());

        TextField field = new TextField(parameter.getValue());

        field.textProperty().addListener((e,o,n)->{
            parameter.setValue(n);
        });

        GridPane grid = new GridPane();
        grid.add(label,0,0);
        grid.add(field,0,1);
        return grid;
    }
}

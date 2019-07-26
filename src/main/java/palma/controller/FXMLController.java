package palma.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.util.Pair;
import org.w3c.dom.events.Event;
import palma.core.pane.OpenController;
import palma.dealer.LogicDesignDealer;
import palma.model.graphic.*;
import palma.model.logic.builder.IdProvider;
import palma.model.logic.builder.device.ButtonDevice;
import palma.model.logic.builder.device.DeviceAdapter;
import palma.model.logic.builder.device.LampDevice;

import java.net.URL;
import java.util.ResourceBundle;


public class FXMLController extends OpenController {

    private int degree = 0;
    private int factor = 1;
    private int factor_1 = 1;

    @FXML
    BorderPane borderPane;

    @FXML
    public AnchorPane widokGrafiki;
    public static AnchorPane referenceGrafikaPublic;

    public Button obrot;

    public Button skalowanieP;

    public Button skalowanieZ;

    public ColorPicker colorWybor;

    public Node selectedButton;

    public TextField nazwa;
    public static TextField referenceNazwaPublic;


    public TextField id;
    public static TextField referenceIDPublic;

    protected static ModuleLayer.Controller instance;

    @FXML
    public void zapiszProjekt(MouseEvent event)  {

    }
    @FXML
    public void wczytajProjekt(MouseEvent event)  {

    }


    @Override
    protected void employ() {
        SelectionHandler selectionHandler = new SelectionHandler(widokGrafiki);
        widokGrafiki.addEventHandler(MouseEvent.MOUSE_PRESSED, selectionHandler.getMousePressedEventHandler());
        FXMLController.referenceNazwaPublic = nazwa;
        FXMLController.referenceIDPublic = id;
        FXMLController.referenceGrafikaPublic = widokGrafiki;
    }

    @Override
    protected void dress() {

    }

    public void setOnDragDetected(MouseEvent event) {
        MyButton button = new MyButton(20.0f, 20.0f);
        button.setOnMouseDragged(e -> {
            button.setLayoutX(e.getSceneX() - 70);
            button.setLayoutY(e.getSceneY() - 45);

    });
        ButtonDevice buttonDevice = new ButtonDevice();
        buttonDevice.setName(IdProvider.getFreeDeviceName(shop().deal(LogicDesignDealer.getDevices),ButtonDevice.defaultName));
        shop().deliver(LogicDesignDealer.selectedDevice, buttonDevice);
        shop().deal(LogicDesignDealer.addSelected);
        button.defaultName = buttonDevice.getName();
        widokGrafiki.getChildren().add(button);
        LogicGraphicConnected.listDeviceConnected.add(new Pair<SelectableNode, DeviceAdapter>(button,buttonDevice));
}

    public void setOnDragDetected_1(MouseEvent event) {

        MyCircle circle = new MyCircle(30.0f, 30.0f, 10.f);
        circle.setOnMouseDragged(e -> {
            circle.setLayoutX(e.getSceneX() - 90);
            circle.setLayoutY(e.getSceneY() - 60);
        });
        LampDevice lampDevice = new LampDevice();
        lampDevice.setName(IdProvider.getFreeDeviceName(shop().deal(LogicDesignDealer.getDevices),LampDevice.defaultName));
        shop().deliver(LogicDesignDealer.selectedDevice, lampDevice);
        shop().deal(LogicDesignDealer.addSelected);
        circle.defaultName = lampDevice.getName();
        widokGrafiki.getChildren().add(circle);
        LogicGraphicConnected.listDeviceConnected.add(new Pair<SelectableNode, DeviceAdapter>(circle,lampDevice));

    }

    public void actionClickObrot(MouseEvent mouseEvent) {

        for (SelectableNode but : SelectionHandler.Clipboard.getSelectedItems()) {
            selectedButton = (Node) but;
            if (mouseEvent.getSource() == obrot) {
                degree = (int) selectedButton.getRotate() + 15;
                selectedButton.setRotate(degree);
            }
            if (mouseEvent.getSource() == skalowanieP) {
                factor = (int) selectedButton.getScaleX() * 2;
                factor = (int) selectedButton.getScaleY() * 2;
                selectedButton.setScaleX(factor);
                selectedButton.setScaleY(factor);
            }
            if (mouseEvent.getSource() == skalowanieZ) {
                factor_1 = (int) selectedButton.getScaleX() / 2;
                factor_1 = (int) selectedButton.getScaleY() / 2;
                selectedButton.setScaleX(factor_1);
                selectedButton.setScaleY(factor_1);
            }
        }
    }

    public void changeColor(ActionEvent event) {
        for (SelectableNode but : SelectionHandler.Clipboard.getSelectedItems()) {
            //     System.console().printf(String.valueOf(but.equals(MyButton.class)));
            if(but instanceof MyButton)
            ((MyButton) but).setFill(Paint.valueOf(colorWybor.getValue().toString()));
            else
                ((MyCircle) but).setFill(Paint.valueOf(colorWybor.getValue().toString()));
        }
    }
}
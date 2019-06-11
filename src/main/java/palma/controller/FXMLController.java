package palma.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import palma.core.pane.OpenController;
import palma.model.graphic.MyButton;
import palma.model.graphic.MyCircle;
import palma.model.graphic.SelectableNode;
import palma.model.graphic.SelectionHandler;

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

    public Button obrot;

    public Button skalowanieP;

    public Button skalowanieZ;

    public ColorPicker colorWybor;

    public Node selectedButton;


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
    }

    @Override
    protected void dress() {

    }

/*
    public void setOnDragDetected(MouseEvent event) {

        ToggleButton button = new ToggleButton("                       ");
        button.setOnMouseDragged(e -> {
            button.setLayoutX(e.getSceneX() - 20);
            button.setLayoutY(e.getSceneY() - 60);


        });

        widokGrafiki.getChildren().add(button);
    }
*/
    public void setOnDragDetected(MouseEvent event) {
        MyButton button = new MyButton(20.0f, 20.0f);
        button.setOnMouseDragged(e -> {
            button.setLayoutX(e.getSceneX() - 70);
            button.setLayoutY(e.getSceneY() - 45);

    });

    widokGrafiki.getChildren().add(button);
}


    public void setOnDragDetected_1(MouseEvent event) {

        MyCircle circle = new MyCircle(30.0f, 30.0f, 10.f);
        circle.setOnMouseDragged(e -> {
            circle.setLayoutX(e.getSceneX() - 90);
            circle.setLayoutY(e.getSceneY() - 60);
        });

        widokGrafiki.getChildren().add(circle);

    }

    public void actionClickObrot(MouseEvent mouseEvent) {

        for (SelectableNode but : SelectionHandler.Clipboard.getSelectedItems()) {
          /*  if(but instanceof ToggleButton)
                if(((ToggleButton) but).isSelected())
                    selectedButton = (ToggleButton) but;*/
                    selectedButton =(Node) but;
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

 /*   private Stage getStage (Event event){
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    private Node getNode (Event event){
        return (Node) event.getSource();
    }
*/
}
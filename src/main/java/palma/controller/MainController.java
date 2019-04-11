package palma.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import palma.core.PaneController;
import palma.core.PaneModel;


public class MainController extends PaneController {

    @FXML
    private BorderPane backgroundPane;

    @Override
    public void init(PaneModel model){
        getPaneHost().getStage().setTitle("PALMa - Nowy projekt");
        getPaneHost().openPane("logic-design");
    }

    @FXML
    void graphicDesignAction(ActionEvent event) {
        backgroundPane.setCenter(getPaneHost().openPane("graphic-design").getPane());
    }

    @FXML
    void logicDesignAction(ActionEvent event) {
        backgroundPane.setCenter(getPaneHost().openPane("logic-design").getPane());
    }
}

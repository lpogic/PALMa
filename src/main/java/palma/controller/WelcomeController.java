package palma.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import palma.core.PaneController;
import palma.core.PaneModel;

public class WelcomeController extends PaneController {

    @FXML
    void create(ActionEvent event) {
        getPaneHost().setMainPane("main");
    }

    @FXML
    void exit(ActionEvent event) {
        getPaneHost().popMainPane();
    }

    @FXML
    void open(ActionEvent event) {
        getStageHost().popUpStage("welcome");
    }

}

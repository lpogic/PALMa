package palma.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import palma.other.PaneController;

public class WelcomeController extends PaneController {

    @FXML
    void create(ActionEvent event) {
        getPaneHost().setMainPane("main");
    }

    @FXML
    void exit(ActionEvent event) {
        getPaneHost().popPane();
    }

    @FXML
    void open(ActionEvent event) {
        getStageHost().popUpStage("welcome");
    }

}

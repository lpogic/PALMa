package palma.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import palma.core.pane.OpenController;

/**
 * Kontroler widoku powitalnego
 */
public class WelcomeController extends OpenController {

    @Override
    protected void employ() {

    }

    @Override
    protected void dress() {

    }

    @FXML
    void create(ActionEvent event) {
        stage().openScene("main").show();
    }

    @FXML
    void exit(ActionEvent event) {
        stage().popOpenScene();
    }

    @FXML
    void open(ActionEvent event) {

        root().popUpStage("welcome");
    }

}

package palma.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import palma.core.pane.OpenController;
import palma.dealer.LogicCompilationDealer;

public class LogicCompilationErrorController extends OpenController {

    @FXML
    private VBox content;

    @Override
    protected void employ() {

    }

    @Override
    protected void dress() {
        content.getChildren().setAll(shop().deal(LogicCompilationDealer.getExceptionButtons));
    }
}

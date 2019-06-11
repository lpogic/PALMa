package palma.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import palma.core.pane.OpenController;
import palma.dealer.LogicDesignDealer;
import palma.dealer.XMLDealer;
import palma.model.logic.writer.LXMLWriter;


public class MainController extends OpenController {

    @FXML
    private BorderPane backgroundPane;

    @Override
    public void employ(){
        stage().getStage().setTitle("PALMa - Nowy projekt");
        backgroundPane.setCenter(scene().openPane("FXML").getParent());
    }

    @Override
    protected void dress() {

    }

    @FXML
    void graphicDesignAction(ActionEvent event) {
        backgroundPane.setCenter(scene().openPane("FXML").getParent());
    }

    @FXML
    void logicDesignAction(ActionEvent event) {
        backgroundPane.setCenter(scene().openPane("logic-design").getParent());
    }

    @FXML
    void exportXml(){
        shop().deal(XMLDealer.exportXml);
    }

}

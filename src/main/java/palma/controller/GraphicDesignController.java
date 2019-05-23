package palma.controller;

import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import palma.core.pane.OpenController;
import palma.model.graphic.EdytorGraficzny;

import javax.swing.*;

public class GraphicDesignController extends OpenController {

    @FXML
    private SwingNode swingNode;

    private EdytorGraficzny edytorGraficzny;

    @Override
    public void employ(){
        edytorGraficzny = new EdytorGraficzny();
        swingNode.setContent((JPanel)edytorGraficzny.getContentPane());
    }

    @Override
    protected void dress() {
        swingNode.requestFocus();
        edytorGraficzny.getContentPane().requestFocus();
    }
}

package palma.other;

import javafx.scene.layout.Pane;

public class OpenPane {
    private Pane pane;
    private PaneController controller;

    public OpenPane(Pane pane, PaneController controller) {
        this.pane = pane;
        this.controller = controller;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public PaneController getController() {
        return controller;
    }

    public void setController(PaneController controller) {
        this.controller = controller;
    }
}

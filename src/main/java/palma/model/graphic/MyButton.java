package palma.model.graphic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import palma.controller.FXMLController;
import palma.dealer.LogicDesignDealer;
import palma.model.logic.builder.IdProvider;
import palma.model.logic.builder.device.ButtonDevice;

public class MyButton extends Rectangle implements SelectableNode {

    public String defaultName = "Button";
    public String ID = "null";

    public MyButton(double centerX, double centerY) {
        super(centerX, centerY);
        this.setStrokeWidth(3);
    }

    @Override
    public boolean requestSelection(boolean select) {
        return true;
    }

    @Override
    public void notifySelection(boolean select) {
        if(select)
        {
            this.setStroke(Color.WHITESMOKE);
        }
        else
            this.setStroke(Color.TRANSPARENT);
    }
}

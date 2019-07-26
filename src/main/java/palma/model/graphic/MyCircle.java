package palma.model.graphic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import palma.controller.FXMLController;

public class MyCircle extends Circle implements SelectableNode {

    public static String defaultName = "Lampa";
    public String ID = "null";
    public MyCircle(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius);
        this.setStrokeWidth(3);
    }

    @Override
    public boolean requestSelection(boolean select) {
        return true;
    }

    @Override
    public void notifySelection(boolean select) {
        if(select) {
            this.setStroke(Color.WHITESMOKE);
        }
        else
            this.setStroke(Color.TRANSPARENT);
    }
}
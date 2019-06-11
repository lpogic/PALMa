package palma.model.graphic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MyCircle extends Circle implements SelectableNode {
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
        if(select)
            this.setStroke(Color.WHITE);
        else
            this.setStroke(Color.BLACK);
    }
}
package palma.model.graphic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MyButton extends Rectangle implements SelectableNode {
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
            this.setStroke(Color.WHITE);
        else
            this.setStroke(Color.BLACK);
    }
}

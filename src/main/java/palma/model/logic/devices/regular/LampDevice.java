package palma.model.logic.devices.regular;

import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import palma.model.logic.Signal;

public class LampDevice extends RegularDevice {
    private Signal input;
    private ObjectProperty<Paint> fill;

    public LampDevice(Signal input, ObjectProperty<Paint> fill) {
        this.input = input;
        this.fill = fill;
    }

    @Override
    public void dusk() {
        fill.setValue(Color.web(input.isHigh() ? "red" : "green"));
    }

    @Override
    public boolean isEvening() {
        return true;
    }
}

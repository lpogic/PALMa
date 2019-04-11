package palma.model.logic.devices.regular;

import javafx.beans.property.ReadOnlyBooleanProperty;
import palma.model.logic.Signal;

public class ButtonDevice extends RegularDevice{
    private Signal output;
    private ReadOnlyBooleanProperty clicked;

    public ButtonDevice(Signal output, ReadOnlyBooleanProperty clicked) {
        this.output = output;
        this.clicked = clicked;
    }

    @Override
    public void dawn() {
        output.setState(clicked.get());

    }

    @Override
    public boolean isMorning() {
        return true;
    }
}

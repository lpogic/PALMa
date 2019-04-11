package palma.model.logic.functions;

import palma.model.logic.Signal;
import palma.model.logic.State;

public class RisingEdgeDetector implements Function {
    private Signal input;
    private Signal output;
    private State lastState;

    public RisingEdgeDetector(Signal input, Signal output) {
        this.input = input;
        this.output = output;
        lastState = State.UNDEFINED;
    }

    @Override
    public boolean execute() {
        if(input.isDefined()) {
            output.setState(lastState == State.LOW && input.isHigh());
            lastState = input.getState();
            return true;
        } else return false;
    }
}

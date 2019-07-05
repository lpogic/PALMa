package palma.model.logic;

/**
 * Sygnal ustawiany przez wyjscie i odczytywany przez wejscie. Poza stanami niskim i wysokim,
 * moze miec stan niezdefiniowany.
 */
public class Signal {
    private State state;

    public Signal() {
    }

    public State getState() {
        return state;
    }

    public void setState(boolean stateHigh) {
        state = stateHigh ? State.HIGH : State.LOW;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isDefined(){
        return state != State.UNDEFINED;
    }

    public boolean isHigh(){
        return state == State.HIGH;
    }

    public boolean isLow(){
        return state == State.LOW;
    }

    public void reset(){
        state = State.UNDEFINED;
    }
}

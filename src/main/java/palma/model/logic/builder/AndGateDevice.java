package palma.model.logic.builder;

public class AndGateDevice extends DeviceAdapter {

    public static final String label = "And Gate";

    private String name;

    public AndGateDevice() {
        super();
        name = label;
        getInputs().add(new Input(this,"In1"));
        getInputs().add(new Input(this, "In2"));
        getOutputs().add(new Output(this, "Out"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isGraphical() {
        return false;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return name;
    }
}

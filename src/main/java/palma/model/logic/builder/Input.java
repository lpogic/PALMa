package palma.model.logic.builder;

public class Input {

    private DeviceAdapter owner;
    private String name;
    private Output output;

    public Input(DeviceAdapter owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public DeviceAdapter getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Output getOutput() {
        return output;
    }

    public void connect(Output output) {
        if(this.output != null)this.output.getInputs().remove(this);
        if(output != null)output.getInputs().add(this);
        this.output = output;
    }

    @Override
    public String toString() {
        return name;
    }
}

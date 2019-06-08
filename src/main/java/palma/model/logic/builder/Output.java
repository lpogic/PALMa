package palma.model.logic.builder;

import java.util.HashSet;
import java.util.Set;

public class Output {

    private DeviceAdapter owner;
    private String name;
    private String signalName;
    private Set<Input> inputs;

    public Output(DeviceAdapter owner, String name) {
        this.owner = owner;
        this.name = name;
        inputs = new HashSet<>();
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

    public String getSignalName() {
        return signalName;
    }

    public void setSignalName(String signalName) {
        this.signalName = signalName;
    }

    public Set<Input> getInputs() {
        return inputs;
    }

    @Override
    public String toString() {
        return name;
    }
}

package palma.model.logic.builder;

import palma.model.logic.builder.device.DeviceAdapter;
import palma.model.logic.writer.XMLNode;
import palma.model.logic.writer.XMLizable;

/**
 * Reprezentuje wejscie urzadzenia
 */
public class Input implements XMLizable {

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

    @Override
    public XMLNode toXmlNode() {
        XMLNode node = new XMLNode("input");
        node.add("name",name);
        node.add("id", getOutput().getId());
        return node;
    }
}

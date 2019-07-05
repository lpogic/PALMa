package palma.model.logic.builder;

import palma.model.logic.builder.device.DeviceAdapter;
import palma.model.logic.writer.XMLNode;
import palma.model.logic.writer.XMLizable;

import java.util.HashSet;
import java.util.Set;

/**
 * Reprezentuje wyjscie urzadzenia
 */
public class Output implements XMLizable {

    private DeviceAdapter owner;
    private String name;
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Input> getInputs() {
        return inputs;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public XMLNode toXmlNode() {
        XMLNode node = new XMLNode("output");
        node.add("name",name);
        node.add("id", id);
        return node;
    }

}

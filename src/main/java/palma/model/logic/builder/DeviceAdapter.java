package palma.model.logic.builder;

import palma.model.logic.writer.XMLNode;
import palma.model.logic.writer.XMLizable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class DeviceAdapter implements XMLizable {

    private String id;
    private List<Input> inputs;
    private List<Output> outputs;

    public DeviceAdapter() {
        this("");
    }

    public DeviceAdapter(String id) {
        this.id = id;
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public List<Output> getOutputs() {
        return outputs;
    }

    public boolean isGraphical() {
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceAdapter adapter = (DeviceAdapter) o;
        return id.equals(adapter.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public XMLNode toXmlNode() {
        XMLNode node = new XMLNode(isGraphical() ? "device" : "function");
        node.add("id", id);
        node.getChildren().addAll(getInputs());
        node.getChildren().addAll(getOutputs());
        return node;
    }
}

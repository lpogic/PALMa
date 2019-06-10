package palma.model.logic.builder.device;

import palma.model.logic.builder.Input;
import palma.model.logic.builder.Output;
import palma.model.logic.writer.XMLNode;

public class AndGateDevice extends DeviceAdapter {

    public static final String defaultName = "And Gate";

    public AndGateDevice() {
        super();
        getInputs().add(new Input(this,"In1"));
        getInputs().add(new Input(this, "In2"));
        getOutputs().add(new Output(this, "Out"));
    }

    @Override
    public boolean isGraphical() {
        return false;
    }

    @Override
    public XMLNode toXmlNode() {
        XMLNode node = super.toXmlNode();
        node.add("class", "and");
        return node;
    }
}

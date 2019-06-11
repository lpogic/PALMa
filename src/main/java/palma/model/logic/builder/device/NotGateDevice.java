package palma.model.logic.builder.device;

import palma.model.logic.builder.Input;
import palma.model.logic.builder.Output;
import palma.model.logic.writer.XMLNode;

public class NotGateDevice extends DeviceAdapter {

    public static final String defaultName = "Not Gate";

    public NotGateDevice() {
        super();
        getInputs().add(new Input(this,"In"));
        getOutputs().add(new Output(this, "Out"));
    }

    @Override
    public String getDefaultName() {
        return defaultName;
    }

    @Override
    public boolean isGraphical() {
        return false;
    }

    @Override
    public XMLNode toXmlNode() {
        XMLNode node = super.toXmlNode();
        node.add("class", "not");
        return node;
    }
}

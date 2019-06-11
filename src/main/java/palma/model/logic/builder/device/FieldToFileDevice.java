package palma.model.logic.builder.device;

import palma.model.logic.builder.Input;
import palma.model.logic.builder.device.DeviceAdapter;
import palma.model.logic.writer.XMLNode;

public class FieldToFileDevice extends DeviceAdapter {

    public static final String defaultName = "Field->File";

    public FieldToFileDevice() {
        getInputs().add(new Input(this, "write"));
    }

    @Override
    public String getDefaultName() {
        return defaultName;
    }

    @Override
    public boolean isGraphical() {
        return super.isGraphical();
    }

    @Override
    public XMLNode toXmlNode() {
        XMLNode node = super.toXmlNode();
        node.add("type","file");
        node.add("class","field->file");
        return node;
    }
}

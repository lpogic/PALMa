package palma.model.logic.builder;

import palma.model.logic.writer.XMLNode;

public class ButtonDevice extends DeviceAdapter {

    public ButtonDevice() {
        super();
        getOutputs().add(new Output(this, "clicked"));
    }

    @Override
    public boolean isGraphical() {
        return true;
    }

    @Override
    public XMLNode toXmlNode() {
        XMLNode node = super.toXmlNode();
        node.add("type", "fxml");
        node.add("class", "button");
        return node;
    }
}

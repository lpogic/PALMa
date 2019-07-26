package palma.model.logic.builder.device;

import palma.model.graphic.LogicGraphicConnected;
import palma.model.graphic.MyButton;
import palma.model.logic.builder.Output;
import palma.model.logic.builder.Parameter;
import palma.model.logic.builder.validate.SizedStringValidator;
import palma.model.logic.writer.XMLNode;

public class ButtonDevice extends DeviceAdapter {

    public static final String defaultName = "Button";

    public ButtonDevice() {
        super();
        getOutputs().add(new Output(this, "clicked"));
        getParameters().add(new Parameter(this, new SizedStringValidator(1,20),"Identyfikator", ""));
    }

    @Override
    public String getDefaultName() {
        return defaultName;
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
        node.add("id", getParameters().getFirstByName("Identyfikator").getValue());
        ((MyButton)LogicGraphicConnected.getObjectWithValue(this).getKey()).ID = (getParameters().getFirstByName("Identyfikator").getValue());
        return node;
    }
}

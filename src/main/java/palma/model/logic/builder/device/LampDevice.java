package palma.model.logic.builder.device;

import palma.model.graphic.LogicGraphicConnected;
import palma.model.graphic.MyCircle;
import palma.model.logic.builder.Input;
import palma.model.logic.builder.Output;
import palma.model.logic.builder.Parameter;
import palma.model.logic.builder.validate.SizedStringValidator;
import palma.model.logic.writer.XMLNode;

public class LampDevice extends DeviceAdapter {

    public static final String defaultName = "Lamp";

    public LampDevice() {
        super();
        getInputs().add(new Input(this, "light"));
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
        node.add("class", "lamp");
        node.add("id", getParameters().getFirstByName("Identyfikator").getValue());
        ((MyCircle) LogicGraphicConnected.getObjectWithValue(this).getKey()).ID = (getParameters().getFirstByName("Identyfikator").getValue());

        return node;
    }
}

package palma.model.logic.builder.device;

import palma.model.logic.builder.Input;
import palma.model.logic.builder.Output;
import palma.model.logic.builder.Parameter;
import palma.model.logic.builder.validate.BoundedIntegerValidator;
import palma.model.logic.builder.validate.SizedStringValidator;
import palma.model.logic.writer.XMLNode;

public class OnDelayTimerDevice extends DeviceAdapter {

    public static final String defaultName = "On Delay Timer";

    public OnDelayTimerDevice() {
        getInputs().add(new Input(this, "trigger"));
        getOutputs().add(new Output(this, "delayed"));
        getParameters().add(new Parameter(this, new BoundedIntegerValidator(Integer.MAX_VALUE,0),"Opoznienie[ms]", "1000"));
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
        node.add("class","ton");
        node.add("period",getParameters().getFirstByName("Opoznienie[ms]").getValue());
        return node;
    }
}

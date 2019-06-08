package palma.model.logic.builder;

import java.util.ArrayList;
import java.util.List;

public class DeviceAdapter {

    private List<Input> inputs;
    private List<Output> outputs;
    private boolean graphical;

    public DeviceAdapter() {
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
        return graphical;
    }

    public String getLabel(){
        return "Adapter";
    }
}

package palma.model.logic.builder;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    public static List<Input> getInvalidInputs(DeviceAdapterCase devices){
        List<Input> invalid = new ArrayList<>();
        for(DeviceAdapter it : devices){
            for(Input input : it.getInputs()){
                if(input.getOwner() == null || input.getOutput() == null)invalid.add(input);
            }
        }
        return invalid;
    }

    public static List<Output> getInvalidOutputs(DeviceAdapterCase devices){
        List<Output> invalid = new ArrayList<>();
        for(DeviceAdapter it : devices){
            for(Output output : it.getOutputs()){
                if(output.getOwner() == null || output.getId() == null || output.getId().isBlank())invalid.add(output);
            }
        }
        return invalid;
    }
}

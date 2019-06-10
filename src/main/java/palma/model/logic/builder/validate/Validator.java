package palma.model.logic.builder.validate;

import palma.model.logic.builder.Input;
import palma.model.logic.builder.Output;
import palma.model.logic.builder.Parameter;
import palma.model.logic.builder.device.DeviceAdapter;
import palma.model.logic.builder.device.DeviceAdapterCase;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    public static void validateAll(DeviceAdapterCase devices)throws ValidationException{
        ValidationException exception = new ValidationException();
        exception.getErrors().addAll(getDeviceErrors(devices));
        exception.getErrors().addAll(getParameterErrors(devices));
        exception.getErrors().addAll(getInputErrors(devices));
        exception.getErrors().addAll(getOutputErrors(devices));
        if(!exception.getErrors().isEmpty())throw exception;
    }

    private static List<ValidationError> getParameterErrors(DeviceAdapterCase devices){
        List<ValidationError> errors = new ArrayList<>();
        for(DeviceAdapter it : devices){
            for(Parameter parameter : it.getParameters()){
                if(parameter.getOwner() == null)errors.add(new ValidationError(parameter, parameter.getName() + ": nie przypisane do urządzenia"));
                else errors.addAll(parameter.selfValidation());
            }
        }
        return errors;
    }

    private static List<ValidationError> getInputErrors(DeviceAdapterCase devices){
        List<ValidationError> errors = new ArrayList<>();
        for(DeviceAdapter it : devices){
            for(Input input : it.getInputs()){
                if(input.getOwner() == null)errors.add(new ValidationError(input, input.getName() + ": nie przypisane do urzadzenia"));
                else if(input.getOutput() == null)
                    errors.add(new ValidationError(input, input.getOwner().getName() + ":" + input.getName() + ": nie podlaczone do wyjscia"));
            }
        }
        return errors;
    }

    private static List<ValidationError> getOutputErrors(DeviceAdapterCase devices){
        List<ValidationError> errors = new ArrayList<>();
        for(DeviceAdapter it : devices){
            for(Output output : it.getOutputs()){
                if(output.getOwner() == null)errors.add(new ValidationError(output, output.getName() + ": nie przypisane do urządzenia"));
                else if(output.getId() == null || output.getId().isBlank())
                    errors.add(new ValidationError(output, output.getOwner().getName() + ":" + output.getName() +  ": nie posiada Id"));
            }
        }
        return errors;
    }

    private static List<ValidationError> getDeviceErrors(DeviceAdapterCase devices){
        List<ValidationError> errors = new ArrayList<>();
        for(DeviceAdapter it : devices){

        }
        return errors;
    }
}

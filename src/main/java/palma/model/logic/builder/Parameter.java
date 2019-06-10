package palma.model.logic.builder;

import palma.model.logic.builder.device.DeviceAdapter;
import palma.model.logic.builder.validate.ParameterValidator;
import palma.model.logic.builder.validate.ValidationError;

import java.util.List;

public class Parameter {

    private DeviceAdapter owner;
    private ParameterValidator validator;
    private String name;
    private String value;

    public Parameter(DeviceAdapter owner, ParameterValidator validator, String name, String initialValue) {
        this.owner = owner;
        this.validator = validator;
        this.name = name;
        this.value = initialValue;
    }

    public DeviceAdapter getOwner() {
        return owner;
    }

    public ParameterValidator getValidator() {
        return validator;
    }

    public List<ValidationError> selfValidation(){
        return validator.validate(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

package palma.model.logic.builder.validate;

import palma.model.logic.builder.Parameter;

import java.util.List;

public interface ParameterValidator {
    List<ValidationError> validate(Parameter parameter);
}

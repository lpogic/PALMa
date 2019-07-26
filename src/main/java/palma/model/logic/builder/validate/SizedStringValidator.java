package palma.model.logic.builder.validate;

import palma.model.logic.builder.Parameter;

import java.util.ArrayList;
import java.util.List;

public class SizedStringValidator implements ParameterValidator {

    private int minSize;
    private int maxSize;

    public SizedStringValidator(int minSize, int maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    @Override
    public List<ValidationError> validate(Parameter parameter) {
        List<ValidationError> errors = new ArrayList<>();
        if (parameter.getValue() == null) errors.add(new ValidationError(parameter, parameter.getOwner() + ":"
                + parameter.getName() + ": wartosc nie moze byc pusta"));
        else {
            if (parameter.getValue().length() < minSize)
                errors.add(new ValidationError(parameter, parameter.getOwner() + ":"
                        + parameter.getName() + ": tekst nie powinien byc krotszy niz " + minSize));
            if (parameter.getValue().length() > maxSize)
                errors.add(new ValidationError(parameter, parameter.getOwner() + ":"
                        + parameter.getName() + ": tekst nie powinien byc dluzszy niz " + maxSize));
        }

        return errors;
    }
}

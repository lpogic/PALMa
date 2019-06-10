package palma.model.logic.builder.validate;


import palma.model.logic.builder.Parameter;

import java.util.ArrayList;
import java.util.List;

public class BoundedIntegerValidator implements ParameterValidator {

    private int upperBound;
    private int lowerBound;

    public BoundedIntegerValidator(int upperBound, int lowerBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }

    @Override
    public List<ValidationError> validate(Parameter parameter) {
        List<ValidationError> errors = new ArrayList<>();
        try{
            int value = Integer.parseInt(parameter.getValue());
            if(value > upperBound)errors.add(new ValidationError(parameter,parameter.getOwner() + ":"
                    + parameter.getName() + ": wartosc powinna byc nie wieksza niz " + upperBound));
            if(value < lowerBound)errors.add(new ValidationError(parameter,parameter.getOwner() + ":"
                    + parameter.getName() + ": wartosc powinna byc nie mniejsza niz " + lowerBound));
        }catch (Exception ex){
            errors.add(new ValidationError(parameter, parameter.getOwner() + ":"
                    + parameter.getName() + ": wartosc powinna byc typu calkowitego"));
        }
        return errors;
    }
}

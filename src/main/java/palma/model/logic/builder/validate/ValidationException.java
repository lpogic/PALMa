package palma.model.logic.builder.validate;

import java.util.ArrayList;
import java.util.List;

/**
 * Wyjatek nieudanej walidacji
 */
public class ValidationException extends Exception {

    private List<ValidationError> errors;

    public ValidationException() {
        errors = new ArrayList<>();
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    @Override
    public String getMessage() {
        StringBuilder strb = new StringBuilder();
        for(ValidationError it : errors){
            strb.append(it.toString()).append("\n");
        }
        return strb.toString();
    }

    public String getCompactMessage(){
        StringBuilder strb = new StringBuilder();
        int i = 1;
        for(ValidationError it : errors){
            strb.append(i).append(". ").append(it.toString()).append("\n");
            if(++i > 8){
                strb.append("I jeszcze ").append(errors.size() - 8).append(" innych bledow...");
                break;
            }
        }
        return strb.toString();
    }
}

package palma.model.logic.builder.validate;

import palma.model.logic.builder.Parameter;

import java.util.List;

/**
 * Interfejs sprawdzajacy przestrzeganie ograniczen nalozonych na parametry
 */
public interface ParameterValidator {
    /**
     * Testuje parametr i zwraca liste bledow
     * @param parameter
     * @return
     */
    List<ValidationError> validate(Parameter parameter);
}

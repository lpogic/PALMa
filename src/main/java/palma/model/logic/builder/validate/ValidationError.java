package palma.model.logic.builder.validate;

public class ValidationError {

    private Object object;
    private String message;

    public ValidationError(Object object, String message) {
        this.object = object;
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}

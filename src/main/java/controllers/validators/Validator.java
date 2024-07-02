package controllers.validators;

import java.util.Collection;

public abstract class Validator<T> {

    protected Validator<T> next;
    protected final String errorMessage;

    protected Validator(String errorMessage) {
        this.next = null;
        this.errorMessage = errorMessage;
    }

    protected Validator(String errorMessage, Validator<T> next) {
        this(errorMessage);
        this.next = next;
    }

    @SuppressWarnings("unused")
    public String validate(T validatable, Collection<T> collection) {

        String error = specificallyValidate(validatable, collection);

        if(error != null){
            return error;
        }

        if(next != null){
            return next.validate(validatable, collection);
        }

        return null;
    }

    protected  abstract String specificallyValidate(T validatable, Collection<T> collection);
}

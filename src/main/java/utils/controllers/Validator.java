package utils.controllers;

public abstract class Validator {

    protected Validator next;
    protected String errorMessage;

    protected Validator(String errorMessage) {
        this.next = null;
        this.errorMessage = errorMessage;
    }

    protected Validator(String errorMessage, Validator next) {
        this(errorMessage);
        this.next = next;

    }

    public String validate(String validatable) {

        String error = specificallyValidate(validatable);

        if(error != null){
            return error;
        }

        if(next != null){
            return next.validate(validatable);
        }

        return null;
    }

    protected  abstract String specificallyValidate(String validatable);

}

package utils.controllers;

public class IsNumberValidator extends Validator {

    public IsNumberValidator(String errorMessage) {
        super(errorMessage);
    }

    public IsNumberValidator(String errorMessage, Validator next) {
        super(errorMessage, next);
    }

    @Override
    protected String specificallyValidate(String validatable) {
        try{
            Integer.parseInt(validatable);
        }catch (NumberFormatException exception){
            return errorMessage;
        }
        return null;
    }
}

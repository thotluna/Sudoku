package controllers.validators;

import utils.models.Result;

public abstract class PutInputValidator {

    protected final PutInputValidator next;

    protected PutInputValidator() {
        this.next = null;
    }

    protected PutInputValidator(PutInputValidator next) {
        this.next = next;
    }

    public Result<String, String> validate(String validatable) {

        Result<String, String> validateResult = specificallyValidate(validatable);

        if(validateResult.hasError()){
            return validateResult;
        }

        if(next != null){
            return next.validate(validatable);
        }

        return validateResult;
    }

    protected  abstract Result<String, String> specificallyValidate(String validatable);


}

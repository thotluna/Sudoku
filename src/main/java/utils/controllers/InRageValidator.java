package utils.controllers;

public class InRageValidator extends Validator {

    private final int maxRange;
    private static final int MIN_RAGE = 1;

    public InRageValidator(int maxRage, String errorMessage) {
        super(errorMessage);
        this.maxRange = maxRage;
    }


    public InRageValidator(int maxRage, String errorMessage, Validator next) {
       super(errorMessage, next);
       this.maxRange = maxRage;
    }

    @Override
    protected String specificallyValidate(String validatable) {
        int validatableNumber = Integer.parseInt(validatable);
        if(MIN_RAGE > validatableNumber || validatableNumber > maxRange){
            return this.errorMessage;
        }
        return null;
    }
}

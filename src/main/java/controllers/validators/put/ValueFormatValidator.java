package controllers.validators.put;

import controllers.validators.PutInputValidator;
import utils.models.Result;
import views.console.MessageRepository;

public class ValueFormatValidator extends PutInputValidator {

    private static final String ERROR = String.format("%s %s",
            MessageRepository.getInstance().get("sudoku.put-view.put.error"),
            MessageRepository.getInstance().get("sudoku.put-view.put.error-value"));

    public ValueFormatValidator(PutInputValidator next) {
        super(next);
    }

    @Override
    protected Result<String, String> specificallyValidate(String validatable) {
        String valueString = String.valueOf(validatable.charAt(3));

        try{
            int value = Integer.parseInt(valueString);
            if(value < 1 || value > 9){
                return  new Result<>(ERROR, null);
            }
        }catch (NumberFormatException e){
            return  new Result<>(ERROR, null);
        }

        return new Result<>(null, null);
    }
}

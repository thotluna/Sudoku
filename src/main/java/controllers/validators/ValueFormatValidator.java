package controllers.validators;

import utils.models.Result;
import views.console.MessageRepository;

public class ValueFormatValidator extends DataInputValidator {

    private static final String ERROR = String.format("%s %s",
            MessageRepository.getInstance().get("sudoku.put-view.put.error"),
            MessageRepository.getInstance().get("sudoku.put-view.put.error-value"));

    public ValueFormatValidator(DataInputValidator next) {
        super(next);
    }

    @Override
    protected Result<String, String> specificallyValidate(String validatable) {
        if(validatable.length() <= 3 || !validatable.contains(":")){
            return new Result<>(null, null);
        }
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

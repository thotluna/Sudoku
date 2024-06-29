package controllers.validators.put;

import utils.models.Result;
import views.console.MessageRepository;

public class ColumnFormatValidator extends PutInputValidator {

    private static final String ERROR = String.format("%s %s",
            MessageRepository.getInstance().get("sudoku.put-view.put.error"),
                        MessageRepository.getInstance().get("sudoku.put-view.put.error-coordinate"));

    public ColumnFormatValidator() {}

    public ColumnFormatValidator(PutInputValidator next) {
        super(next);
    }

    @Override
    protected Result<String, String> specificallyValidate(String validatable) {
        String column = String.valueOf(validatable.charAt(1));

        try{
            int number = Integer.parseInt(column);
            if(number < 1 || number > 9){
                return  new Result<>(ERROR, null);
            }
        }catch (NumberFormatException e){
            return  new Result<>(ERROR, null);
        }

        return new Result<>(null, null);
    }
}

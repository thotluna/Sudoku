package controllers.validators;

import utils.models.Result;
import views.console.MessageRepository;

public class RowFormatValidator extends DataInputValidator {

    public RowFormatValidator(DataInputValidator next) {
        super(next);
    }

    @Override
    protected Result<String, String> specificallyValidate(String validatable) {

        String row = String.valueOf(validatable.charAt(0)).toUpperCase();

        if( !row.matches("[A-I]")){
            String error = String.format("%s %s",
                    MessageRepository.getInstance().get("sudoku.put-view.put.error"),
                    MessageRepository.getInstance().get("sudoku.put-view.put.error-coordinate"));

            return  new Result<>(error, null);
        }

        return new Result<>(null, null);
    }
}

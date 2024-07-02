package controllers.validators.put;

import controllers.validators.PutInputValidator;
import utils.models.Result;
import views.console.MessageRepository;

public class FormatStringValidator extends PutInputValidator {

    public FormatStringValidator(PutInputValidator next) {
        super(next);
    }

    @Override
    protected Result<String, String> specificallyValidate(String validatable) {
        if(validatable.length() < 4 || validatable.length() > 5 || !validatable.contains(":")){
            return  new Result<>(MessageRepository.getInstance().get("sudoku.put-view.put.error"), null);
        }
        return  new Result<>(null, null);
    }
}

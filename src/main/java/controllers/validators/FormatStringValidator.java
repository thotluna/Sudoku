package controllers.validators;

import utils.models.Result;
import views.console.MessageRepository;

public class FormatStringValidator extends DataInputValidator {

    public FormatStringValidator(DataInputValidator next) {
        super(next);
    }

    @Override
    protected Result<String, String> specificallyValidate(String validatable) {


        String patters = "[A-Ia-i][1-9]|[A-Ia-i][1-9][+.-]|[A-Ia-i][1-9][/:][1-9][+]?";
        if(!validatable.matches(patters)){
            return new Result<>(MessageRepository.getInstance().get("sudoku.put-view.put.error"),
                    null);
        }

        return  new Result<>(null, null);
    }
}

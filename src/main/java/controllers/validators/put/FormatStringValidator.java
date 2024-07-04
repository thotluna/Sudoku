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
        String[] separate = validatable.split(":");
        if(validatable.length() < 4 || validatable.length() > 5 ||
                !validatable.contains(":") || separate.length != 2 ||
                separate[0].length() != 2 ||
                separate[1].length() < 1 || separate[1].length() >2 ||
                (separate[1].length() == 2 && !String.valueOf(separate[1].charAt(1)).matches("[?,H]"))
        ){
            return  new Result<>(MessageRepository.getInstance().get("sudoku.put-view.put.error"), null);
        }

        return  new Result<>(null, null);
    }
}

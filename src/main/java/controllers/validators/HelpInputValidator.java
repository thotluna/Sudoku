package controllers.validators;

import controllers.GameController;
import utils.models.Result;
import views.console.MessageRepository;

public class HelpInputValidator extends InputPutValidator {

    public HelpInputValidator(GameController controller) {
        super(controller);
    }

    public HelpInputValidator(GameController controller, DataInputValidator next) {
        super(controller, next);
    }

    @Override
    protected Result<String, String> specificallyValidate(String validatable) {
        String patter = "[A-Za-z][1-9][.]";
        if(validatable.matches(patter)){
            return new Result<>(null, null);
        }
        return new Result<>(MessageRepository.getInstance().get("sudoku.input-game.error-format"), null);
    }
}

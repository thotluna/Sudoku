package controllers.validators;

import controllers.GameController;
import models.Coordinate;
import utils.models.Result;
import views.console.MessageRepository;

public class NotRepeatInColumnWithControllerValidator extends InputPutValidator {

    public NotRepeatInColumnWithControllerValidator(GameController controller) {
        super(controller);
    }

    public NotRepeatInColumnWithControllerValidator(GameController controller, DataInputValidator next) {
        super(controller, next);
    }

    @Override
    protected Result<String, String> specificallyValidate(String validatable) {

        validatable = validatable.replace('/', ':');

        if(validatable.length() <= 3 || !validatable.contains(":")){
            return new Result<>(null, null);
        }

        Coordinate coordinate = new Coordinate(validatable.split(":")[0]);
        int value = Integer.parseInt(validatable.substring(3,4));

        if(controller.hasValueInColumn(value, coordinate.getColumn())){
            String error = MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-column");
            return  new Result<>(error, null);
        }
        return new Result<>(null, null);
    }
}

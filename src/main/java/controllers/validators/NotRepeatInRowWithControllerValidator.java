package controllers.validators;

import controllers.GameController;
import models.Coordinate;
import utils.models.Result;
import views.console.MessageRepository;

public class NotRepeatInRowWithControllerValidator extends InputPutValidator {

    public NotRepeatInRowWithControllerValidator(GameController controller) {
        super(controller);
    }

    public NotRepeatInRowWithControllerValidator(GameController controller, DataInputValidator next) {
        super(controller, next);
    }

    @Override
    protected Result<String, String> specificallyValidate(String validatable) {

        validatable = validatable.replace('/', ':');

        if(validatable.length() <= 3 || !validatable.contains(":")){
            return new Result<>(null, null);
        }

        Coordinate coordinate = new Coordinate( validatable.split(":")[0]);
        int value = Integer.parseInt( validatable.substring(3, 4));


        if(controller.hasValueInRow(value, coordinate.getRow())){
            String error = MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-row");
            return  new Result<>(error, null);
        }
        return new Result<>(null, null);
    }
}

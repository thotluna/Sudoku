package controllers.validators;

import controllers.GameController;
import models.Board;
import models.Coordinate;
import utils.models.Result;
import views.console.MessageRepository;

public class NotRepeatInColumnWithControllerValidator extends InputPutValidator {

    public NotRepeatInColumnWithControllerValidator(GameController controller, DataInputValidator next) {
        super(controller, next);
    }

    @Override
    protected Result<String, String> specificallyValidate(String validatable) {

        if(validatable.length() <= 3 || !validatable.contains(":")){
            return new Result<>(null, null);
        }

        Coordinate coordinate = new Coordinate(validatable.split(":")[0]);
        int value = Integer.parseInt(validatable.split(":")[1]);

        Board board = controller.getBoard();
        if(board.hasValueInColumn(value, coordinate.getColumn())){
            String error = MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-column");
            return  new Result<>(error, null);
        }
        return new Result<>(null, null);
    }
}

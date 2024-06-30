package controllers.validators.put;

import controllers.GameController;
import models.Cell;
import models.Coordinate;
import utils.models.Result;
import views.console.MessageRepository;

public class NotRepeatInColumnValidator extends InputPutValidator {
    public NotRepeatInColumnValidator(GameController controller) {
        super(controller);
    }

    public NotRepeatInColumnValidator(GameController controller, PutInputValidator next) {
        super(controller, next);
    }

    @Override
    protected Result<String, String> specificallyValidate(String validatable) {
        Coordinate coordinate = new Coordinate(validatable.split(":")[0]);
        int value = Integer.parseInt(validatable.split(":")[1]);

        Cell[][] board = controller.getBoard();

        for (int row = 0; row < 9; row++) {
            if(board[row][coordinate.getColumn()].getValue() == value){
                String error = MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-column");
                return  new Result<>(error, null);
            }
        }
        return new Result<>(null, null);
    }
}

package controllers.validators.put;

import controllers.GameController;
import models.Cell;
import models.Coordinate;
import utils.models.Result;
import views.console.MessageRepository;

import java.util.Arrays;

public class NotRepeatInRowValidator extends InputPutValidator {
    public NotRepeatInRowValidator(GameController controller) {
        super(controller);
    }

    public NotRepeatInRowValidator(GameController controller, PutInputValidator next) {
        super(controller, next);
    }

    @Override
    protected Result<String, String> specificallyValidate(String validatable) {
        Coordinate coordinate = new Coordinate( validatable.split(":")[0]);
        int value = Integer.parseInt( validatable.split(":")[1]);

        Cell[][] board = controller.getBoard();

        Cell[] row = board[coordinate.getRow()];

        boolean exist = Arrays.stream(row).anyMatch(cell -> cell.getValue() == value);

        if(exist){
            String error = MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-row");
            return  new Result<>(error, null);
        }
        return new Result<>(null, null);
    }
}

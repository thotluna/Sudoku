package controllers.validators.put;

import controllers.GameController;
import models.Cell;
import models.Coordinate;
import utils.models.Result;
import views.console.MessageRepository;

public class NotRepeatSubgridValidator extends InputPutValidator {
    public NotRepeatSubgridValidator(GameController controller) {
        super(controller);
    }

    protected NotRepeatSubgridValidator(GameController controller, PutInputValidator next) {
        super(controller, next);
    }

    @Override
    protected Result<String, String> specificallyValidate(String validatable) {
        String[] separate = validatable.split(":");
        Coordinate coordinate = new Coordinate(separate[0]);

        int value = Integer.parseInt(separate[1]);

        Cell[][] board = controller.getBoard();

        for (int row = coordinate.rowGroup() * 3 ; row < (coordinate.rowGroup() * 3) + 3; row++) {
            for (int column = coordinate.columnGroup() * 3 ; column < (coordinate.columnGroup() * 3) + 3; column++) {
                if(board[row][column].getValue() == value){
                    String error = MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-subgrid");
                    return  new Result<>(error, null);
                }
            }
        }

        return new Result<>(null, null);
    }
}

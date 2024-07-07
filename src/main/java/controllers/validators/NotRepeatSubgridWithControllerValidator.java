package controllers.validators;

import controllers.GameController;
import models.Board;
import models.Coordinate;
import utils.models.Result;
import views.console.MessageRepository;

public class NotRepeatSubgridWithControllerValidator extends InputPutValidator {
    public NotRepeatSubgridWithControllerValidator(GameController controller) {
        super(controller);
    }

    @Override
    protected Result<String, String> specificallyValidate(String validatable) {
        if(validatable.length() <= 3 || !validatable.contains(":")){
            return new Result<>(null, null);
        }

        Coordinate coordinate = new Coordinate(validatable.substring(0,2));

        int value = Integer.parseInt(validatable.substring(3,4));

        Board board = controller.getBoard();
        if(board.hasValueInSubstring(value, coordinate)){
            String error = MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-subgrid");
            return  new Result<>(error, null);
        }

        return new Result<>(null, null);
    }
}

package controllers.validators;

import controllers.GameController;
import utils.models.Result;
import views.console.MessageRepository;

public class AvailableCoordinateValidator extends InputPutValidator {

    public AvailableCoordinateValidator( GameController controller, DataInputValidator next) {
        super( controller, next);
    }

    @Override
    protected Result<String, String> specificallyValidate(String validatable) {
        String coordinate = validatable.split(":")[0];
        if(!controller.isValidCell(coordinate)){
            String error = String.format("%s %s", MessageRepository.getInstance().get("sudoku.put-view.put.error"),
                    MessageRepository.getInstance().get("sudoku.put-view.put.error-coordinate-cell-busy"));
            return  new Result<>(error, null);
        }
        return new Result<>(null, null);
    }
}

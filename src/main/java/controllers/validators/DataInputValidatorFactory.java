package controllers.validators;

import controllers.GameController;

public class DataInputValidatorFactory {
    private final DataInputValidator validator;

    public DataInputValidatorFactory(GameController controller) {

        DataInputValidator notRepeatSubgridValidator = new NotRepeatSubgridWithControllerValidator(controller);
        DataInputValidator notRepeatColumnValidator = new NotRepeatInColumnWithControllerValidator(controller, notRepeatSubgridValidator);
        DataInputValidator notRepeatRowValidator = new NotRepeatInRowWithControllerValidator(controller, notRepeatColumnValidator);
        DataInputValidator availableCoordinateValidator = new AvailableCoordinateValidator( controller, notRepeatRowValidator);
        validator = new FormatStringValidator(availableCoordinateValidator);

    }

    public DataInputValidator getValidator(){
        return validator;
    }
}

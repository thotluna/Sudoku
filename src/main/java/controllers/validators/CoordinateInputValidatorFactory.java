package controllers.validators;

import controllers.GameController;

public class CoordinateInputValidatorFactory {
    private final DataInputValidator validator;

    public CoordinateInputValidatorFactory(GameController controller) {

        DataInputValidator availableCoordinateValidator = new AvailableCoordinateValidator( controller);
        validator = new FormatStringValidator(availableCoordinateValidator);

    }

    public DataInputValidator getValidator(){
        return validator;
    }
}

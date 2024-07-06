package controllers.validators;

import controllers.GameController;

public class CoordinateInputValidatorFactory {
    private final DataInputValidator validator;

    public CoordinateInputValidatorFactory(GameController controller) {

        DataInputValidator availableCoordinateValidator = new AvailableCoordinateValidator( controller);
        DataInputValidator columnFormatValidator = new ColumnFormatValidator(availableCoordinateValidator);
        DataInputValidator rowFormatValidator = new RowFormatValidator(columnFormatValidator);
        validator = new FormatStringValidator(rowFormatValidator);

    }

    public DataInputValidator getValidator(){
        return validator;
    }
}

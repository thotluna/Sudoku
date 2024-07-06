package controllers.validators;

import controllers.GameController;

public class DataInputValidatorFactory {
    private final DataInputValidator validator;

    public DataInputValidatorFactory(GameController controller) {

        DataInputValidator notRepeatSubgridValidator = new NotRepeatSubgridWithControllerValidator(controller);
        DataInputValidator notRepeatColumnValidator = new NotRepeatInColumnWithControllerValidator(controller, notRepeatSubgridValidator);
        DataInputValidator notRepeatRowValidator = new NotRepeatInRowWithControllerValidator(controller, notRepeatColumnValidator);
        DataInputValidator availableCoordinateValidator = new AvailableCoordinateValidator( controller, notRepeatRowValidator);
        DataInputValidator valueFormatValidator = new ValueFormatValidator(availableCoordinateValidator);
        DataInputValidator columnFormatValidator = new ColumnFormatValidator(valueFormatValidator);
        DataInputValidator rowFormatValidator = new RowFormatValidator(columnFormatValidator);
        validator = new FormatStringValidator(rowFormatValidator);

    }

    public DataInputValidator getValidator(){
        return validator;
    }
}

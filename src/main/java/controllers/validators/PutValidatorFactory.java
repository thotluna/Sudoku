package controllers.validators;

import controllers.GameController;
import controllers.validators.put.*;

public class PutValidatorFactory {
    private final PutInputValidator validator;

    public PutValidatorFactory(GameController controller) {

        PutInputValidator notRepeatSubgridValidator = new NotRepeatSubgridWithControllerValidator(controller);
        PutInputValidator notRepeatColumnValidator = new NotRepeatInColumnWithControllerValidator(controller, notRepeatSubgridValidator);
        PutInputValidator notRepeatRowValidator = new NotRepeatInRowWithControllerValidator(controller, notRepeatColumnValidator);
        PutInputValidator availableCoordinateValidator = new AvailableCoordinateValidator( controller, notRepeatRowValidator);
        PutInputValidator valueFormatValidator = new ValueFormatValidator(availableCoordinateValidator);
        PutInputValidator columnFormatValidator = new ColumnFormatValidator(valueFormatValidator);
        PutInputValidator rowFormatValidator = new RowFormatValidator(columnFormatValidator);
        validator = new FormatStringValidator(rowFormatValidator);

    }

    public PutInputValidator getValidator(){
        return validator;
    }
}

package controllers.validators;

import models.Cell;
import java.util.Collection;

public class NonRepeatedValueRowValidator extends Validator<Cell> {

    public NonRepeatedValueRowValidator(String errorMessage) {
        super(errorMessage);
    }

    public NonRepeatedValueRowValidator(String errorMessage, Validator<Cell> next) {
        super(errorMessage, next);
    }

    @Override
    protected String specificallyValidate(Cell validatable, Collection<Cell> cells) {

        boolean exist = cells.stream()
                .filter(cell -> cell!= null && cell.getRow() == validatable.getRow() )
                .anyMatch(cell -> cell.containValue(validatable.getValue()));

        if(exist){
            return this.errorMessage;
        }

        return null;

    }
}

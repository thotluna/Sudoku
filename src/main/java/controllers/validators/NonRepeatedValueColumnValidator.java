package controllers.validators;

import models.Cell;

import java.util.Collection;

public class NonRepeatedValueColumnValidator extends Validator<Cell> {

    public NonRepeatedValueColumnValidator(String errorMessage, Validator<Cell> next) {
        super(errorMessage, next);
    }

    @Override
    protected String specificallyValidate(Cell validatable, Collection<Cell> cells) {

        boolean exist = cells.stream()
                .filter(cell -> cell != null && cell.getColumn() == validatable.getColumn())
                .anyMatch(cell -> cell.containValue(validatable.value()));

        if(exist){
            return this.errorMessage;
        }

        return null;

    }
}

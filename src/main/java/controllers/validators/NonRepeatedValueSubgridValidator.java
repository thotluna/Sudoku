package controllers.validators;

import models.Cell;

import java.util.Collection;

public class NonRepeatedValueSubgridValidator extends Validator<Cell> {

    public NonRepeatedValueSubgridValidator(String errorMessage) {
        super(errorMessage);
    }

    @Override
    protected String specificallyValidate(Cell validatable, Collection<Cell> cells) {

        int rowGroup = getGroup(validatable.getRow());
        int columnGroup = getGroup(validatable.getColumn());

        boolean exist = cells.stream()
                .filter(cell -> cell != null && getGroup(cell.getRow()) == rowGroup && getGroup(cell.getColumn()) == columnGroup)
                .anyMatch(cell -> cell.containValue(validatable.value()));

        if(exist){
            return this.errorMessage;
        }

        return null;

    }

    private int getGroup(int valueCoordinate){
        return (int) Math.floor(valueCoordinate / 3.0);
    }
}

package controllers.validators;

import models.Cell;
import views.console.MessageRepository;

public class GenerateValidatorsFactory {

    private final Validator<Cell> validator;

    public GenerateValidatorsFactory() {

        Validator<Cell> validatorSubGrid = new NonRepeatedValueSubgridValidator(
                MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-subgrid")
        );
        Validator<Cell> validatorColumn = new NonRepeatedValueColumnValidator(
                MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-column"),
                validatorSubGrid
        );
        this.validator = new NonRepeatedValueRowValidator(
                MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-row"),
                validatorColumn);
    }

    public Validator<Cell> getCellValidator(){
        return this.validator;

    }
}

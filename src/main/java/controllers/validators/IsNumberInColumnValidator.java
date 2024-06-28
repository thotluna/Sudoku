package controllers.validators;

import models.Coordinate;

public  class IsNumberInColumnValidator extends ValidatorSudoku {


    public IsNumberInColumnValidator() {
    }

    public IsNumberInColumnValidator(ValidatorSudoku next) {
        super(next);
    }

    @Override
    protected boolean specificValidation(int[][] board, int number, Coordinate coordinate) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][coordinate.getColumn()] == number) {
                return false;
            }
        }
        return true;
    }
}

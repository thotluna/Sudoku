package controllers.validators;

import models.Coordinate;

public  class IsNumberInRowValidator extends ValidatorSudoku {


    public IsNumberInRowValidator() {
    }

    public IsNumberInRowValidator(ValidatorSudoku next) {
        super(next);
    }

    @Override
    protected boolean specificValidation(int[][] board, int number, Coordinate coordinate) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[coordinate.getRow()][i] == number) {
                return false;
            }
        }
        return true;
    }
}

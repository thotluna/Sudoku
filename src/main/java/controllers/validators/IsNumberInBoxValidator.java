package controllers.validators;

import models.Coordinate;

public  class IsNumberInBoxValidator extends ValidatorSudoku {


    public IsNumberInBoxValidator() {
    }

    public IsNumberInBoxValidator(ValidatorSudoku next) {
        super(next);
    }

    @Override
    protected boolean specificValidation(int[][] board, int number, Coordinate coordinate) {
        int localBoxRow = coordinate.getRow() - coordinate.getRow() % 3;
        int localBoxColumn = coordinate.getColumn() - coordinate.getColumn() % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return false;
                }
            }
        }
        return true;
    }
}

package controllers.validators;

import models.Coordinate;

public abstract class ValidatorSudoku {

    protected static final int GRID_SIZE = 9;
    private final ValidatorSudoku next;

    protected ValidatorSudoku() {
        this.next = null;
    }

    protected ValidatorSudoku(ValidatorSudoku next) {
        this.next = next;
    }

    public boolean validate(int[][] board, int number, Coordinate coordinate){
        boolean solved = specificValidation(board, number, coordinate);

        if(!solved){
            return false;
        }

        if (next != null){
            return next.validate(board, number, coordinate);
        }

        return true;
    }

    protected abstract boolean specificValidation(int[][] board, int number, Coordinate coordinate);
}

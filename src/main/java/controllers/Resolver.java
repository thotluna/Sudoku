package controllers;

import controllers.validators.IsNumberInBoxValidator;
import controllers.validators.IsNumberInColumnValidator;
import controllers.validators.IsNumberInRowValidator;
import controllers.validators.ValidatorSudoku;
import models.Coordinate;

public class Resolver {

    private static final int GRID_SIZE = 9;
    private int[][] solution;

    private static boolean isValidPlacement(int[][] board, int number, Coordinate coordinate) {
        ValidatorSudoku isNumberInRowValidator = new IsNumberInRowValidator();
        ValidatorSudoku isNumberInColumnValidator = new IsNumberInColumnValidator(isNumberInRowValidator);
        ValidatorSudoku validator = new IsNumberInBoxValidator(isNumberInColumnValidator);

        return validator.validate(board, number, coordinate);

    }

    public boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                Coordinate coordinate = new Coordinate(row, column);
                if (board[row][column] == 0) {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidPlacement(board, numberToTry, coordinate)) {
                            board[row][column] = numberToTry;
                            if (solveBoard(board)) {
                                this.solution = board;
                                return true;
                            } else {
                                board[row][column] = 0;
                            }

                        }
                    }
                    return false;
                }
            }
        }
        this.solution = board;
        return true;
    }

    public int[][] getSolution() {
        return solution;
    }

    public void print() {
        System.out.println("resolve");
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                System.out.print(this.solution[row][column]);
            }
            System.out.println("");
        }
    }
}

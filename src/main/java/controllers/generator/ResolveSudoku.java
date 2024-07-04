package controllers.generator;

import models.Board;
import models.Cell;
import models.Coordinate;
import types.TypeCell;

public class ResolveSudoku {

    private final int dimension;
    private final int maxValue;

    private Board board;

    public ResolveSudoku(int dimension, int maxValue) {
        this.dimension = dimension;
        this.maxValue = maxValue;
    }

    public ResolveSudoku() {
        this(9, 9);
    }

    public boolean solved(Board board){
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                Coordinate coordinate = new Coordinate(row, col);
                if(board.isNullCell(coordinate)){
                    int value = 1;
                    while (!board.isComplete() && value <= maxValue){
                        Cell cell = new Cell(coordinate, value, TypeCell.CANDIDATE);
                        if(isValid(board, cell)){
                            board.addCell(cell);
                            this.board = board;
                            if(solved(board)){
                                return true;
                            }else{
                                board.addCell(Cell.nullCell(coordinate));
                                this.board = board;
                            }
                        }
                        value++;
                    }
                    return false;
                }
            }
        }
        this.board = board;
        return true;
    }

    private boolean isValid(Board board, Cell cell){
        return !board.hasValueInRow(cell.value(), cell.getRow()) &&
                !board.hasValueInColumn(cell.value(), cell.getColumn()) &&
                !board.hasValueInSubstring(cell.value(), cell.coordinate());
    }

    public Board getBoard() {
        return this.board;
    }
}

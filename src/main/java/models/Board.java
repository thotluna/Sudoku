package models;

import types.TypeCell;
import utils.models.Interval;

import java.util.Arrays;
import java.util.Objects;

public class Board {

    private final int dimension;

    private Cell[][] board;

    public static final int DIMENSION_DEFAULT = 9;

    public Board() {
        this.dimension = DIMENSION_DEFAULT;
        createBoard();
    }

    public Board(int dimension) {
        this.dimension = dimension;
        createBoard();
    }

    private void createBoard() {
        board = new Cell[dimension][dimension];
        for (int row = 0; row < dimension; row++) {
            for (int column = 0; column < dimension; column++) {
                board[row][column] = new Cell(new Coordinate(row, column), 0, TypeCell.CANDIDATE);
            }
        }
    }

    public void clear() {
        createBoard();
    }

    void forceAdd(Cell cell){
        board[cell.getRow()][cell.getColumn()] = cell;
    }

    public void addCell(Cell cell){
        assert cell != null;
        assert cell.type() != TypeCell.FIXED;
        this.forceAdd(cell);
    }

    public void addCell(Cell cell, boolean override){
        assert cell != null;
        assert override;
        this.forceAdd(cell);
    }

    public Cell getCell(int row, int column){
        assert new Interval(0, dimension -1).isWithinRange(row);
        assert new Interval(0, dimension -1).isWithinRange(column);
        return board[row][column];
    }

    public Cell getCell(Coordinate coordinate){
        return this.getCell(coordinate.getRow(), coordinate.getColumn());
    }

    public int getValue(int row, int column){
        assert new Interval(0, dimension -1).isWithinRange(row);
        assert new Interval(0, dimension -1).isWithinRange(column);
        return board[row][column].value();
    }

    @SuppressWarnings("unused")
    public int getValue(Coordinate coordinate){
        return this.getValue(coordinate.getRow(), coordinate.getColumn());
    }

    public boolean isNullCell(int row, int column){
        assert new Interval(0, dimension -1).isWithinRange(row);
        assert new Interval(0, dimension -1).isWithinRange(column);
        return board[row][column].isEmpty();
    }

    public boolean isNullCell(Coordinate coordinate){
        return this.isNullCell(coordinate.getRow(), coordinate.getColumn());
    }

    @SuppressWarnings("unused")
    public boolean isBusyCell(int row, int column){
        assert new Interval(0, dimension -1).isWithinRange(row);
        assert new Interval(0, dimension -1).isWithinRange(column);
        return !this.isNullCell(row, column) && board[row][column].type() == TypeCell.FIXED;
    }

    public boolean isBusyCell(Coordinate coordinate){
        return this.isBusyCell(coordinate.getRow(), coordinate.getColumn());
    }
    public boolean isBusyCell(String coordinateString){
        Coordinate coordinate = new Coordinate(coordinateString);
        return this.isBusyCell(coordinate.getRow(), coordinate.getColumn());
    }

    public Board newCopy(){
        Board newBoard = new Board();
        for (int row = 0; row < dimension; row++) {
            for (int column = 0; column < dimension; column++) {
                newBoard.addCell(this.board[row][column], true);
            }
        }
        return newBoard;
    }

    public boolean isComplete(){

        for (int row = 0; row < dimension; row++) {
            for (int column = 0; column < dimension; column++) {
                if(this.isNullCell(row, column)){
                    return false;
                }
            }
        }

        return true;
    }

    public Cell[][] get(){
        return board;
    }

    public boolean hasValueInRow(int value, int row){
        Cell[] rowCells = board[row];
        for (int col = 0; col < dimension; col++) {
            if(rowCells[col].value() == value){
                return true;
            }
        }
        return false;
    }

    public boolean hasValueInColumn(int value, int column){
        for (int row = 0; row < dimension; row++) {
            if(board[row][column].value() == value){
                return true;
            }
        }
        return false;
    }

    public boolean hasValueInSubstring(int value, Coordinate coordinate){
        int max = (int) (Math.sqrt(dimension));
        int rowSubgrid = (coordinate.getRow() / max) * max;
        int columnSubgrid = (coordinate.getColumn() /max) * max;

        for (int row = rowSubgrid; row < rowSubgrid + max; row++) {
            for (int column = columnSubgrid; column < columnSubgrid + max; column++) {
                if(board[row][column].value() == value){
                    return true;
                }
            }
        }

        return false;
    }

    public void print(){
        for (int row = 0; row < dimension; row++) {
            for (int column = 0; column < dimension; column++) {
                System.out.print(this.getCell(row, column).value() + " " );
                if((column + 1) % Math.sqrt(dimension) == 0){
                    System.out.print(" " );
                }
            }
            System.out.println();
            if((row + 1) % Math.sqrt(dimension) == 0){
                System.out.println();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return dimension == board1.dimension && Arrays.deepEquals(board, board1.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(dimension);
        result = 31 * result + Arrays.deepHashCode(board);
        return result;
    }

    public boolean isEmptyComplete() {
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                if(!this.isNullCell(row, col)){
                    return false;
                }
            }
        }
        return true;
    }
}

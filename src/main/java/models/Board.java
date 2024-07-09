package models;

import types.TypeCell;
import utils.models.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Board {

    private final int dimension;

    private Cell[][] cells;

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
        cells = new Cell[dimension][dimension];

        for (int row = 0; row < dimension; row++) {
            for (int column = 0; column < dimension; column++) {
                cells[row][column] = new Cell(new Coordinate(row, column), 0, TypeCell.CANDIDATE);
            }
        }


    }

    public void clear() {
        createBoard();
    }

    void forceAdd(Cell cell){
        cells[cell.getRow()][cell.getColumn()] = cell;
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
        return cells[row][column];
    }

    public Cell getCell(Coordinate coordinate){
        return this.getCell(coordinate.getRow(), coordinate.getColumn());
    }

    public int getValue(int row, int column){
        assert new Interval(0, dimension -1).isWithinRange(row);
        assert new Interval(0, dimension -1).isWithinRange(column);
        return cells[row][column].value();
    }

    public int getValue(Coordinate coordinate){
        return this.getValue(coordinate.getRow(), coordinate.getColumn());
    }

    public boolean isNullCell(int row, int column){
        assert new Interval(0, dimension -1).isWithinRange(row);
        assert new Interval(0, dimension -1).isWithinRange(column);
        return cells[row][column].isEmpty();
    }

    public boolean isNullCell(Coordinate coordinate){
        return this.isNullCell(coordinate.getRow(), coordinate.getColumn());
    }


    public boolean isBusyCell(int row, int column){
        assert new Interval(0, dimension -1).isWithinRange(row);
        assert new Interval(0, dimension -1).isWithinRange(column);
        return !this.isNullCell(row, column) && cells[row][column].type() == TypeCell.FIXED;
    }

    public boolean isBusyCell(Coordinate coordinate){
        return this.isBusyCell(coordinate.getRow(), coordinate.getColumn());
    }
    public boolean isBusyCell(String coordinateString){
        Coordinate coordinate = new Coordinate(coordinateString);
        return this.isBusyCell(coordinate);
    }

    public Board newCopy(){
        Board newBoard = new Board();
        for (int row = 0; row < dimension; row++) {
            for (int column = 0; column < dimension; column++) {
                newBoard.addCell(this.cells[row][column], true);
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

    public List<Cell> verifyClean(Coordinate coordinate, int value) {
        Cell old = getCell(coordinate);
        if(old.value() == value) return new ArrayList<>();

        return getCellsError(old.coordinate(), old.value());
    }

    public boolean hasValueInRow(int value, int row){
        Cell[] rowCells = cells[row];
        for (int col = 0; col < dimension; col++) {
            if(rowCells[col].value() == value){
                return true;
            }
        }
        return false;
    }

    public Cell getCellWithValueInRow(int value, int row) {
        for (Cell cell : cells[row]) {
            if(cell.value() == value){
                return cell;
            }
        }
        return null;
    }

    public boolean hasValueInColumn(int value, int column){
        for (int row = 0; row < dimension; row++) {
            if(cells[row][column].value() == value){
                return true;
            }
        }
        return false;
    }

    public Cell getCellWithValueInColumn(int value, int column) {
        for (int row = 0; row < dimension; row++) {
            Cell cell = cells[row][column];
            if (cell.value() == value){
                return cell;
            }
        }
        return null;
    }

    public boolean hasValueInSubstring(int value, Coordinate coordinate){
        int max = (int) (Math.sqrt(dimension));
        int rowSubgrid = (coordinate.getRow() / max) * max;
        int columnSubgrid = (coordinate.getColumn() /max) * max;

        for (int row = rowSubgrid; row < rowSubgrid + max; row++) {
            for (int column = columnSubgrid; column < columnSubgrid + max; column++) {
                if(cells[row][column].value() == value){
                    return true;
                }
            }
        }

        return false;
    }

    public Cell getCellWithValueInSubgrid(int value, Coordinate coordinate) {
        int max = (int) (Math.sqrt(dimension));
        int rowSubgrid = (coordinate.getRow() / max) * max;
        int columnSubgrid = (coordinate.getColumn() /max) * max;

        for (int row = rowSubgrid; row < rowSubgrid + max; row++) {
            for (int column = columnSubgrid; column < columnSubgrid + max; column++) {
                if(cells[row][column].value() == value){
                    return cells[row][column];
                }
            }
        }

        return null;
    }

    public List<Cell> getCellsError(Coordinate coordinate, int value) {
        List<Cell> cellsWithError = new ArrayList<>();
        if(hasValueInRow(value, coordinate.getRow())){
            cellsWithError.add(getCellWithValueInRow(value, coordinate.getRow()));
        }
        if(hasValueInColumn(value, coordinate.getRow())){
            cellsWithError.add(getCellWithValueInColumn(value, coordinate.getColumn()));
        }
        if(hasValueInSubstring(value, coordinate)){
            cellsWithError.add( getCellWithValueInSubgrid(value, coordinate));
        }

        return cellsWithError;
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

    public Cell[][] get(){
        return cells;
    }

    public int getDimension() {
        return dimension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return dimension == board1.dimension && Arrays.deepEquals(cells, board1.cells);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(dimension);
        result = 31 * result + Arrays.deepHashCode(cells);
        return result;
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



}

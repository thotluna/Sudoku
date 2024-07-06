package models;

import types.TypeCell;
import utils.models.Interval;

public record Cell(Coordinate coordinate, int value, TypeCell type) {
    private static final Interval INTERVAL_VALUE = new Interval(0, 9);

    public Cell {
        assert INTERVAL_VALUE.isWithinRange(value);
    }

    public static Cell nullCell(int row, int column) {
        return new Cell(new Coordinate(row, column), 0, TypeCell.CANDIDATE);
    }

    public static Cell newCellCandidate(int row, int column, int value){
        return new Cell(new Coordinate(row, column), value, TypeCell.CANDIDATE);
    }

    public static Cell newCellCandidate(int row, int column, int value, TypeCell type){
        return new Cell(new Coordinate(row, column), value, type);
    }

    public static Cell nullCell(Coordinate coordinate) {
        return nullCell(coordinate.getRow(), coordinate.getColumn());
    }

    public int getColumn() {
        return coordinate.getColumn();
    }

    public int getRow() {
        return coordinate.getRow();
    }

    @Override
    public String toString() {
        return coordinate.toString() + ":" + value + type.toString();
    }

    public boolean isEmpty() {
        return value == 0;
    }

}

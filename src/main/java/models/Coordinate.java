package models;

import utils.models.Interval;

import java.util.Objects;

public class Coordinate {
    private final int column;
    private final int row;

    private static final Interval INTERVAL = new Interval(0, 8);
    private static final int OFFSET_ANSI = 64;

    public Coordinate(int row, int column) {
        assert INTERVAL.isWithinRange(row);
        assert INTERVAL.isWithinRange(column);
        this.column = column;
        this.row = row;
    }

    public Coordinate(String coordinateString) {
        assert coordinateString.length() == 2;
        coordinateString = coordinateString.toUpperCase();
        Interval interval = new Interval(1, 9);
        assert interval.isWithinRange(coordinateString.charAt(0) - OFFSET_ANSI);
        int columnTemp = Integer.parseInt(String.valueOf(coordinateString.charAt(1)));
        assert interval.isWithinRange(columnTemp);

        this.row = coordinateString.charAt(0) - OFFSET_ANSI - 1;
        this.column = columnTemp - 1;
    }

    @Override
    public String toString() {
        return String.format("%S%d",  (char) (row + OFFSET_ANSI+1), column +1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return column == that.column && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public int rowGroup(){
        return getGroup(row);
    }

    public int columnGroup(){
        return getGroup(column);
    }

    private int getGroup(int valueCoordinate){
        return (int) Math.floor(valueCoordinate / 3.0);
    }
}

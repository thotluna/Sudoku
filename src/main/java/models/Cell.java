package models;

import types.TypeCell;
import utils.models.Interval;

public class Cell {
    private final Coordinate coordinate;
    private final int value;

    private final TypeCell type;

    private static final Interval INTERVAL_VALUE = new Interval(0, 9);

    public Cell(Coordinate coordinate, int value, TypeCell type) {
        assert INTERVAL_VALUE.isWithinRange(value);
        this.coordinate = coordinate;
        this.value = value;
        this.type = type;
    }

    @SuppressWarnings("unused")
    public boolean hasThisCoordinate(Coordinate coordinate){
        return this.coordinate.equals(coordinate);
    }

    public int getColumn(){
        return coordinate.getColumn();
    }

    public int getRow(){
        return coordinate.getRow();
    }

    public Coordinate getCoordinate(){
        return coordinate;
    }

    public int getValue() {
        return value;
    }

    public boolean containValue(int value) {
        return this.value == value;
    }

    @Override
    public String toString() {
        return coordinate.toString() + ":" + value;
    }


    public TypeCell getType() {
        return type;
    }

    public boolean isEmpty() {
        return value == 0;
    }
}

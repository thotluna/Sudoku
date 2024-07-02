package models;

import types.TypeCell;

public class Game {
    private Board initial;
    private Board solution;
    private Board board;

    public Game() {
        this.start();
    }

    public void start(){
        solution = new Board();
        initial = new Board();
        board = new Board();
    }

    public void restart() {
        board = initial;
    }

    public void addCells(Board initialBoard) {
        assert initialBoard != null;
        initial = initialBoard;
        board = initialBoard;
    }

    public Board getBoard() {
        return board;
    }

    public void setSolution(Board solution) {
        this.solution = solution;
    }

    public boolean hasGame() {
        return initial != null;
    }

    public boolean isComplete() {
        return board.isComplete();
    }

    public boolean isAvailableCell(String coordinateString) {
        Coordinate coordinate = new Coordinate(coordinateString);
        return board.isBusyCell(coordinate);
    }

    public void addCell(Cell cell) {
        assert board.isNullCell(cell.coordinate())
                ||  board.getCell(cell.coordinate()).type() != TypeCell.FIXED;
        board.addCell(cell);
    }

    public Board getSolution(){
        return solution;
    }
}

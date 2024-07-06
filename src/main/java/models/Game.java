package models;

import types.TypeCell;

public class Game {
    private Board initial;
    private Board solution;
    private Board board;

    private int helpAvailable;

    public Game() {
        this.start();
    }

    public Game(Board initial, Board solution, Board board) {
        this.initial = initial;
        this.solution = solution;
        this.board = board;
        helpAvailable = 3;
    }

    public void start(){
        solution = new Board();
        initial = new Board();
        board = new Board();
        helpAvailable = 3;
    }

    public void restart() {
        assert !initial.isEmptyComplete();
        board = initial.newCopy();
        helpAvailable = 3;
    }

    public void addCells(Board initialBoard) {
        assert initialBoard != null;
        initial = initialBoard.newCopy();
        board = initialBoard.newCopy();
    }

    public boolean hasGame() {
        return !initial.isEmptyComplete();
    }

    public boolean isGameOver() {
        return board.isComplete();
    }

    public Board getBoard() {
        return board;
    }

    public void setSolution(Board solution) {
        this.solution = solution;
    }

    public Board getSolution(){
        return solution;
    }

    public Board getInitial() {
        return this.initial;
    }

    public Memento createMemento() {
        return new Memento(board.newCopy());
    }

    public void addCell(Cell cell){
        verifyClean(cell.coordinate(), cell.value());
        board.addCell(cell);
    }

    public void setMemento(Memento memento) {
        for (int row = 0; row < board.getDimension(); row++) {
            for (int col = 0; col < board.getDimension(); col++) {
                board.addCell(memento.getBoard().getCell(row, col));
            }
        }
    }

    public Cell getCell(int row, int column) {
        return board.getCell(row, column);
    }

    public void load(Game game) {
        initial = game.initial.newCopy();
        solution = game.solution.newCopy();
        board = game.board.newCopy();
    }

    public void helpCell(Coordinate coordinate) {
        Cell cell = solution.getCell(coordinate);
        if(cell.type() != TypeCell.FIXED){
            board.addCell(new Cell(cell.coordinate(), cell.value(), TypeCell.HELP));
            helpAvailable--;
            verifyRepeat(cell.coordinate(),cell.value());
        }
    }

    private void verifyRepeat(Coordinate coordinate, int value) {
        for (Cell cell: board.getCellsError(coordinate, value)) {
            board.addCell(new Cell(cell.coordinate(), cell.value(), TypeCell.ERROR));
        }
    }

    private void verifyClean(Coordinate coordinate, int value) {

        for (Cell cell: board.verifyClean(coordinate, value)) {
            board.addCell(new Cell(cell.coordinate(), cell.value(), TypeCell.CANDIDATE));
        }
    }

    public boolean hasHelp() {
        return helpAvailable > 0;
    }

    public int getHelpAvailable() {
        return helpAvailable;
    }
}

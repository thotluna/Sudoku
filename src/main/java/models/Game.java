package models;

public class Game {
    private Board initial;
    private Board solution;
    private Board board;

    public Game() {
        this.start();
    }

    public Game(Board initial, Board solution, Board board) {
        this.initial = initial;
        this.solution = solution;
        this.board = board;
    }

    public void start(){
        solution = new Board();
        initial = new Board();
        board = new Board();
    }

    public void restart() {
        assert !initial.isEmptyComplete();
        board = initial.newCopy();
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
}

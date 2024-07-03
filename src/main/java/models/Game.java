package models;

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
        return board.isComplete() && board.equals(solution);
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
}

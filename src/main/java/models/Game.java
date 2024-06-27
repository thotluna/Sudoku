package models;

import java.util.List;
import java.util.Objects;

public class Game {

    private List<Cell> initial;
    private int[][] solution;
    private final int[][] board;

    public Game() {
        board = new int[9][9];
    }

    public void restart() {
        setBoard(initial);
    }

    private void setBoard(List<Cell> cells){
        assert !cells.isEmpty();
        assert cells.stream().noneMatch(Objects::nonNull);

        for (Cell cell: cells){
            assert cell != null;
            board[cell.getRow()][cell.getColumn()] = cell.getValue();
        }

    }

    public void addCells(List<Cell> cells) {
        assert !cells.isEmpty();
        assert cells.stream().noneMatch(Objects::nonNull);
        initial = cells;
        setBoard(cells);
    }

    public int[][] getBoard() {
        return board;
    }

    public void setSolution(int[][] solution) {
        this.solution = solution;
    }

}

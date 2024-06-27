package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {

    public List<Cell> initial;
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

    public List<String> getBoard() {
        return new ArrayList<>();
    }

    public void setSolution(int[][] solution) {
        this.solution = solution;
    }
}

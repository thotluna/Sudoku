package models;

import types.TypeCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {
    private List<Cell> initial;
    private int[][] solution;
    private Cell[][] board;

    public Game() {
        this.start();
    }

    public void start(){
        clearBoard();
        solution = new int[9][9];
        initial = new ArrayList<>();
    }

    public void restart() {
        clearBoard();
        setBoard(initial);
    }

    private void clearBoard(){
        this.board = new Cell[9][9];
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                board[row][column] = new Cell(new Coordinate(row, column), 0, TypeCell.CANDIDATE);
            }
        }
    }

    public void addCells(List<Cell> cells) {
        assert !cells.isEmpty();
        assert cells.stream().noneMatch(Objects::isNull);
        initial = cells;
        setBoard(cells);
    }

    private void setBoard(List<Cell> cells){
        assert !cells.isEmpty();
        assert cells.stream().noneMatch(Objects::isNull);

        for (Cell cell: cells){
            assert cell != null;
            board[cell.getRow()][cell.getColumn()] = cell;
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setSolution(int[][] solution) {
        this.solution = solution;
    }

    @SuppressWarnings("unused")
    public int[][] getSolution(){ return solution; }

    public boolean hasGame() {
        return initial != null && !initial.isEmpty();
    }
}

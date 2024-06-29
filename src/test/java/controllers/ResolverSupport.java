package controllers;

import models.Cell;
import models.Coordinate;
import types.TypeCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResolverSupport {

    public int[][][] getSolvableGame() {

        return new int[][][]{
                {
                        {4, 2, 0, 0, 7, 0, 8, 0, 5},
                        {0, 7, 0, 1, 0, 5, 6, 0, 2},
                        {0, 6, 1, 0, 9, 0, 0, 0, 4},
                        {9, 0, 4, 0, 0, 2, 0, 5, 1},
                        {0, 5, 0, 3, 0, 1, 0, 6, 9},
                        {0, 1, 0, 0, 0, 9, 0, 0, 0},
                        {8, 0, 2, 0, 0, 0, 0, 0, 0},
                        {0, 9, 0, 8, 2, 4, 5, 0, 0},
                        {0, 4, 0, 9, 0, 6, 0, 2, 0}
                }, {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        }, {
                {0, 0, 0, 0, 7, 0, 0, 1, 0},
                {1, 0, 0, 0, 2, 9, 7, 0, 0},
                {3, 0, 0, 5, 0, 0, 9, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 0, 7, 4, 5, 0, 0, 0},
                {7, 0, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 0, 8, 0, 6, 0, 0},
                {6, 9, 4, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 4, 0},
        }
        };

    }

    public int[][] getNotSolvableGame() {

        return new int[][]{
                {0, 0, 0, 7, 0, 3, 0, 0, 4},
                {0, 5, 0, 9, 4, 2, 1, 0, 0},
                {9, 2, 0, 6, 0, 0, 3, 0, 0},
                {0, 0, 6, 0, 7, 0, 8, 4, 0},
                {3, 0, 5, 0, 0, 0, 0, 9, 0},
                {0, 1, 2, 8, 0, 0, 0, 0, 3},
                {0, 0, 4, 0, 8, 0, 2, 0, 0},
                {0, 0, 0, 4, 0, 5, 9, 7, 0},
                {1, 3, 0, 2, 0, 0, 4, 6, 0}
        };
    }

    public List<Cell> getSolvableGameForCells() {
        int[][] solvable = getSolvableGame()[0];
         return converteIntToListCell(solvable);
    }

    private List<Cell> converteIntToListCell(int[][] integerArray){
        List<Cell> board = new ArrayList<>();
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (integerArray[row][column] != 0) {
                    board.add(new Cell(new Coordinate(row, column), integerArray[row][column], TypeCell.FIXED));
                }
            }
        }
        return board.stream().filter(Objects::nonNull).toList();
    }

    public List<Cell> getCompleteBoardForCell() {
        int[][] board = new int[][]{
                {3, 5, 8, 4, 9, 1, 7, 2, 6},
                {2, 9, 1, 6, 7, 8, 5, 3, 4},
                {4, 6, 7, 2, 5, 3, 9, 1, 8},
                {5, 7, 2, 3, 8, 9, 6, 4, 1},
                {1, 8, 4, 7, 2, 6, 3, 5, 9},
                {9 ,3 ,6 ,5 ,1 ,4 ,8 ,7 ,2},
                {6, 4, 9, 1, 3, 7, 2, 8, 5},
                {8, 2, 3, 9, 4, 5, 1, 6, 7},
                {7, 1, 5, 8, 6, 2, 4, 9, 3},
        };

        return converteIntToListCell(board);
    }

    @SuppressWarnings("unused")
    public Cell[][] getCompleteArrayBoardForCell(){
        Cell[][] board = new Cell[9][9];
        for (Cell cell: getCompleteBoardForCell()) {
            board[cell.getRow()][cell.getColumn()] = cell;
        }

        return board;
    }

    public Cell[][] getIncompleteArrayBoardForCell(String data, TypeCell typeCell, int otherValueInRowDelete,
                                                   boolean subgridTest){
        Cell[][] board = new Cell[9][9];
        for (Cell cell: getCompleteBoardForCell()) {
            board[cell.getRow()][cell.getColumn()] = cell;
        }

        Coordinate coordinate = new Coordinate(data.split(":")[0]);
        TypeCell type = typeCell == null ? TypeCell.CANDIDATE : typeCell;

        if(otherValueInRowDelete > 0){
            Cell[] row = board[coordinate.getRow()];
            for (Cell cell :  row) {
                if(cell.getValue() == otherValueInRowDelete){
                    board[cell.getRow()][cell.getColumn()] = new Cell(new Coordinate(cell.getRow(), cell.getColumn())
                            , 0, TypeCell.CANDIDATE);
                }
            }
        }

        if(subgridTest){
            for (int row = 0; row < 9; row++) {
                if(board[row][coordinate.getColumn()].getValue() == otherValueInRowDelete){
                    board[row][coordinate.getColumn()] = new Cell(new Coordinate(coordinate.getRow(), coordinate.getColumn()), 0, TypeCell.CANDIDATE);
                }
            }
        }



        board[coordinate.getRow()][coordinate.getColumn()] = new Cell(coordinate, 0, type);

        return board;
    }


}

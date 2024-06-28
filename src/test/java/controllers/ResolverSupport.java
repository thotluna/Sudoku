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
            },{
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
            },{
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

    public List<Cell> getSolvableGameForCells(){
        int[][] solvable = getSolvableGame()[0];
        List<Cell> board = new ArrayList<>();
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
               if(solvable[row][column] != 0){
                   board.add(new Cell(new Coordinate(row, column), solvable[row][column], TypeCell.FIXED));
               }
            }
        }
        return board.stream().filter(Objects::nonNull).toList();
    }


}

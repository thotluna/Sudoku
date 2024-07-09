package controllers.generator;

import models.Board;
import models.Cell;
import models.Coordinate;
import org.junit.jupiter.api.Test;
import types.TypeCell;

import static org.junit.jupiter.api.Assertions.*;

class ResolveSudokuTest {

    Board board;
    ResolveSudoku resolve;


    @Test
    void GiveBoardExtreme_WhenResolveBoardCalled_ThenSolved(){
        int[][] data = new int[][]{
                {0,2,0,0,3,0,0,8,4},
                {5,9,4,0,0,7,0,1,0},
                {0,1,3,0,0,0,0,5,0},
                {0,0,0,2,1,8,0,0,0},
                {2,0,8,0,0,0,1,0,6},
                {1,7,5,0,9,6,0,0,0},
                {0,0,1,0,6,2,0,7,8},
                {7,3,0,0,8,5,0,0,0},
                {9,0,2,0,0,0,5,6,1}
        };
        board = createBoard(data);
        resolve = new ResolveSudoku();
        boolean solved =  resolve.solved(board);
        assertTrue(solved);
    }

    @Test
    void GiveBoardMedium_WhenResolveBoardCalled_ThenSolved(){
        int[][] data = new int[][]{
                {4,0,0,6,0,0,3,0,0},
                {0,5,0,0,0,4,2,0,0},
                {0,0,0,0,5,7,0,0,0},
                {9,0,0,2,0,0,0,0,1},
                {5,0,0,0,0,0,0,0,7},
                {0,6,0,7,1,0,0,0,8},
                {6,0,0,1,0,0,9,0,0},
                {0,0,8,4,0,0,0,0,2},
                {0,0,0,0,0,0,0,0,0}
        };
        board = createBoard(data);
        resolve = new ResolveSudoku();
        boolean solved =  resolve.solved(board);
        assertTrue(solved);
    }

    private Board createBoard(int[][] base){
        Board nBoard = new Board();
        for (int row = 0; row < base.length; row++) {
            for (int col = 0; col < base.length; col++) {
                nBoard.addCell(new Cell(new Coordinate(row, col), base[row][col], TypeCell.FIXED), true);
            }
        }
        return nBoard;
    }
}
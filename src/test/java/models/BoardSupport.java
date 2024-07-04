package models;

public class BoardSupport {

    public static Board getBoardComplete(){
        int[][] matrixValue = new int[][]{
                {4,7,9,6,2,1,3,8,5},
                {1,5,6,3,8,4,2,7,9},
                {8,3,2,9,5,7,1,6,4},
                {9,8,7,2,4,6,5,3,1},
                {5,4,1,8,9,3,6,2,7},
                {2,6,3,7,1,5,4,9,8},
                {6,2,5,1,7,8,9,4,3},
                {3,1,8,4,6,9,7,5,2},
                {7,9,4,5,3,2,8,1,6}
        };

        return matrixToBoard(matrixValue);
    }

    public static Boards getBoardSet(){
        int[][] initial = new int[][]{
            {0,6,0,0,0,0,0,0,8},
            {0,0,8,1,0,7,0,0,4},
            {5,0,4,0,6,3,0,0,0},
            {2,0,3,4,0,0,0,5,7},
            {0,4,5,0,0,9,0,0,6},
            {0,0,0,5,7,0,0,0,0},
            {0,0,0,7,0,5,6,0,0},
            {0,0,0,0,2,0,3,4,5},
            {4,5,6,9,0,0,7,2,0}
        };

        int[][] solution = new int[][]{
                {9,6,7,2,5,4,1,3,8},
                {3,2,8,1,9,7,5,6,4},
                {5,1,4,8,6,3,9,7,2},
                {2,9,3,4,1,6,8,5,7},
                {7,4,5,3,8,9,2,1,6},
                {6,8,1,5,7,2,4,9,3},
                {1,3,2,7,4,5,6,8,9},
                {8,7,9,6,2,1,3,4,5},
                {4,5,6,9,3,8,7,2,1}
        };

        return new Boards(matrixToBoard(initial), matrixToBoard(solution));
    }

    private static Board matrixToBoard(int[][] matrix){
        Board board = new Board();
        for (int row = 0; row < Board.DIMENSION_DEFAULT; row++) {
            for (int col = 0; col < Board.DIMENSION_DEFAULT; col++) {
                 board.addCell(Cell.newCellCandidate(row, col, matrix[row][col]));
            }
        }
        return board;
    }
}


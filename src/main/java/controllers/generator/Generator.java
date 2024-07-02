package controllers.generator;

import controllers.GeneratorBoard;
import models.Board;
import models.Cell;
import models.Coordinate;
import types.TypeCell;

import java.util.*;

public class Generator implements GeneratorBoard {
    private final Board boardInitial;
    private Board boardSolution;

    public Generator() {
        boardInitial = new Board();
        boardSolution = new Board();
    }

    @Override
    public void generateSudoku(){
        boolean solved;
        ResolveSudoku resolve = new ResolveSudoku();
        do{
            boardInitial.clear();
            boardSolution.clear();
            generateSeek();
            solved = resolve.solved(boardInitial.newCopy());

        }while (!solved);

        boardSolution = resolve.getBoard();


    }

    private void generateSeek() {
        int nCell = 0;
        do{
            int row = new Random().nextInt(Board.DIMENSION_DEFAULT);
            int col = new Random().nextInt(Board.DIMENSION_DEFAULT);
            int value = new Random().nextInt(Board.DIMENSION_DEFAULT) + 1;
            Coordinate coordinate = new Coordinate(row, col);
            if(this.validate(value, coordinate )){
                this.boardInitial.addCell(new Cell(coordinate, value, TypeCell.FIXED), true);
                nCell++;
            }

        }while (nCell  < 50);

    }

    private boolean validate(int value, Coordinate coordinate){
        return !boardInitial.hasValueInRow(value, coordinate.getRow()) &&
                !boardInitial.hasValueInColumn(value, coordinate.getColumn()) &&
                !boardInitial.hasValueInSubstring(value, coordinate);
    }

    @Override
    public Board getBoardInitial() {
        return boardInitial;
    }

    @Override
    public Board getBoardSolution() {
        return boardSolution;
    }

    public static void main(String[] args) {
        Generator generator = new Generator();
        generator.generateSudoku();

        generator.boardInitial.print();
        generator.boardSolution.print();
    }
}

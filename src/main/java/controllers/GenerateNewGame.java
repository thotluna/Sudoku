package controllers;

import controllers.validators.GenerateValidatorsFactory;
import controllers.validators.Validator;
import models.Cell;
import models.Coordinate;
import types.TypeCell;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GenerateNewGame {

    private List<Cell> initial;
    private int[][] solution;

    public List<Cell> generate(){
        List<Cell> cells = new ArrayList<>();
        return this.generateListCellValidValid(cells);
    }

    private List<Cell> generateListCellValidValid(List<Cell> cells){
        List<Cell> nCells = this.operate(cells).stream().filter(Objects::nonNull).toList();
        if(nCells.isEmpty()) {
            return nCells;
        }
        Resolver resolver = new Resolver();
        resolver.solveBoard(this.convertCellsToArray(nCells));
        solution = resolver.getSolution();
        initial = nCells;
        return nCells;
    }

    private List<Cell> operate(List<Cell> cells) {

        for (int row = 0; row < 9; row++) {
            int[] columns = getIntRandom(new Random().nextInt(4) + 2);
            for (int column : columns) {
                Coordinate coordinate = new Coordinate(row, column);
                Cell cell = getNewCellValid(coordinate, cells);
                if(cell != null){
                    cells.add(getNewCellValid(coordinate, cells));
                }else {
                    return new ArrayList<>();
                }
            }

        }

        return cells;
    }

    private Cell getNewCellValid(Coordinate coordinate, List<Cell> cells){
        Validator<Cell> validator = new GenerateValidatorsFactory().getCellValidator();
        Cell cell;
        int attempts = 0;
        do{
            int value = new Random().nextInt(9) + 1;
            cell = new Cell(coordinate, value, TypeCell.FIXED);
            attempts++;
            if(attempts > 10){
                return null;
            }
        }while (validator.validate(cell, cells) != null);

        return cell;
    }

    private int[] getIntRandom(int numberOfRandomNumber){
        int[] candidates = new int[numberOfRandomNumber];
        for (int i = 0; i < numberOfRandomNumber; i++) {
            candidates[i] = new Random().nextInt(8) + 1;
        }
        return candidates;
    }

    private int[][]  convertCellsToArray(List<Cell> cells){
        int[][] aCells = new int[9][9];
        for (Cell cell: cells) {
            if(cell != null){
                aCells[cell.getRow()][cell.getColumn()] = cell.getValue();
            }
        }
        return aCells;
    }

    public List<Cell> getInitial() {
        return initial;
    }

    public int[][] getSolution() {
        return solution;
    }
}

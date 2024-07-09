package controllers;

import models.Board;
import models.Cell;
import models.Coordinate;
import models.Session;
import types.TypeCell;

public class GameController implements Controller, Ejectable {
    private final Session session;

    private final SaveController saveController;

    private Boolean finishGame;

    public GameController(Session session, SaveController saveController) {
        this.saveController = saveController;
        this.session = session;
        this.finishGame = false;
    }

    @Override
    public void accept(ControllerVisitor view) {
        view.visit(this);
    }

    public void nextState() {
        session.nextState();
    }


    public Board getBoard() {
        return session.getBoard();
    }

    public boolean isGameOver(){
        return session.isGameComplete() || finishGame;
    }

    public boolean isNotGameOver(){
        return !isGameOver();
    }


    public boolean isValidCell(String coordinate) {
        return !session.isCellBusy(coordinate);
    }

    public void addCell(String data) {
        Coordinate coordinate = new Coordinate(data.split(":")[0]);
        String valueType = data.contains(":")
                ? data.split(":")[1]
                : null;
        int value = data.contains(":") && data.length() > 3
                ? Integer.parseInt(String.valueOf(data.charAt(3)))
                : 0;

        TypeCell type;

        if (data.contains(":")){
            if (valueType != null && valueType.length() == 2)
                type = valueType.charAt(1) == 'h' ? TypeCell.HIGHLIGHT : TypeCell.CANDIDATE;
            else type = TypeCell.CANDIDATE;
        }else{
            type = data.length() == 3 && data.charAt(2) == 'h'
                    ? TypeCell.HIGHLIGHT
                    : TypeCell.CANDIDATE;
        }

        session.addCell(new Cell(coordinate, value , type));

    }

    public void addCell(Cell cell){
        session.addCell(cell);
    }

    public Board getSolution() {
        return session.getSolution();
    }

    public SaveController getSaveController(){
        return saveController;
    }

    public void undo() {
        session.undo();
    }

    public boolean isUndoable(){
        return session.isUndoable();
    }

    public void redo() {
        session.redo();
    }

    public boolean isRedoable(){
        return session.isRedoable();
    }


    public void register() {
        session.register();
    }

    @Override
    public void exit() {
        finishGame = true;
    }

    public boolean isComplete() {
        return session.isGameComplete();
    }

    public void resetIsComplete() {
        finishGame = false;
    }

    public void helpCell(String data) {
        Coordinate coordinate = new Coordinate(data.substring(0,2));
        session.helpCell(coordinate);
    }

    public boolean hasHelp() {
        return session.hasHelp();
    }

    public int getHelpAvailable() {
        return session.getHelpAvailable();
    }

    public void addCellHighlight(String data) {
        Coordinate coordinate = new Coordinate(data.substring(0, 2));
        Cell cell = session.getCell(coordinate);
        TypeCell type = cell.type() == TypeCell.CANDIDATE
                ? TypeCell.HIGHLIGHT
                : TypeCell.CANDIDATE;
        if(data.contains("/")){
            int value = Integer.parseInt(data.substring(3, 4));
            cell = new Cell(cell.coordinate(), value, type);
        }else{
            cell = new Cell(cell.coordinate(), cell.value(), type);
        }
        addCell(cell);
    }

    public void deleteCell(String data) {
        Coordinate coordinate = new Coordinate(data.substring(0, 2));
        addCell(Cell.nullCell(coordinate));
    }

    public boolean hasValueInColumn(int value, int column) {
        return session.hasValueInColumn(value, column);
    }

    public boolean hasValueInRow(int value, int row) {
        return session.hasValueInRow(value, row);
    }

    public boolean hasValueInSubstring(int value, Coordinate coordinate) {
        return session.hasValueInSubstring(value, coordinate);
    }
}

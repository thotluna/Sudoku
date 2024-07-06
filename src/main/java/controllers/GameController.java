package controllers;

import models.Board;
import models.Cell;
import models.Coordinate;
import models.Session;
import types.TypeCell;

public class GameController implements Controller, Ejectable {
    private final Session session;

    private final SaveController saveController;
    private Board board;

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

    public void loadBoard() {
        this.board = session.getBoard();
    }

    public Board getBoard() {

        return board;
    }

    public boolean isGameOver(){
        return session.isGameComplete() || finishGame;
    }

    public boolean isNotGameOver(){
        return !isGameOver();
    }


    public boolean isValidCell(String coordinate) {
        return !board.isBusyCell(coordinate);
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
}

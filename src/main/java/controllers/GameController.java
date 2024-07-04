package controllers;

import models.Board;
import models.Cell;
import models.Coordinate;
import models.Session;
import types.TypeCell;

public class GameController implements Controller {
    private final Session session;

    private final SaveController saveController;
    private Board board;

    public GameController(Session session, SaveController saveController) {
        this.saveController = saveController;
        this.session = session;
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
        return session.isGameComplete();
    }

    public boolean isNotGameOver(){
        return !isGameOver();
    }


    public boolean isValidCell(String coordinate) {
        return !board.isBusyCell(coordinate);
    }

    public void addCell(String data) {
        Coordinate coordinate = new Coordinate(data.split(":")[0]);
        int value  = Integer.parseInt(data.split(":")[1]);
        board.addCell(new Cell(coordinate, value , TypeCell.CANDIDATE));
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


}

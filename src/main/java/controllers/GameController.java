package controllers;

import models.Board;
import models.Cell;
import models.Coordinate;
import models.Session;
import types.TypeCell;

public class GameController implements Controller {
    private final Session session;

    public GameController(Session session) {
        this.session = session;
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
        return session.isGameComplete();
    }

    public boolean isNotGameOver(){
        return !isGameOver();
    }


    public boolean isValidCell(String coordinate) {
        return session.isAvailableCell(coordinate);
    }

    public void addCell(String data) {
        Coordinate coordinate = new Coordinate(data.split(":")[0]);
        int value  = Integer.parseInt(data.split(":")[1]);
        session.addCell(new Cell(coordinate, value , TypeCell.CANDIDATE));
    }

    public Board getSolution() {
        return session.getSolution();
    }
}

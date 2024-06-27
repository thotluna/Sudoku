package controllers;

import models.Cell;
import models.Session;

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

    public Cell[][] getBoard() {
        return session.getBoard();
    }

    public boolean isFinish() {
        return false;
    }


}

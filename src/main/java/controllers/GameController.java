package controllers;

import models.Session;

public class GameController implements Controller {
    private Session session;

    public GameController(Session session) {
        this.session = session;
    }

    @Override
    public void accept(ControllerVisitor view) {
        view.visit(this);
    }
}

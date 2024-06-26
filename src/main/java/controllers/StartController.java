package controllers;

import models.Session;

public class StartController implements Controller {

    private final Session session;

    public StartController(Session session) {
        this.session = session;
    }

    @Override
    public void accept(ControllerVisitor view) {
        assert view != null;
        view.visit(this);
    }

    public void endState() {
        session.endState();
    }

    public void createNewGame() {
        GenerateNewGame generator = new GenerateNewGame();
    }
}

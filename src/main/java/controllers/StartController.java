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

    public boolean createNewGame() {
        GenerateNewGame generator = new GenerateNewGame();

        boolean success = !generator.generate().isEmpty();
        if (success){
            session.setCells(generator.getInitial());
            session.setSolution(generator.getSolution());
            session.inGameState();

        }
        return success;
    }

    public void loadGame() {
        session.nextState();
    }

    public void inGameState() {
        session.inGameState();
    }
}

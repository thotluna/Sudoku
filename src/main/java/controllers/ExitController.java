package controllers;

import models.Session;

public class ExitController implements Controller, Ejectable {
    private final Session session;

    public ExitController(Session session) {
        this.session = session;
    }

    @Override
    public void accept(ControllerVisitor view) {
        view.visit(this);
    }

    public void restart() {
        session.restart();
    }

    public void start() {
        session.start();
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    public boolean hasGame() {
        return session.hasGame();
    }
}

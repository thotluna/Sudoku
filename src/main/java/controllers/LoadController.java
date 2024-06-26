package controllers;

import models.Session;

public class LoadController implements Controller {
    private Session session;

    public LoadController(Session session) {
        this.session = session;
    }

    @Override
    public void accept(ControllerVisitor view) {
        view.visit(this);
    }
}

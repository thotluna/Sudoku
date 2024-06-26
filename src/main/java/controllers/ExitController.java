package controllers;

import models.Session;

public class ExitController implements Controller {
    private Session session;

    public ExitController(Session session) {
        this.session = session;
    }

    @Override
    public void accept(ControllerVisitor view) {

    }
}

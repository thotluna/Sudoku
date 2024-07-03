package controllers;

import models.Session;

public class SaveController {

    private Session session;

    public SaveController(Session session) {
        this.session = session;
    }

    public void saveGame(){

    }

    public boolean hasGame() {
        return session.hasGame();
    }
}

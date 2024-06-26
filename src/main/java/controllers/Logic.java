package controllers;

import models.Game;
import models.Session;
import models.StateValue;

import java.util.HashMap;
import java.util.Map;

public class Logic {

    private final Map<StateValue, Controller> controllers;
    private final Session session;

    public Logic() {
        Game game = new Game();
        this.session = new Session(game);
        this.controllers = new HashMap<>();
        this.controllers.put(StateValue.INITIAL, new StartController(this.session));
        this.controllers.put(StateValue.LOAD, new LoadController(this.session));
        this.controllers.put(StateValue.IN_GAME, new GameController(this.session));
        this.controllers.put(StateValue.OUT_GAME, new ExitController(this.session));
    }

    public boolean hasController() {
        return this.getController() != null;
    }

    public Controller getController() {
        return this.controllers.get(this.session.getStateValue());
    }
}

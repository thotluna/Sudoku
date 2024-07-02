package controllers;

import controllers.generator.Generator;
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

    public void inGameState() {
        session.inGameState();
    }

    public void endState() {
        session.endState();
    }

    public void createNewGame() {
        GeneratorBoard generator = new Generator();
        generator.generateSudoku();

        session.setBoardInitial(generator.getBoardInitial());
        session.setSolution(generator.getBoardSolution());
        session.inGameState();
    }

    public void loadGame() {
        session.nextState();
    }

}

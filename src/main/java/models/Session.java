package models;

import java.util.List;

public class Session {

    private final State state;
    private final Game game;

    public Session(Game game) {
        this.state = new State();
        this.game = game;
    }

    public void restart(){
        this.state.restart();
        this.game.restart();
    }

    public StateValue getStateValue() {
        return state.getStateValue();
    }

    public void nextState(){
        this.state.nextState();
    }

    public void addCells(List<Cell> cells){
        game.addCells(cells);
    }

    public List<String> getBoard(){
        return this.game.getBoard();

    }

    public void endState() {
        state.endState();
    }
}
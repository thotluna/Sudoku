package models;

public class State {

    private StateValue state;

    public State() {
        this.start();
    }

    public void start(){
        this.state = StateValue.INITIAL;
    }

    public void restart() {
        this.state = StateValue.IN_GAME;
    }

    public StateValue getStateValue(){
        return state;
    }

    public void nextState(){
        assert this.state.ordinal() < StateValue.values().length - 1;
        this.state = StateValue.values()[this.state.ordinal() + 1];
    }

    public void endState() {
        state = StateValue.OUT_GAME;
    }

    public void inGameState() {
        state = StateValue.IN_GAME;
    }
}

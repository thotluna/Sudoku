package models;

public class Session {

    private final State state;
    private final Game game;

    public Session(Game game) {
        this.state = new State();
        this.game = game;
    }

    public void start() {
        this.game.start();
        this.state.start();
    }

    public void restart(){
        assert game.hasGame();
        this.game.restart();
        this.state.restart();
    }

    public void nextState(){
        this.state.nextState();
    }

    public void endState() {
        state.endState();
    }

    public void inGameState() {
        state.inGameState();
    }

    public StateValue getStateValue() {
        return state.getStateValue();
    }

    public boolean hasGame() {
        return game.hasGame();
    }

    public boolean isGameComplete() {
        return game.isGameOver();
    }

    public Board getBoard(){
        return this.game.getBoard();
    }

    public void setBoardInitial(Board initial) {
        game.addCells(initial);
    }

    public void setSolution(Board solution) {
        game.setSolution(solution);
    }

    public Board getSolution() {
        return game.getSolution();
    }

    public Game getGame() {
        return game;
    }
}

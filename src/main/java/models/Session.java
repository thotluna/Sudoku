package models;

import java.util.List;

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

    public void setCells(List<Cell> initial) {
        game.addCells(initial);
    }

    public void setSolution(int[][] solution) {
        game.setSolution(solution);
    }

    public boolean hasGame() {
        return game.hasGame();
    }

    public Cell[][] getBoard(){
        return this.game.getBoard();
    }

    public boolean isGameComplete() {
        return game.isComplete();
    }

    public boolean isAvailableCell(String coordinate) {
        return game.isAvailableCell(coordinate);
    }

    public void addCell(Cell cell) {
        game.addCell(cell);
    }
}

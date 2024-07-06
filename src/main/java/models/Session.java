package models;

public class Session {

    private final State state;
    private final Game game;

    private final Register register;

    public Session(Game game) {
        this.state = new State();
        this.game = game;
        this.register = new Register(game);
    }

    public void start() {
        this.game.start();
        this.state.start();
    }

    public void restart(){
        assert game.hasGame();
        this.game.restart();
        register();
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
        register();
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

    public void loadGame(Game game){
        setBoardInitial(game.getBoard());
        setSolution(game.getSolution());
        register();
    }

    public void undo() {
        register.undo();

    }

    public boolean isUndoable() {
        return register.undoable();
    }

    public void redo() {
        register.redo();
    }

    public boolean isRedoable() {
        return register.redoable();
    }

    public void addCell(Cell cell) {
        game.addCell(cell);
    }

    public void register() {
        register.register();
    }

    public void helpCell(Coordinate coordinate) {
        game.helpCell(coordinate);
    }

    public boolean hasHelp() {
        return game.hasHelp();
    }

    public int getHelpAvailable() {
        return game.getHelpAvailable();
    }
}

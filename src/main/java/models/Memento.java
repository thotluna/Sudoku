package models;

public class Memento {

    private final Board board;

    public Memento(Board boards) {
        this.board = boards;
    }

    public Board getBoard() {
        return board;
    }
}

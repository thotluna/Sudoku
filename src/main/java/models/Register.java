package models;

import java.util.ArrayList;
import java.util.List;

public class Register {

    private final List<Memento> mementos;
    private final Game game;
    private int firstPreview;

    public Register(Game game) {
        this.game = game;
        mementos = new ArrayList<>();
        reset();
    }

    @SuppressWarnings("ListRemoveInLoop")
    public void register(){
        for (int i = 0; i < firstPreview; i++) {
            mementos.remove(0);
        }
        this.firstPreview = 0;
        mementos.add(firstPreview, game.createMemento());
    }

    public void reset(){
        this.mementos.clear();
        firstPreview = 0;
    }

    public void undo() {
        assert undoable();
        this.firstPreview++;
        this.game.setMemento(mementos.get(firstPreview));
    }

    public boolean undoable(){
        return !mementos.isEmpty() && this.firstPreview < this.mementos.size() - 1;
    }


    public void redo() {
        assert redoable();
        this.firstPreview--;
        this.game.setMemento(mementos.get(firstPreview));
    }

    public boolean redoable(){
        return this.firstPreview >= 1;
    }
}

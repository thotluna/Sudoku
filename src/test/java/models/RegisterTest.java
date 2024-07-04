package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class RegisterTest {

    Register register;

    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        register = new Register(game);
        register.register();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void GiveANewGame_WhenUndoableCall_ReturnFalse(){
        assertThat(register.undoable(), is(false));
    }

    @Test
    void GiveANewGame_WhenRedoableCall_ReturnFalse(){
        assertThat(register.redoable(), is(false));
    }

    @Test
    void GiveGameWithOneCellAdd_WhenUndoableCall_ReturnTrue(){
        game.addCell(Cell.newCellCandidate(0,0,1));
        register.register();

        assertThat(register.undoable(), is(true));
    }

    @Test
    void GiveGameUndoOneCell_WhenRedoableCall_ReturnTrue(){
        game.addCell(Cell.newCellCandidate(0,0,1));
        register.register();

        register.undo();

        assertThat(register.redoable(), is(true));
    }

    @Test
    void GiveGameWithA1Value1_WhenUndoCall_ThenCellA1Value0(){
        Cell cell = Cell.newCellCandidate(0,0,1);
        game.addCell(cell);
        register.register();

        assertThat(game.getCell(cell.getRow(), cell.getColumn()), is(equalTo(cell)));
        assertThat(register.undoable(), is(true));

        register.undo();
        assertThat(game.getCell(cell.getRow(), cell.getColumn()), not(equalTo(cell)));
        assertThat(game.getCell(cell.getRow(), cell.getColumn()), is(Cell.nullCell(cell.getRow(), cell.getColumn())));
    }

    @Test
    void GiveGameWithA1Value1Undo_WhenRedoCall_ThenCellA1Value1(){
        Cell cell = Cell.newCellCandidate(0,0,1);
        game.addCell(cell);
        register.register();

        register.undo();
        assertThat(game.getCell(cell.getRow(), cell.getColumn()), not(equalTo(cell)));

        register.redo();
        assertThat(game.getCell(cell.getRow(), cell.getColumn()), is(cell));
    }

    @Test
    void GiveGame_WhenResetCall_ThenCantUndoCall(){
        Cell cell = Cell.newCellCandidate(0,0,1);
        game.addCell(cell);
        register.register();

        register.reset();
        assertThat(register.undoable(), is(false));
    }

}
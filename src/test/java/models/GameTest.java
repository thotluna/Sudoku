package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.BoardSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class GameTest {

    Game game;
    Boards boards;

    @BeforeEach
    void setUp() {
        game = new Game();
        boards = BoardSupport.getBoardSet();
        game.addCells(boards.initial());
        game.setSolution(boards.solution());

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void GiveANewGame_WhenAddCells_ThenHaveAInitialBoardSolutionBoardAndBoard(){
        assertThat(game.getBoard(), is(equalTo(boards.initial())));
        assertThat(game.getSolution(), is(equalTo(boards.solution())));
    }

    @Test
    void GiveGame_WhenStart_ThenHaveAInitialBoardSolutionBoardAndBoardEmpty(){
        Game game = new Game();

        assertThat(game.getBoard().isEmptyComplete(), is(true));
        assertThat(game.getSolution().isEmptyComplete(), is(true));
    }

    @Test
    void GiveGame_WhenRestartCalled_ThenHaveAInitialBoardSolutionBoardAndBoardRestart(){
        Cell cell = Cell.newCellCandidate(0, 0, 9);
        game.getBoard().addCell(cell);

        assertThat(game.getBoard(), not(equalTo(boards.initial())));

        game.restart();

        assertThat(game.getBoard(), is(equalTo(boards.initial())));
    }

    @Test
    void GiveGameNoEmpty_WhenHasGameCalled_ThenReturnTrue(){
        assertThat(game.hasGame(), is(true));
        game.start();
        assertThat(game.hasGame(), is(false));
    }

    @Test
    void GiveBoardFull_WhenIsGameOverCalled_ThenReturnTrue(){
        game = new Game();
        game.setSolution(boards.solution());
        game.addCells(boards.solution().newCopy());

        assertThat(game.isGameOver(), is(true));
    }


}
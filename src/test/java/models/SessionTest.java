package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.BoardSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class SessionTest {

    Session session;

    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        game.addCells(BoardSupport.getBoardSet().initial());
        game.setSolution(BoardSupport.getBoardSet().solution());
        session = new Session(game);
    }

    @Test
    void GiveStartGame_WhenSessionCreated_ThenStateInitAndGameBoarBlank(){
        Game game = new Game();
        Session session = new Session(game);

        assertThat(session.getStateValue(), is(StateValue.INITIAL));
        assertThat(session.hasGame(), is(false));
    }

    @Test
    void GiveAnOngoingGame_WhenSessionRestartCalled_ThenReloadBoardAndState(){
        Coordinate coordinate = new Coordinate("A1");
        game.getBoard().addCell(Cell.newCellCandidate(coordinate.getRow(), coordinate.getColumn(), 9));
        Board boardOngoingGame = game.getBoard().newCopy();

        session.restart();

        assertThat(game.hasGame(), is(true));
        assertThat(game.getBoard(), not(equalTo(boardOngoingGame)));
        assertThat(game.getBoard().isNullCell(coordinate), is(true));
        assertThat(session.getStateValue(), is(StateValue.IN_GAME));

    }

}
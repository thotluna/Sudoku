package models;

import controllers.ResolverSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

class SessionTest {

    Session session;

    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        session = new Session(game);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void GiveStartGame_WhenSessionCreated_ThenStateInitAndGameBoarBlank(){
        assertThat(session.getStateValue(), is(StateValue.INITIAL));
        assertThat(session.hasGame(), is(false));
        assertThat(session.getBoard(), is(new ContainsValidCellsMatcher()));
    }

    @Test
    void GiveRestartGame_WhenSessionRestarted_ThenStateInGameAndNotGameBoarBlank(){
        ResolverSupport support = new ResolverSupport();
        session.setSolution(support.getSolvableGame()[0]);
        session.setCells(support.getSolvableGameForCells());
        session.endState();

        session.restart();

        assertThat(session.getStateValue(), is(StateValue.IN_GAME));
        assertThat(session.hasGame(), is(true));
        assertThat(session.getBoard(), is(not(new ContainsValidCellsMatcher())));
    }
}
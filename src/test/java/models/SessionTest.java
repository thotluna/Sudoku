package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class SessionTest {

    Session session;

    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        session = new Session(game);
    }

    @Test
    void GiveStartGame_WhenSessionCreated_ThenStateInitAndGameBoarBlank(){
        assertThat(session.getStateValue(), is(StateValue.INITIAL));
        assertThat(session.hasGame(), is(false));
        assertThat(session.getBoard().get(), is(new ContainsValidCellsMatcher()));
    }

}
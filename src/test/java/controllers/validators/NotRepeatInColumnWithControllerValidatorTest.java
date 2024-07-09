package controllers.validators;

import controllers.GameController;
import controllers.SaveController;
import models.Game;
import models.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import shared.BoardSupport;
import utils.models.Result;
import views.console.MessageRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class NotRepeatInColumnWithControllerValidatorTest {

    DataInputValidator validator;

    Game game;

    Session session;

    @Mock
    SaveController saveController;

    GameController controller;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {

        closeable = MockitoAnnotations.openMocks(this);

        game = new Game();
        game.addCells(BoardSupport.getBoardSet().initial());

        session = new Session(game);
        controller = new GameController(session, saveController);

        validator = new NotRepeatInColumnWithControllerValidator(controller);

    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void GiveInputDataFail_WhenNotRepeatColumnValidatorCalled_ThenShowError(){

        String[] elementsTest = new String[]{"A1/4", "a1:4"};

        for(String element : elementsTest){
            Result<String, String> result = validator.validate(element);

            assertThat(result.hasError(), is(true));
            assertThat(result.error(), equalTo(MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-column")));
        }
    }

    @Test
    void GiveInputData_WhenNotRepeatColumnValidatorCalled_ThenNoShowError(){

        String[] elementsTest = new String[]{"A1/9", "a1:3"};

        for(String element : elementsTest){
            Result<String, String> result = validator.validate(element);

            assertThat(result.hasError(), is(false));
            assertThat(result.error(), nullValue());
        }
    }
}
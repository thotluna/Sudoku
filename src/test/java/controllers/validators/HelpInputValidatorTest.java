package controllers.validators;

import controllers.GameController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import views.console.MessageRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class HelpInputValidatorTest {

    DataInputValidator validator;
    @Mock
    GameController controller;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        validator = new HelpInputValidator(controller);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void GiveInputCorrect_WhenHelpInputValidatorValidateCalled_ThenReturnNotHasError(){
        String[] inputs = new String[]{"A1.", "i9."};
        for (String input :
                inputs) {
            assertThat(validator.validate(input).hasError(), is(false));
            assertThat(validator.validate(input).error(), nullValue());
        }
    }
    @Test
    void GiveInputIncorrect_WhenHelpInputValidatorValidateCalled_ThenReturnError(){
        String[] inputs = new String[]{ "A1", "A1+", "A1-", "A1:1"};
        for (String input :
                inputs) {
            assertThat(validator.validate(input).hasError(), is(true));
            assertThat(validator.validate(input).error(),
                    equalTo(MessageRepository.getInstance().get("sudoku.input-game.error-format")));
        }
    }
}
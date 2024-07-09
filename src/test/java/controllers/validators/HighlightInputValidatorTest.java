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

class HighlightInputValidatorTest {

    DataInputValidator validator;
    @Mock
    GameController controller;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        validator = new HighlightInputValidator(controller);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void GiveInputCorrect_WhenHighlightInputValidatorValidateCalled_ThenReturnNotHasError(){
        String[] inputs = new String[]{"A1:1+", "i9/9+", "a1+", "I9+"};
        for (String input :
                inputs) {
            assertThat(validator.validate(input).hasError(), is(false));
            assertThat(validator.validate(input).error(), nullValue());
        }
    }
    @Test
    void GiveInputIncorrect_WhenHighlightInputValidatorValidateCalled_ThenReturnError(){
        String[] inputs = new String[]{"A1:1", "i9/9", "a1", "a0", "I9.", "I9-"};
        for (String input :
                inputs) {
            assertThat(validator.validate(input).hasError(), is(true));
            assertThat(validator.validate(input).error(),
                    equalTo(MessageRepository.getInstance().get("sudoku.input-game.error-format")));
        }
    }
}
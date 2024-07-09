package controllers.validators;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.console.MessageRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class FormatStringValidatorTest {

    DataInputValidator validator;

    @BeforeEach
    void setUp() {
        validator = new FormatStringValidator();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void GiveAInputStringFail_WhenValidateFormat_ReturnError(){
        String[] proof = new String[]{"1", "01/1", "J1:1", "A10", "A10:1", "A0:1", "A1-1", "A1:0" };

        for (String unit : proof) {
            assertThat(validator.validate(unit).hasError(), is(true));
        }
    }

    @Test
    void GiveAInputStringCorrect_WhenValidateFormat_ReturnNoError(){
        String[] proof = new String[]{"A1+", "A1.", "A1-", "A1/1", "A1:1", "A1/1+", "I9/9"};

        for (String unit : proof) {
            assertThat(validator.validate(unit).hasError(), is(false));
        }
    }

    @Test
    void GiveAInputStringFail_WhenValidateFormat_ReturnErrorMessage(){

        assertThat(validator.validate("1").error(), equalTo(MessageRepository.getInstance().get("sudoku.put-view.put" +
                    ".error")));
    }

    @Test
    void GiveAInputStringCorrect_WhenValidateFormat_ReturnNotErrorMessage(){

        assertThat(validator.validate("A1/1").error(), nullValue());
    }
}
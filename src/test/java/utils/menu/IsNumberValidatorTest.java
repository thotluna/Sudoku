package utils.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsNumberValidatorTest {

    Validator validator;

    static final String MESSAGE = "message";
    private static final String OTHER_MESSAGE = "other message";

    @BeforeEach
    public void setup(){
        validator = new IsNumberValidator(MESSAGE);
    }

    @Test
    void GivenALetterAsInputAndNextIsNull_WhenIsNumberValidatorCalled_ThenErrorStringReturned(){
        String error = validator.validate("a");
        assertEquals(MESSAGE, error);
    }

    @Test
    void GivenANumberAsInputAndNextIsNull_WhenIsNumberValidatorCalled_ThenNullReturned(){
        String error = validator.validate("1");
        assertNull(error);
    }

    @Test
    void GivenALetterAsInputAndNextIsNotNull_WhenIsNumberValidatorCalled_ThenErrorStringReturned(){
        Validator validator = getValidator();
        String error = validator.validate("a");
        assertEquals(MESSAGE, error);
    }

    @Test
    void GivenANumberAsInputAndNextIsNotNull_WhenIsNumberValidatorCalled_ThenNullReturned(){
        Validator validator = getValidator();
        String error = validator.validate("1");
        assertEquals(OTHER_MESSAGE, error);
    }

    private Validator getValidator(){
        Validator otherValidator = new Validator(OTHER_MESSAGE) {
            @Override
            protected String specificallyValidate(String validatable) {
                return OTHER_MESSAGE;
            }
        };
        return new IsNumberValidator(MESSAGE, otherValidator);
    }

}
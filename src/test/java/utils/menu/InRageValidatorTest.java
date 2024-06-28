package utils.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.controllers.InRageValidator;
import utils.controllers.Validator;

import static org.junit.jupiter.api.Assertions.*;

class InRageValidatorTest {

    private Validator validator;
    private final int MAX_RAGE = 3;
    private final String MESSAGE = "message";
    private final String OTHER_MESSAGE = "other message";

    @BeforeEach
    void setUp() {
        validator = new InRageValidator(MAX_RAGE, MESSAGE);
    }

    @Test
    void GivenANumberOutOfRangeAndNextIsNull_WhenOutRageValidatorCalled_ThenErrorStringReturned() {
        String[] validatables = new String[]{"0", "100"};
        for (String validatable : validatables) {
            String error = validator.validate(validatable);
            assertEquals(MESSAGE, error);
        }

    }

    @Test
    void GivenANumberInOfRangeAndNextIsNull_WhenOutRageValidatorCalled_ThenNullReturned() {
        String[] validatables = new String[]{
                "1",
                String.valueOf((int)Math.ceil(MAX_RAGE/2f)),
                String.valueOf(MAX_RAGE)
        };
        for (String validatable : validatables) {
            String error = validator.validate(validatable);
            assertNull(error, "error with validatable: " + validatable);
        }
    }

    @Test
    void GivenANumberOutOfRangeAndNextIsNotNull_WhenOutRageValidatorCalled_ThenErrorStringReturned(){
        Validator validator = getValidator();
        String[] validatables = new String[]{"0", "100"};
        for (String validatable : validatables) {
            String error = validator.validate(validatable);
            assertEquals(MESSAGE, error);
        }
    }

    @Test
    void GivenANumberInOfRangeAndNextIsNotNull_WhenOutRageValidatorCalled_ThenNullReturned() {
        Validator validator = getValidator();
        String[] validatables = new String[]{
                "1",
                String.valueOf((int)Math.ceil(MAX_RAGE/2f)),
                String.valueOf(MAX_RAGE)
        };
        for (String validatable : validatables) {
            String error = validator.validate(validatable);
            assertEquals(OTHER_MESSAGE, error);
        }
    }

    private Validator getValidator(){
        Validator otherValidator = new Validator(OTHER_MESSAGE) {
            @Override
            protected String specificallyValidate(String validatable) {
                return OTHER_MESSAGE;
            }
        };
        return new InRageValidator(MAX_RAGE, MESSAGE, otherValidator);
    }


}
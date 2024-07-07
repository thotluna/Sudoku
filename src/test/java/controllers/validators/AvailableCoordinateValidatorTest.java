package controllers.validators;

import controllers.GameController;
import models.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import views.console.MessageRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AvailableCoordinateValidatorTest {

    DataInputValidator validator;

    @Mock
    Session session;

    @InjectMocks
    GameController controller;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        validator = new AvailableCoordinateValidator(controller);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void GiveDataInputFail_WhenValidatorValidate_ThenReturnErrorCellBusy(){
        when(session.isCellBusy(any())).thenReturn(true);

        String error = String.format("%s %s", MessageRepository.getInstance().get("sudoku.put-view.put.error"),
                MessageRepository.getInstance().get("sudoku.put-view.put.error-coordinate-cell-busy"));

        assertThat(validator.validate("A1+").error(), equalTo(error));
        verify(session, times(1)).isCellBusy("A1+");
    }
}
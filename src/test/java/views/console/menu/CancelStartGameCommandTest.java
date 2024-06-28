package views.console.menu;

import controllers.StartController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import views.console.MessageRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class CancelStartGameCommandTest {

    CancelStartGameCommand command;

    @Mock
    StartController controller;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        command = new CancelStartGameCommand(MessageRepository.getInstance().get("sudoku.start-menu.cancel"),
                controller);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();

    }

    @Test
    void  GiveCancelGame_WhenExecuteCalled_ThenEndStateCalled(){
        command.execute();
        verify(controller).endState();
    }

    @Test
    void GiveCancelCommand_WhenIsActiveCalled_ThenReturnTrue(){
        assertTrue(command.isActive());
    }
}
package views.console.menu;

import controllers.StartController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import views.console.MessageRepository;
import views.console.TestAbstract;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class CancelStartGameCommandTest extends TestAbstract {

    CancelStartGameCommand command;

    @Mock
    StartController controller;

    @BeforeEach
    void setUp() {
        setUpMock();
        command = new CancelStartGameCommand(MessageRepository.getInstance().get("sudoku.start-menu.cancel"),
                controller);
    }

    @AfterEach
    void tearDown() throws Exception {
        afterTestMock();
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
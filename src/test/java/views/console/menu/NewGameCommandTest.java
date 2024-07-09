package views.console.menu;

import controllers.StartController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import views.console.MessageRepository;
import views.console.TestAbstract;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class NewGameCommandTest extends TestAbstract {
    NewGameCommand command;
    @Mock
    StartController controller;

    @BeforeEach
    void setUp() {
        setUpMock();
        command = new NewGameCommand(MessageRepository.getInstance().get("sudoku.start-menu.new"), controller);
    }

    @AfterEach
    void tearDown() throws Exception {
        afterTestMock();
    }

    @Test
    void GiveNewCommand_WhenExecuteCalled_thenCreateNewGameCalled() {
        command.execute();
        verify(controller, times(1)).createNewGame();
    }

    @Test
    void GiveNewCommand_WhenIsActiveCalled_ThenReturnTrue() {
        assertTrue(command.isActive());
    }
}
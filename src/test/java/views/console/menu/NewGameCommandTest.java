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

class NewGameCommandTest {

    NewGameCommand command;

    @Mock
    StartController controller;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        command = new NewGameCommand(MessageRepository.getInstance().get("sudoku.start-menu.new"), controller );
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void GiveNewCommand_WhenExecuteCalled_thenCreateNewGameCalled(){
        command.execute();

        verify(controller).createNewGame();
    }

    @Test
    void GiveNewCommand_WhenIsActiveCalled_ThenReturnTrue(){
       assertTrue(command.isActive());
    }
}
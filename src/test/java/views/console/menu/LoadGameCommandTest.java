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

class LoadGameCommandTest {

    LoadGameCommand command;

    @Mock
    StartController controller;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        command = new LoadGameCommand(MessageRepository.getInstance().get("sudoku.start-menu.load"), controller);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();

    }

    @Test
    void  GiveLoadGame_WhenExecuteCalled_ThenLoadGameCalled(){
        command.execute();
        verify(controller).loadGame();
    }

    @Test
    void GiveLoadCommand_WhenIsActiveCalled_ThenReturnTrue(){
        assertTrue(command.isActive());
    }
}
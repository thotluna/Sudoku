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

class LoadGameCommandTest extends TestAbstract {
    LoadGameCommand command;
    @Mock
    StartController controller;


    @BeforeEach
    void setUp() {
        setUpMock();
        command = new LoadGameCommand(MessageRepository.getInstance().get("sudoku.start-menu.load"), controller);
    }

    @AfterEach
    void tearDown() throws Exception {
        afterTestMock();
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
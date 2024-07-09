package views.console;

import controllers.StartController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import shared.CommandSupport;
import utils.Console;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StartViewTest extends TestAbstract {
    @Mock
    StartController controller;
    StartView view;

    @BeforeEach
    void setUp() {
        setUpMock();
        consoleStatic.when(Console::getInstance).thenReturn(console);
        view = new StartView();
    }

    @AfterEach
    void tearDown() throws Exception {
        afterTestMock();
    }

    @Test
    void GiveInitialApp_WhenStartViewCalled_ThenShowStartMenu() {
        when(console.readString("-> ")).thenReturn("1");
        view.interact(controller);
        verify(console).writeln(MessageRepository.getInstance().get("sudoku.start-menu"));
        verify(console).writeln(CommandSupport.createTitleCommand(
                MessageRepository.getInstance().get("sudoku.start-menu.new"), 1));
        verify(console).writeln(CommandSupport.createTitleCommand(
                MessageRepository.getInstance().get("sudoku.start-menu.load"), 2));
        verify(console).writeln(CommandSupport.createTitleCommand(
                MessageRepository.getInstance().get("sudoku.start-menu.cancel"), 3));
    }

    @Test
    void GiveInitialApp_WhenNewGameSelectedInStartView_ThenStartControllerNewGameAndChangeStateCalled() {
        when(console.readString("-> ")).thenReturn("1");
        view.interact(controller);
        verify(controller).createNewGame();
        verify(controller).inGameState();
    }

    @Test
    void GiveInitialApp_WhenLoadGameSelectedInStartView_ThenStartControllerLoadGameCalled() {
        when(console.readString("-> ")).thenReturn("2");
        view.interact(controller);
        verify(controller).loadGame();
    }

    @Test
    void GiveInitialApp_WhenExitGameSelectedInStartView_ThenStartControllerExitGameCalled() {
        when(console.readString("-> ")).thenReturn("3");
        view.interact(controller);
        verify(controller).endState();
    }
}
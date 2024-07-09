package views.console.menu;

import controllers.ExitController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import views.console.TestAbstract;

import static org.mockito.Mockito.*;
import static shared.CommandSupport.createTitleCommand;

class ExitMenuTest extends TestAbstract {
    @Mock
    ExitController controller;
    ExitMenu menu;

    private static final String TITLE = "Title menu";

    @BeforeEach
    void setUp() {
        setUpMock();
        menu = new ExitMenu(TITLE, controller);
    }

    @AfterEach
    void endSet() throws Exception {
        afterTestMock();
    }

    @Test
    void GiveInit_WhenExitMenuCalled_ThenPrintTitleMenu() {
        when(console.readString("-> ")).thenReturn("1");
        menu.execute();
        verify(console, times(1)).writeln(TITLE);
    }

    @Test
    void GiveInitAppAndExit_WhenExitMenuCalled_ThenPrintTitleCommands() {
        when(console.readString("-> ")).thenReturn("1");
        menu.execute();
        String[] titleCommands = new String[]{"Other Game", "Exit Game"};
        for (int i = 0; i < titleCommands.length; i++) {
            verify(console, times(1))
                    .writeln(createTitleCommand(titleCommands[i], i + 1));
        }
    }

    @Test
    void GiveAfterPlayAndExit_WhenExitMenuCalled_ThenPrintTitleCommands() {
        when(console.readString("-> ")).thenReturn("1");
        when(controller.hasGame()).thenReturn(true);
        menu.execute();
        String[] titleCommands = new String[]{"Restart Game", "Other Game", "Exit Game"};
        for (int i = 0; i < titleCommands.length; i++) {
            verify(console, times(1))
                    .writeln(createTitleCommand(titleCommands[i], i + 1));
        }
    }
}
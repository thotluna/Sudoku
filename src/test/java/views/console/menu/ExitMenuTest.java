package views.console.menu;

import controllers.ExitController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.Colors;
import utils.Console;

import static org.mockito.Mockito.*;

class ExitMenuTest {

    @Mock
    ExitController controller;
    @Mock
    Console console;
    ExitMenu menu;

    private static final String TITLE = "Title menu";

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        menu = new ExitMenu(TITLE, controller);
        menu.setConsole(console);
    }

    @AfterEach
    void endSet() throws Exception {
        closeable.close();
    }

    @Test
    void GiveInit_WhenExitMenuCalled_ThenPrintTitleMenu(){
        when(console.readString("-> ")).thenReturn("1");

        menu.execute();

        verify(console, times(1)).writeln(TITLE);
    }

    @Test
    void GiveInitAppAndExit_WhenExitMenuCalled_ThenPrintTitleCommands(){
        when(console.readString("-> ")).thenReturn("1");

        menu.execute();

        String[] titleCommands = new String[]{"Other Game", "Exit Game"};

        for (int i = 0; i < titleCommands.length; i++) {
            verify(console, times(1))
                    .writeln(createTitleCommand(titleCommands[i], i + 1));
        }

    }

    @Test
    void GiveAfterPlayAndExit_WhenExitMenuCalled_ThenPrintTitleCommands(){
        when(console.readString("-> ")).thenReturn("1");
        when(controller.hasGame()).thenReturn(true);

        menu.execute();

        String[] titleCommands = new String[]{"Restart Game", "Other Game", "Exit Game"};

        for (int i = 0; i < titleCommands.length; i++) {
            verify(console, times(1))
                    .writeln(createTitleCommand(titleCommands[i], i + 1));
        }

    }

    private String createTitleCommand(String title, int order){
        return String.format("%s%d.- %s%S.%s", Colors.CYAN.get(), order, Colors.BLUE.get(),
                title, Colors.DEFAULT.get());
    }

}
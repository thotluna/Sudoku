package utils.menu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.Colors;
import utils.Console;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MenuTest {
    @Mock
    Console console;
    @Mock
    CommandBase command;
    @Mock
    CommandBase command2;


    private Menu menu;
    private AutoCloseable closeable;

    private static final String TITLE_MENU = "title menu";
    private static final String TITLE_COMMAND = "title command";
    private static final String TITLE_COMMAND_2 = "title command 2";
    private static final String ERROR_NON_NUMBER = "not number";
    private static final String ERROR_OUT_RAGE = "out rage";

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        this.menu = new Menu(TITLE_MENU, ERROR_NON_NUMBER, ERROR_OUT_RAGE);
        menu.addCommand(command);
        menu.addCommand(command2);
        menu.setConsole(console);

    }

    @AfterEach
    void afterTest() throws Exception {
        closeable.close();
    }

    @Test
    void GiveCommandsListNull_WhenMenuCalled_ThenThrowError() {
        Menu menu = new Menu(TITLE_MENU);
        assertThrows(AssertionError.class, menu::execute);
    }

    @Test
    void GiveCommandsListIsNotNull_WhenMenuCalled_ThenPrintTitleConsole() {
        when(console.readString(any())).thenReturn("1");
        when(command.getTitle()).thenReturn(TITLE_COMMAND);
        when(command.isActive()).thenReturn(true);
        menu.execute();
        verify(console, times(1)).readString(any());
        verify(console, times(1)).writeln(TITLE_MENU);
    }

    @Test
    void GiveCommandsListIsNotNull_WhenMenuCalled_ThenPrintTitleCommand() {
        when(console.readString(any())).thenReturn("1");
        when(command.getTitle()).thenReturn(TITLE_COMMAND);
        when(command.isActive()).thenReturn(true);
        menu.execute();
        String titleCommand = String.format("%s%d.- %s%S.%s", Colors.CYAN.get(), 1, Colors.BLUE.get(),
                TITLE_COMMAND, Colors.DEFAULT.get());
        verify(this.console).writeln(titleCommand);
    }

    @Test
    void GiveCommandsListIsNotNull_WhenMenuCalled_ThenPrintTitleRead() {
        when(command.getTitle()).thenReturn(TITLE_COMMAND);
        when(command.isActive()).thenReturn(true);
        when(this.console.readString(any())).thenReturn("1");
        menu.execute();
        verify(this.console).readString("-> ");
    }

    @Test
    void GiveCommandsListIsNotNull_WhenMenuCalled_ThenPrintOnlyActiveCommand() {
        when(command.getTitle()).thenReturn(TITLE_COMMAND);
        when(command2.getTitle()).thenReturn(TITLE_COMMAND_2);
        when(command.isActive()).thenReturn(false);
        when(command2.isActive()).thenReturn(true);
        when(this.console.readString(any())).thenReturn("1");
        menu.execute();

        String[] titlesCommand = new String[]{
                String.format("%s%d.- %s%S.%s", Colors.CYAN.get(), 1, Colors.BLUE.get(),
                        TITLE_COMMAND, Colors.DEFAULT.get()),
                String.format("%s%d.- %s%S.%s", Colors.CYAN.get(), 1, Colors.BLUE.get(),
                        TITLE_COMMAND_2, Colors.DEFAULT.get())
        };


        verify(this.console, times(0)).writeln(titlesCommand[0]);
        verify(this.console, times(1)).writeln(titlesCommand[1]);
    }

    @Test
    void GivenALetterAsInput_WhenIsNumberValidatorCalled_ThenPrintError() {
        when(command.getTitle()).thenReturn(TITLE_COMMAND);
        when(command.isActive()).thenReturn(true);
        when(this.console.readString(any())).thenReturn("a", " ", "$", "1");

        menu.execute();

        verify(this.console, times(3)).writeError(ERROR_NON_NUMBER);
    }

    @Test
    void GivenAOutRageNumberAsInput_WhenIsNumberValidatorCalled_ThenPrintError() {
        when(command.getTitle()).thenReturn(TITLE_COMMAND);
        when(command.isActive()).thenReturn(true);
        when(command2.getTitle()).thenReturn(TITLE_COMMAND_2);
        when(command2.isActive()).thenReturn(false);
        when(console.readString(any())).thenReturn("0", "2", "1");
        menu.execute();

        verify(this.console, times(2)).writeError(ERROR_OUT_RAGE + " 1");
    }

}
package utils.menu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import utils.Colors;
import utils.Console;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MenuTest {

    private Menu menu;

    private static final String TITLE_MENU = "title menu";
    private static final String TITLE_COMMAND = "title command";

    private static final String ERROR_NON_NUMBER = "not number";
    private static final String ERROR_OUT_RAGE = "out rage";

    @Mock
    CommandBase command;

    private AutoCloseable closeable;

    @Mock
    Console console;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        this.menu = new Menu(TITLE_MENU, ERROR_NON_NUMBER, ERROR_OUT_RAGE);
        menu.addCommand(command);
    }

    @AfterEach
    void afterTest() throws Exception {
        closeable.close();
    }

    @Test
    void GiveCommandsListNull_WhenMenuCalled_ThenThrowError(){
        Menu menu =  new Menu(TITLE_MENU);
        assertThrows(AssertionError.class, menu::execute);
    }

    @Test
    void GiveCommandsListIsNotNull_WhenMenuCalled_ThenPrintTitleConsole(){
        when(command.getTitle()).thenReturn(TITLE_COMMAND);
        when(command.isActive()).thenReturn(true);

        try(MockedStatic<Console> ignored = mockStatic(Console.class)) {
            when(Console.getInstance()).thenReturn(this.console);
            when(this.console.readString(any())).thenReturn("1");

            menu.execute();

            verify(this.console).writeln(TITLE_MENU);


        }
    }

    @Test
    void GiveCommandsListIsNotNull_WhenMenuCalled_ThenPrintTitleCommand(){
        when(command.getTitle()).thenReturn(TITLE_COMMAND);
        when(command.isActive()).thenReturn(true);

        try(MockedStatic<Console> ignored = mockStatic(Console.class)) {
            when(Console.getInstance()).thenReturn(this.console);
            when(this.console.readString(any())).thenReturn("1");

            menu.execute();

            String titleCommand = String.format("%s%d.- %s%S.%s", Colors.CYAN.get(), 1, Colors.BLUE.get(),
                    TITLE_COMMAND, Colors.DEFAULT.get());

            verify(this.console).writeln(titleCommand);
        }
    }

    @Test
    void GiveCommandsListIsNotNull_WhenMenuCalled_ThenPrintTitleRead(){
        when(command.getTitle()).thenReturn(TITLE_COMMAND);
        when(command.isActive()).thenReturn(true);

        try(MockedStatic<Console> ignored = mockStatic(Console.class)) {
            when(Console.getInstance()).thenReturn(this.console);
            when(this.console.readString(any())).thenReturn("1");

            menu.execute();

            verify(this.console).readString("-> ");
        }
    }

    @Test
    void GivenALetterAsInput_WhenIsNumberValidatorCalled_ThenPrintError(){
        when(command.getTitle()).thenReturn(TITLE_COMMAND);
        when(command.isActive()).thenReturn(true);

        try(MockedStatic<Console> ignored = mockStatic(Console.class)) {
            when(Console.getInstance()).thenReturn(this.console);
            when(this.console.readString(any())).thenReturn("a", " ", "$", "1");

            menu.execute();

            verify(this.console, times(3)).writeError(ERROR_NON_NUMBER);
        }
    }

    @Test
    void GivenAOutRageNumberAsInput_WhenIsNumberValidatorCalled_ThenPrintError(){
        when(command.getTitle()).thenReturn(TITLE_COMMAND);
        when(command.isActive()).thenReturn(true);

        try(MockedStatic<Console> ignored = mockStatic(Console.class)) {
            when(Console.getInstance()).thenReturn(this.console);
            when(this.console.readString(any())).thenReturn("0", "2", "1");

            menu.execute();

            verify(this.console, times(2)).writeError(ERROR_OUT_RAGE +" 1");
        }
    }


}
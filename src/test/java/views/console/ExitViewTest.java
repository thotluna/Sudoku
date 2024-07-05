package views.console;

import controllers.ExitController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import utils.Console;

import static org.mockito.Mockito.*;
import static shared.CommandSupport.createTitleCommand;

class ExitViewTest {

    @Mock
    Console console;
    @Mock
    ExitController controller;
    ExitView view;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        view = new ExitView();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
    @Test
    void GiveEndGame_WhenExitViewCalled_ThenShowMenu(){

        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);

            when(console.readString("-> ")).thenReturn("1");

            view.interact(controller);

            verify(console).writeln(MessageRepository.getInstance()
                    .get("sudoku.start-menu"));
        }
    }

    @Test
    void GiveEndGame_WhenExitViewCalled_ThenShowCommands(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(controller.hasGame()).thenReturn(true);
            when(console.readString("-> ")).thenReturn("1");

            view.interact(controller);

            String[] titleCommands = new String[]{
                    MessageRepository.getInstance().get("sudoku.exit-menu.restart"),
                    MessageRepository.getInstance().get("sudoku.exit-menu.other-game"),
                    MessageRepository.getInstance().get("sudoku.exit-menu.exit-game"),

            };

            for (int i = 0; i < titleCommands.length; i++) {
                verify(console, times(1))
                        .writeln(createTitleCommand(titleCommands[i], i + 1));
            }
        }

    }

    @Test
    void GiveExitWithoutGame_WhenExitViewCalled_ThenShowCommands(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(console.readString("-> ")).thenReturn("1");

            view.interact(controller);

            String[] titleCommands = new String[]{
                    MessageRepository.getInstance().get("sudoku.exit-menu.other-game"),
                    MessageRepository.getInstance().get("sudoku.exit-menu.exit-game"),
            };

            for (int i = 0; i < titleCommands.length; i++) {
                verify(console, times(1))
                        .writeln(createTitleCommand(titleCommands[i], i + 1));
            }

            verify(console, times(0))
                    .writeln(createTitleCommand(MessageRepository.getInstance().get("sudoku.exit-menu.restart"), 1));
        }
    }

    @Test
    void GiveEndGame_WhenExitViewAndRestartCalled_ThenControllerRestartCalled(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(controller.hasGame()).thenReturn(true);
            when(console.readString("-> ")).thenReturn("1");

            view.interact(controller);

            verify(controller).restart();
        }
    }

    @Test
    void GiveEndGame_WhenExitViewAndOtherGameCalled_ThenControllerStartCalled(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(controller.hasGame()).thenReturn(true);
            when(console.readString("-> ")).thenReturn("2");

            view.interact(controller);

            verify(controller).start();
        }
    }

    @Test
    void GiveEndGame_WhenExitViewAndOtherGameCalled_ThenControllerExitCalled(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(controller.hasGame()).thenReturn(true);
            when(console.readString("-> ")).thenReturn("3");

            view.interact(controller);

            verify(controller).exit();
        }
    }
}
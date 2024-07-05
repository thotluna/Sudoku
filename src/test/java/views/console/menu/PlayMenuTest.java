package views.console.menu;

import controllers.GameController;
import controllers.SaveController;
import models.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import utils.Console;
import utils.menu.Menu;
import views.console.MessageRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static shared.CommandSupport.createTitleCommand;

class PlayMenuTest {

    @Mock
    Session session;
    @Mock
    SaveController saveController;

    GameController controller;
    Menu menu;
    @Mock
    Console console;

    private AutoCloseable closeable;

    private static final String MENU_TITLE = "menu title";


    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        controller = new GameController(session, saveController);
        menu = new PlayMenu(MENU_TITLE, controller);

        when(saveController.hasGame()).thenReturn(true);
        when(controller.isRedoable()).thenReturn(true);
        when(controller.isUndoable()).thenReturn(true);

        when(console.readString("-> ")).thenReturn("2");
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void GiveAGamePlay_WhenPlayMenuExecuteCalled_thenShowTitleMenu(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);

            menu.execute();

            verify(console).writeln(MENU_TITLE);
        }
    }

    @Test
    void GiveAGamePlay_WhenPlayMenuExecuteCalled_thenShowPutTitleCommand(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            menu.execute();

            verify(console).writeln(createTitleCommand(MessageRepository.getInstance().get("sudoku" +
                    ".play-menu.put"), 1));
        }
    }

    @Test
    void GiveAGamePlay_WhenPlayMenuExecuteCalled_thenShowUndoTitleCommand(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            menu.execute();

            verify(console).writeln(createTitleCommand(MessageRepository.getInstance().get("sudoku" +
                    ".play-menu.undo"), 2));
        }
    }

    @Test
    void GiveAGamePlay_WhenPlayMenuExecuteCalled_thenShowRedoTitleCommand(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            menu.execute();

            verify(console).writeln(createTitleCommand(MessageRepository.getInstance().get("sudoku" +
                    ".play-menu.redo"), 3));
        }
    }

    @Test
    void GiveAGamePlay_WhenPlayMenuExecuteCalled_thenShowSaveTitleCommand(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            menu.execute();

            verify(console).writeln(createTitleCommand(MessageRepository.getInstance().get("sudoku" +
                    ".play-menu.save"), 4));
        }
    }
}
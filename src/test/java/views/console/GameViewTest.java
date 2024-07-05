package views.console;

import controllers.GameController;
import controllers.SaveController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import shared.CommandSupport;
import utils.Console;

import static org.mockito.Mockito.*;
import static shared.BoardSupport.getBoardSet;

class GameViewTest {

    @Mock
    GameController controller;

    @Mock
    SaveController saveController;

    @Mock
    Console console;

    GameView view;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        view = new GameView();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void GiveInNewGame_WhenGameInteract_ThenShowMenuGame(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(controller.getBoard()).thenReturn(getBoardSet().initial());
            when(controller.getSaveController()).thenReturn(saveController);
            when(saveController.hasGame()).thenReturn(true);
            when(console.readString("-> ")).thenReturn("2");

            view.interact(controller);

            verify(console).writeln(MessageRepository.getInstance().get("sudoku.start-menu"));
            verify(console).writeln(
                    CommandSupport.createTitleCommand(
                            MessageRepository.getInstance().get("sudoku.play-menu.put"), 1));
            verify(console).writeln(
                    CommandSupport.createTitleCommand(
                            MessageRepository.getInstance().get("sudoku.play-menu.save"), 2));
            verify(console).writeln(
                    CommandSupport.createTitleCommand(
                            MessageRepository.getInstance().get("sudoku.exit-menu.exit-game"), 3));
        }
    }

    @Test
    void GiveInGame_WhenGameInteract_ThenShowMenuGame() {
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(controller.getBoard()).thenReturn(getBoardSet().initial());
            when(controller.isUndoable()).thenReturn(true);
            when(controller.isRedoable()).thenReturn(true);
            when(controller.getSaveController()).thenReturn(saveController);
            when(saveController.hasGame()).thenReturn(true);
            when(console.readString("-> ")).thenReturn("2");

            view.interact(controller);

            verify(console).writeln(MessageRepository.getInstance().get("sudoku.start-menu"));
            verify(console).writeln(
                    CommandSupport.createTitleCommand(
                            MessageRepository.getInstance().get("sudoku.play-menu.put"), 1));
            verify(console).writeln(
                    CommandSupport.createTitleCommand(
                            MessageRepository.getInstance().get("sudoku.play-menu.undo"), 2));
            verify(console).writeln(
                    CommandSupport.createTitleCommand(
                            MessageRepository.getInstance().get("sudoku.play-menu.redo"), 3));
            verify(console).writeln(
                    CommandSupport.createTitleCommand(
                            MessageRepository.getInstance().get("sudoku.play-menu.save"), 4));
            verify(console).writeln(
                    CommandSupport.createTitleCommand(
                            MessageRepository.getInstance().get("sudoku.exit-menu.exit-game"), 5));
        }
    }

    @Test
    void GiveInGame_WhenGameInteractAndGameNotFinish_ThenShowMenuGame(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(controller.getBoard()).thenReturn(getBoardSet().initial());
            when(controller.getSaveController()).thenReturn(saveController);
            when(saveController.hasGame()).thenReturn(true);
            when(controller.isNotGameOver()).thenReturn(true, false);

            when(console.readString("-> ")).thenReturn("2");

            view.interact(controller);

            verify(console, times(2)).writeln(MessageRepository.getInstance().get("sudoku.start-menu"));
        }

    }

}
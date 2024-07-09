package views.console;

import controllers.GameController;
import controllers.SaveController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import shared.CommandSupport;

import static org.mockito.Mockito.*;
import static shared.BoardSupport.getBoardSet;

class GameViewTest extends TestAbstract {
    @Mock
    GameController controller;
    @Mock
    SaveController saveController;
    GameView view;

    @BeforeEach
    void setUp() {
        setUpMock();
        view = new GameView();
    }

    @AfterEach
    void tearDown() throws Exception {
        afterTestMock();
    }

    @Test
    void GiveInNewGame_WhenGameInteract_ThenShowMenuGame() {

        when(controller.getBoard()).thenReturn(getBoardSet().initial());
        when(controller.getSaveController()).thenReturn(saveController);
        when(saveController.hasGame()).thenReturn(true);
        when(console.readString("-> ")).thenReturn("--", "3");

        view.interact(controller);

        String[] titles = new String[]{
                MessageRepository.getInstance().get("sudoku.play-menu.put"),
                MessageRepository.getInstance().get("sudoku.play-menu.highlight"),
                MessageRepository.getInstance().get("sudoku.play-menu.save"),
                MessageRepository.getInstance().get("sudoku.exit-menu.exit-game")
        };

        verify(console).writeln(MessageRepository.getInstance().get("sudoku.start-menu"));

        for (int i = 0; i < titles.length; i++) {
            verify(console).writeln(
                    CommandSupport.createTitleCommand(titles[i], i + 1));
        }

    }

    @Test
    void GiveInGame_WhenGameInteract_ThenShowMenuGame() {

        when(controller.getBoard()).thenReturn(getBoardSet().initial());
        when(controller.isUndoable()).thenReturn(true);
        when(controller.isRedoable()).thenReturn(true);
        when(controller.getSaveController()).thenReturn(saveController);
        when(saveController.hasGame()).thenReturn(true);
        when(console.readString("-> ")).thenReturn("--", "3");

        view.interact(controller);

        String[] titles = new String[]{
                MessageRepository.getInstance().get("sudoku.play-menu.put"),
                MessageRepository.getInstance().get("sudoku.play-menu.highlight"),
                MessageRepository.getInstance().get("sudoku.play-menu.undo"),
                MessageRepository.getInstance().get("sudoku.play-menu.redo"),
                MessageRepository.getInstance().get("sudoku.play-menu.save"),
                MessageRepository.getInstance().get("sudoku.exit-menu.exit-game")
        };

        verify(console).writeln(MessageRepository.getInstance().get("sudoku.start-menu"));

        for (int i = 0; i < titles.length; i++) {
            verify(console).writeln(
                    CommandSupport.createTitleCommand(titles[i], i + 1));
        }
    }

    @Test
    void GiveInGame_WhenGameInteractAndGameNotFinish_ThenShowMenuGame() {

        when(controller.getBoard()).thenReturn(getBoardSet().initial());
        when(controller.getSaveController()).thenReturn(saveController);
        when(saveController.hasGame()).thenReturn(true);
        when(controller.isNotGameOver()).thenReturn(true, false);

        when(console.readString("-> ")).thenReturn("--", "3", "--", "3");

        view.interact(controller);

        verify(console, times(2)).writeln(MessageRepository.getInstance().get("sudoku.start-menu"));

    }

    @Test
    void GiveInGame_WhenInputDirectData_ThenAddCell() {

        when(controller.getBoard()).thenReturn(getBoardSet().initial());
        when(controller.getSaveController()).thenReturn(saveController);
        when(saveController.hasGame()).thenReturn(true);
        when(controller.isValidCell(any())).thenReturn(true);
        when(controller.isNotGameOver()).thenReturn(true, false);

        when(console.readString("-> ")).thenReturn("A1/1", "A1:1", "--", "3");

        view.interact(controller);

        verify(controller).addCell("A1:1");
    }

    @Test
    void GiveInGame_WhenInputDirectDataHighlight_ThenAddHighlightCell() {

        when(controller.getBoard()).thenReturn(getBoardSet().initial());
        when(controller.getSaveController()).thenReturn(saveController);
        when(saveController.hasGame()).thenReturn(true);
        when(controller.isValidCell(any())).thenReturn(true);
        when(controller.isNotGameOver()).thenReturn(true, false);

        when(console.readString("-> ")).thenReturn("A1/1+", "A1+", "--", "3");

        view.interact(controller);

        verify(controller, times(1)).addCellHighlight("A1/1+");
        verify(controller, times(1)).addCellHighlight("A1+");
    }

    @Test
    void GiveInGame_WhenInputDirectDataHelp_ThenAddHelpCell() {

        when(controller.getBoard()).thenReturn(getBoardSet().initial());
        when(controller.getSaveController()).thenReturn(saveController);
        when(saveController.hasGame()).thenReturn(true);
        when(controller.isValidCell(any())).thenReturn(true);
        when(controller.isNotGameOver()).thenReturn(true, false);

        when(console.readString("-> ")).thenReturn("A1.", "--", "3");

        view.interact(controller);

        verify(controller, times(1)).helpCell("A1.");
    }

    @Test
    void GiveInGame_WhenInputDeleteData_ThenDeleteCell() {

        when(controller.getBoard()).thenReturn(getBoardSet().initial());
        when(controller.getSaveController()).thenReturn(saveController);
        when(saveController.hasGame()).thenReturn(true);
        when(controller.isValidCell(any())).thenReturn(true);
        when(controller.isNotGameOver()).thenReturn(true, false);

        when(console.readString("-> ")).thenReturn("A1-", "--", "3");

        view.interact(controller);

        verify(controller, times(1)).deleteCell("A1-");
    }

    @Test
    void GiveInGame_WhenInputFailFormat_ThenShowErrorFormat() {

        when(controller.getBoard()).thenReturn(getBoardSet().initial());
        when(controller.getSaveController()).thenReturn(saveController);
        when(saveController.hasGame()).thenReturn(true);
        when(controller.isValidCell(any())).thenReturn(true);
        when(controller.isNotGameOver()).thenReturn(true, true, true, true, true, true, false);

        when(console.readString("-> ")).thenReturn("A", "11", "a-", "a.", "a-", "a+", "--", "3");

        view.interact(controller);

        verify(console, times(6)).writeError(MessageRepository.getInstance().get("sudoku.put-view.put.error"));

        verify(controller, times(0)).addCell((String) any());
        verify(controller, times(0)).addCellHighlight(any());
        verify(controller, times(0)).helpCell(any());
    }

}
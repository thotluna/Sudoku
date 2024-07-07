package views.console;

import controllers.GameController;
import models.Board;
import models.Cell;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import utils.Console;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HighlightViewTest {

    @Mock
    Console console;

    @Mock
    GameController controller;

    HighlightView view;

    private AutoCloseable closeable;

    private static final String DATA = "A1:3";

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        view = new HighlightView(controller);

        when(controller.getBoard()).thenReturn(new Board());
        when(controller.isValidCell(any())).thenReturn(true);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void GiveStart_WhenHighlightViewCalled_thenPrintAsk(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(console.readString("-> ")).thenReturn(DATA);

            view.interact();

            verify(console).writeln(MessageRepository.getInstance().get("sudoku.highlight-view.highlight"));
        }
    }

    @Test
    void GiveShowASkPutData_WhenInputFormatIsFail_ThenPrinterBadFormat(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(console.readString("-> ")).thenReturn("5", "A54", "A5-4", "A10:1", "A1:10", "A1:A", "A1:0", DATA);


            view.interact();

            verify(console, times(7)).writeError(
                    MessageRepository.getInstance().get("sudoku.put-view.put.error"));
        }
    }

    @Test
    void GiveShowASkPutData_WhenInputFormatIsFail_ThenNotPrinterBadFormat(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(console.readString("-> ")).thenReturn("A1", "A1:1", DATA);


            view.interact();

            verify(console, times(0)).writeError(
                    MessageRepository.getInstance().get("sudoku.put-view.put.error"));
        }
    }

    @Test
    void GiveShowASkData_WhenInputValueValid_ThenNotPrinterErrorValue(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(console.readString("-> ")).thenReturn("A1", "A1h", DATA);

            view.interact();

            String error = String.format("%s %s",
                    MessageRepository.getInstance().get("sudoku.put-view.put.error"),
                    MessageRepository.getInstance().get("sudoku.put-view.put.error-value"));

            verify(console, times(0)).writeError(error);
        }
    }

    @Test
    void GiveShowASkPutData_WhenInputCoordinateISCellBusy_ThenPrinterErrorAvailableCell(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(controller.isValidCell(any())).thenReturn(false, false,true);
            when(console.readString("-> ")).thenReturn("A1:1", "a1", DATA);

            view.interact();
            String error = String.format("%s %s", MessageRepository.getInstance().get("sudoku.put-view.put.error"),
                    MessageRepository.getInstance().get("sudoku.put-view.put.error-coordinate-cell-busy"));

            verify(console, times(2)).writeError(error);
        }
    }

    @Test
    void GiveShowASkPutData_WhenInputValueCellExistInRow_ThenPrinterErrorValueRepeatRow(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(controller.isValidCell(any())).thenReturn(true);
            when(console.readString("-> ")).thenReturn("A1:1", DATA);
            Board board = new Board();
            board.addCell(Cell.newCellCandidate(0, 1, 1));
            when(controller.getBoard()).thenReturn(board);


            view.interact();


            verify(console, times(1)).writeError(
                    MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-row"));
        }
    }

    @Test
    void GiveShowASkDataCoordinate_WhenInputHighlightExistInRow_ThenNotPrinterErrorValueRepeatRow(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(controller.isValidCell(any())).thenReturn(true);
            when(console.readString("-> ")).thenReturn("A1");
            Board board = new Board();
            board.addCell(Cell.newCellCandidate(0, 1, 1));
            when(controller.getBoard()).thenReturn(board);


            view.interact();


            verify(console, times(0)).writeError(
                    MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-row"));
        }
    }

    @Test
    void GiveShowASkDataHighlight_WhenInputValueCellExistInColumn_ThenPrinterErrorValueRepeatColumn(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(controller.isValidCell(any())).thenReturn(true);
            when(console.readString("-> ")).thenReturn("A1:1", DATA);
            Board board = new Board();
            board.addCell(Cell.newCellCandidate(1, 0, 1));
            when(controller.getBoard()).thenReturn(board);


            view.interact();


            verify(console, times(1)).writeError(
                    MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-column"));
        }
    }

    @Test
    void GiveShowASkkCoordinateHighlight_WhenInputValueCellExistInColumn_ThenNotPrinterErrorValueRepeatColumn(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(controller.isValidCell(any())).thenReturn(true);
            when(console.readString("-> ")).thenReturn("A1");
            Board board = new Board();
            board.addCell(Cell.newCellCandidate(1, 0, 1));
            when(controller.getBoard()).thenReturn(board);


            view.interact();


            verify(console, times(0)).writeError(
                    MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-column"));
        }
    }

    @Test
    void GiveShowASkDataHighlight_WhenInputValueCellExistInSubgrid_ThenPrinterErrorValueRepeatSubgrid(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(controller.isValidCell(any())).thenReturn(true);
            when(console.readString("-> ")).thenReturn("A1:1", DATA);
            Board board = new Board();
            board.addCell(Cell.newCellCandidate(2, 2, 1));
            when(controller.getBoard()).thenReturn(board);


            view.interact();


            verify(console, times(1)).writeError(
                    MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-subgrid"));
        }
    }

    @Test
    void GiveShowASkCoordinateHighlight_WhenInputValueCellExistInSubgrid_ThenNotPrinterErrorValueRepeatSubgrid(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(controller.isValidCell(any())).thenReturn(true);
            when(console.readString("-> ")).thenReturn("A1");
            Board board = new Board();
            board.addCell(Cell.newCellCandidate(2, 2, 1));
            when(controller.getBoard()).thenReturn(board);


            view.interact();


            verify(console, times(0)).writeError(
                    MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-subgrid"));
        }
    }


}
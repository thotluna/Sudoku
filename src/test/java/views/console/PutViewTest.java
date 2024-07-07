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

import static org.mockito.Mockito.*;

class PutViewTest {

    @Mock
    Console console;

    @Mock
    GameController controller;

    PutView view;

    private AutoCloseable closeable;

    private static final String DATA = "A1:3";

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        view = new PutView(controller);

        when(controller.getBoard()).thenReturn(new Board());
        when(controller.isValidCell(any())).thenReturn(true);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void GiveStart_WhenPutViewCalled_thenPrintAsk(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(console.readString("-> ")).thenReturn(DATA);

            view.interact();

            verify(console).writeln(MessageRepository.getInstance().get("sudoku.put-view.put"));
        }
    }

    @Test
    void GiveShowASkPutData_WhenInputFormatIsFail_ThenPrinterBadFormat(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(console.readString("-> ")).thenReturn("5", "A54", "A5-4", "A10:1", "A1:10", DATA);


            view.interact();

            verify(console, times(5)).writeError(
                    MessageRepository.getInstance().get("sudoku.put-view.put.error"));
        }
    }

    @Test
    void GiveShowASkPutData_WhenInputCoordinateISCellBusy_ThenPrinterErrorAvailableCell(){
        try (MockedStatic<Console> utilities = Mockito.mockStatic(Console.class)) {
            utilities.when(Console::getInstance).thenReturn(console);
            when(controller.isValidCell(any())).thenReturn(false, true);
            when(console.readString("-> ")).thenReturn("A1:1", DATA);

            view.interact();
            String error = String.format("%s %s", MessageRepository.getInstance().get("sudoku.put-view.put.error"),
                    MessageRepository.getInstance().get("sudoku.put-view.put.error-coordinate-cell-busy"));

            verify(console, times(1)).writeError(error);
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
    void GiveShowASkPutData_WhenInputValueCellExistInColumn_ThenPrinterErrorValueRepeatColumn(){
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
    void GiveShowASkPutData_WhenInputValueCellExistInSubgrid_ThenPrinterErrorValueRepeatSubgrid(){
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




}
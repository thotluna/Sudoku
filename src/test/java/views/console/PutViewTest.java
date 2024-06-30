package views.console;

import controllers.GameController;
import shared.ResolverSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.Console;

import static org.mockito.Mockito.*;

class PutViewTest {

    PutView view;

    @Mock
    Console console;

    @Mock
    GameController controller;

    private AutoCloseable closeable;

    private static final String DATA = "A1:3";

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        view = new PutView(controller);
        view.setConsole(console);
        resolveBoard(0, false);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void GiveStart_WhenPutViewCalled_thenPrintAsk(){
        when(console.readString("-> ")).thenReturn(DATA);

        view.interact();

        verify(console).writeln(MessageRepository.getInstance().get("sudoku.put-view.put"));
    }


    @Test
    void GiveDataFailInput_WhenPutViewInteractCalled_ThenPrinterError(){
        resolveBoard(0, false);
        when(console.readString("-> ")).thenReturn("a", "a:a", DATA);

        view.interact();

        verify(console, times(3)).writeln(MessageRepository.getInstance().get("sudoku.put-view.put"));
        verify(console, times(2)).writeError(MessageRepository.getInstance().get("sudoku.put-view.put.error"));
    }

    @Test
    void GiveCoordinateRowFailInput_WhenPutViewInteractCalled_ThenPrinterErrorCoordinate(){
        when(console.readString("-> ")).thenReturn("55:5", "J1:5", DATA);

        view.interact();

        verify(console, times(2)).writeError(
                String.format("%s %s",
                        MessageRepository.getInstance().get("sudoku.put-view.put.error"),
                        MessageRepository.getInstance().get("sudoku.put-view.put.error-coordinate")));
    }

    @Test
    void GiveCoordinateColumnFailInput_WhenPutViewInteractCalled_ThenPrinterErrorCoordinate(){
        when(console.readString("-> ")).thenReturn("AA:5", "A0:1", DATA);

        view.interact();

        verify(console, times(2)).writeError(
                MessageRepository.getInstance().get("sudoku.put-view.put.error") +
                        " " +
                        MessageRepository.getInstance().get("sudoku.put-view.put.error-coordinate"));
    }

    @Test
    void GiveCoordinateBusyInput_WhenPutViewInteractCalled_ThenPrinterErrorCoordinateBusy(){
        when(controller.isValidCell(any())).thenReturn(false, true);
        when(console.readString("-> ")).thenReturn("A1:1", DATA);

        view.interact();

        verify(console, times(1)).writeError(
                String.format("%s %s", MessageRepository.getInstance().get("sudoku.put-view.put.error"),
                        MessageRepository.getInstance().get("sudoku.put-view.put.error-coordinate-cell-busy")));

    }


    @Test
    void GiveValueFailInput_WhenPutViewInteractCalled_ThenPrinterErrorValue(){
        when(console.readString("-> ")).thenReturn("A1:A", "A1:0", DATA);

        view.interact();

        verify(console, times(2)).writeError(
                MessageRepository.getInstance().get("sudoku.put-view.put.error") +
                        " " +
                        MessageRepository.getInstance().get("sudoku.put-view.put.error-value"));
    }

    @Test
    void GiveValueRepeatToRowInput_WhenPutViewInteractCalled_ThenPrinterErrorRow(){
        when(console.readString("-> ")).thenReturn("A1:1", DATA);

        view.interact();

        verify(console, times(1)).writeError(
                MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-row"));
    }

    @Test
    void GiveValueRepeatToColumnInput_WhenPutViewInteractCalled_ThenPrinterErrorColumn(){
        resolveBoard(1, false);
        when(console.readString("-> ")).thenReturn("A1:1", DATA);

        view.interact();

        verify(console, times(1)).writeError(
                MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-column"));
    }

    @Test
    void GiveValueRepeatToSubgridInput_WhenPutViewInteractCalled_ThenPrinterErrorSubGrid(){
        resolveBoard(1, true);
        when(console.readString("-> ")).thenReturn("A1:1", DATA);

        view.interact();

        verify(console, times(1)).writeError(
                MessageRepository.getInstance().get("sudoku.validator.non-repeated-value-subgrid"));
    }

    private void resolveBoard(int otherValueInRowDelete, boolean subgridTest) {
        ResolverSupport resolverSupport = new ResolverSupport();
        when(controller.getBoard()).thenReturn(resolverSupport.getIncompleteArrayBoardForCell(DATA, null, otherValueInRowDelete, subgridTest));
        when(controller.isValidCell(any())).thenReturn(true);
    }


}
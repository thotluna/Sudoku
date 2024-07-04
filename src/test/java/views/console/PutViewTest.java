package views.console;

import controllers.GameController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.Console;

import static org.mockito.Mockito.*;

@Disabled
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




    @Disabled
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




}
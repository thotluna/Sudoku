package views.console;

import controllers.GameController;
import models.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

class PutViewTest extends TestAbstract {
    @Mock
    GameController controller;
    PutView view;
    private static final String DATA = "A1:3";

    @BeforeEach
    void setUp() {
        this.setUpMock();
        view = new PutView(controller);
        when(controller.getBoard()).thenReturn(new Board());
        when(controller.isValidCell(any())).thenReturn(true);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.afterTestMock();
    }

    @Test
    void GiveStart_WhenPutViewCalled_thenPrintAsk() {
        when(console.readString("-> ")).thenReturn(DATA);
        view.interact();
        verify(console).writeln(MessageRepository.getInstance().get("sudoku.put-view.put"));
    }

    @Test
    void GiveInputIncorrect_WhenPutInputIsValidate_ThenPrintError() {
        when(console.readString("-> ")).thenReturn("5", "A54", "A5-4", "A10:1", "A1:10", "A5/5+", "G8+", "N5:5", DATA);
        view.interact();
        verify(console, times(8)).writeError(any());
    }

    @Test
    void GiveInputCorrect_WhenPutInputIsValidate_ThenNotPrintError() {
        String[] inputs = new String[]{"A1:1", "i9/9"};
        for (String input :  inputs) {
            when(console.readString("-> ")).thenReturn(input);
            view.interact();
            verify(console, times(0)).writeError(any());
        }
    }
}
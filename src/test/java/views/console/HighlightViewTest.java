package views.console;

import controllers.GameController;
import models.Board;
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

    private MockedStatic<Console> consoleStatic;

    private static final String DATA = "A1:3+";

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        consoleStatic = Mockito.mockStatic(Console.class);
        consoleStatic.when(Console::getInstance).thenReturn(console);

        view = new HighlightView(controller);

        when(controller.getBoard()).thenReturn(new Board());
        when(controller.isValidCell(any())).thenReturn(true);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
        consoleStatic.close();
    }

    @Test
    void GiveHighlightOptionSelected_WhenHighlightViewCalled_ThenPrintQuestion(){
        when(console.readString("-> ")).thenReturn(DATA);
        view.interact();
        verify(console).writeln(MessageRepository.getInstance().get("sudoku.highlight-view.highlight"));
    }

    @Test
    void GiveInputIncorrect_WhenValidate_ThenPrintError() {
        when(console.readString("-> ")).thenReturn("5", "a", "A54", "A5-4", "A10:1", "A1:10", "A1:A", "A1:0", DATA);
        view.interact();
        verify(console, times(8)).writeError(any());
    }

    @Test
    void GiveInputCorrect_WhenValidate_ThenNotPrintError() {
        when(console.readString("-> ")).thenReturn("i9+","A1:1+");
        view.interact();
        verify(console, times(0)).writeError(any());
    }
}
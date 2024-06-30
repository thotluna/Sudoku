package views.console;

import controllers.ExitController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.Console;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ExitViewTest {

    ExitView view;
    @Mock
    Console console;
    @Mock
    ExitController controller;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        view = new ExitView();
        view.setConsole(console);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Disabled
    @Test
    void GiveInteract_WhenExitView_ThenMenuPrint(){
        when(console.readString("-> ")).thenReturn("1");

        view.interact(controller);

        verify(console).writeln(MessageRepository.getInstance()
                .get("sudoku.start-menu"));

    }
}
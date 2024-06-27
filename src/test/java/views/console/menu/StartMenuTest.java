package views.console.menu;

import controllers.StartController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import utils.Colors;
import utils.Console;
import views.console.MessageRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StartMenuTest {

    StartMenu menu;

    String title;

    @Mock
    StartController controller;

    @Mock
    Console console;

    private AutoCloseable closeable;


    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        title = MessageRepository.getInstance().get("sudoku.start-menu");

        menu = new StartMenu(title, controller);

    }

    @AfterEach
    void afterTest() throws Exception {
        closeable.close();
    }

    @Test
    void GiveStart_WhenStarMenuCalled_ThenTitleIsPrint(){
        try(MockedStatic<Console> ignored = mockStatic(Console.class)) {
            when(Console.getInstance()).thenReturn(this.console);
            when(this.console.readString(any())).thenReturn("1");

            menu.execute();

            verify(this.console).writeln(title);
        }
    }

    @Test
    void GiveStart_WhenStarMenuCalled_ThenTitleCommandsPrint(){
        try(MockedStatic<Console> ignored = mockStatic(Console.class)) {
            when(Console.getInstance()).thenReturn(this.console);
            when(this.console.readString(any())).thenReturn("1");

            menu.execute();

            String newGameCommandTitle = String.format("%s%d.- %s%S.%s", Colors.CYAN.get(), 1, Colors.BLUE.get(),
                    MessageRepository.getInstance().get("sudoku.start-menu.new"), Colors.DEFAULT.get());
            String loadGameCommandTitle = String.format("%s%d.- %s%S.%s", Colors.CYAN.get(), 2, Colors.BLUE.get(),
                    MessageRepository.getInstance().get("sudoku.start-menu.load"), Colors.DEFAULT.get());

            verify(this.console).writeln(newGameCommandTitle);
            verify(this.console).writeln(loadGameCommandTitle);
        }
    }

}
package views.console;

import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import utils.Console;

public class TestAbstract {

    @Mock
    protected Console console;
    protected AutoCloseable closeable;
    protected MockedStatic<Console> consoleStatic;

    protected void setUpMock() {
        closeable = MockitoAnnotations.openMocks(this);
        consoleStatic = Mockito.mockStatic(Console.class);
        consoleStatic.when(Console::getInstance).thenReturn(console);

    }

    protected void afterTestMock() throws Exception {
        closeable.close();
        consoleStatic.close();
    }
}

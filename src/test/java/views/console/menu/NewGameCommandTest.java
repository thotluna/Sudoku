package views.console.menu;

import controllers.GenerateNewGame;
import controllers.StartController;
import models.Cell;
import models.Coordinate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import types.TypeCell;
import utils.Console;
import views.console.MessageRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NewGameCommandTest {

    NewGameCommand command;

    @Mock
    StartController controller;

    @Mock
    Console console;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        command = new NewGameCommand(MessageRepository.getInstance().get("sudoku.start-menu.new"), controller );
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void GiveNewCommand_WhenExecuteCalled_thenCreateNewGameCalled(){
        try(MockedStatic<Console> ignored = mockStatic(Console.class)) {
            when(Console.getInstance()).thenReturn(this.console);
            when(controller.createNewGame()).thenReturn(false).thenReturn(true);

            command.execute();

            verify(controller, times(2)).createNewGame();
            verify(console, times(1)).writeln(MessageRepository.getInstance().get("sudoku.start-menu.new.generate"));
        }

    }

    @Test
    void GiveNewCommand_WhenIsActiveCalled_ThenReturnTrue(){
       assertTrue(command.isActive());
    }
}
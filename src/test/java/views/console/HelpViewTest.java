package views.console;

import controllers.GameController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HelpViewTest extends TestAbstract{
    @Mock
    GameController controller;
    HelpView view;

    private final String[] inputsValid = new String[]{"A1.", "i9."};
    private final String[] inputsNotValid = new String[]{"A1:1", "a1:1."};
    private final String helpACandidateQuestion = MessageRepository.getInstance().
            get("sudoku.help-view.question");
    private final String errorInFormatAllowedToHelp = MessageRepository.getInstance()
            .get("sudoku.input-game.error-format");

    @BeforeEach
    void setUp() {
        setUpMock();
        when(controller.isValidCell(any())).thenReturn(true);
        view = new HelpView(controller);
    }

    @AfterEach
    void tearDown() throws Exception {
        afterTestMock();
    }

    @Test
    void GiveAGame_WhenPutViewInteract_ThenShowAQuestion() {
        when(console.readString("-> ")).thenReturn(inputsValid[0]);
        view.interact();
        verify(console).writeln(helpACandidateQuestion);
    }

    @Test
    void GiveInputNotValid_WhenHelpViewInteract_ThenShowError() {
            when(console.readString("-> ")).thenReturn(inputsNotValid[0], inputsNotValid[1], inputsValid[0]);
            view.interact();
            verify(console, times(2)).writeError(errorInFormatAllowedToHelp);
    }

    @Test
    void GiveInputValid_WhenHelpViewInteract_ThenNotShowError() {
        for (String input : inputsValid) {
            when(console.readString("-> ")).thenReturn(input);
            view.interact();
            verify(console, times(0))
                    .writeError(errorInFormatAllowedToHelp);
        }
    }
}
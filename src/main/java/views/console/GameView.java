package views.console;

import controllers.GameController;
import controllers.Interpreter;
import controllers.validators.DataInputValidator;
import controllers.validators.DataInputValidatorFactory;
import utils.Console;
import utils.models.Result;
import views.console.menu.PlayMenu;

public class GameView {

    private final BoardView boardView;

    public GameView() {
        boardView = new BoardView();
    }

    public void interact(GameController controller) {
        Interpreter interpreter = new Interpreter(controller);
        DataInputValidator validator = new DataInputValidatorFactory(controller).getValidator();
        do{
            boardView.interact(controller.getBoard(), controller.getHelpAvailable());
            Console.getInstance().writeln("Introduce your Candidate or -- for menu:");
            String selected = Console.getInstance().readString("-> ");

            if(selected.equals("--")){
                PlayMenu menu = new PlayMenu(MessageRepository.getInstance().get("sudoku.start-menu"), controller);
                menu.execute();
            }else{
                Result<String, String> validate = validator.validate(selected);
                if(validate.hasError()){
                    Console.getInstance().writeError(validate.error());
                }else{
                    interpreter.interprets(selected);
                }

            }


        }while (controller.isNotGameOver());

        controller.resetIsComplete();

        if(controller.isComplete()){
            new GameOverView().interact();
        }

        controller.nextState();

    }

}

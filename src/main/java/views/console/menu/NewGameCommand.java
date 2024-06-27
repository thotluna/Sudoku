package views.console.menu;

import controllers.StartController;
import utils.Console;
import views.console.MessageRepository;

public class NewGameCommand extends StartCommandBase {

    protected NewGameCommand(String title, StartController controller) {
        super(title, controller);
    }

    @Override
    public void execute() {
        boolean success;
        do{
            success = controller.createNewGame();
            if(!success){
                Console.getInstance().writeln(MessageRepository.getInstance().get("sudoku.start-menu.new.generate"));
            }
        }while (!success);
        controller.inGameState();
    }

    @Override
    public boolean isActive() {
        return true;
    }
}

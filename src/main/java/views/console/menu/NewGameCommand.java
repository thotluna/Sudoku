package views.console.menu;

import controllers.StartController;
import utils.Console;
import views.console.MessageRepository;

public class NewGameCommand extends StartCommandBase {

    private Console console;

    protected NewGameCommand(String title, StartController controller) {
        super(title, controller);
        console = new Console();
    }

    @Override
    public void execute() {
        boolean success;
        do{
            success = controller.createNewGame();
            if(!success){
                console.writeln(MessageRepository.getInstance().get("sudoku.start-menu.new.generate"));
            }
        }while (!success);
        controller.inGameState();
    }

    @Override
    public boolean isActive() {
        return true;
    }

    void setConsole(Console console){
        this.console = console;
    }
}

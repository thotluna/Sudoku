package views.console.menu;

import controllers.StartController;

public class NewGameCommand extends StartCommandBase {


    protected NewGameCommand(String title, StartController controller) {
        super(title, controller);
    }

    @Override
    public void execute() {
        controller.createNewGame();
        controller.inGameState();
    }

    @Override
    public boolean isActive() {
        return true;
    }

}

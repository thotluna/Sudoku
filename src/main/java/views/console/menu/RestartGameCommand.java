package views.console.menu;

import controllers.ExitController;

public class RestartGameCommand extends ExitCommandBase{

    protected RestartGameCommand(String title, ExitController controller) {
        super(title, controller);
    }

    @Override
    public void execute() {
        controller.restart();
    }

    @Override
    public boolean isActive() {
        return controller.hasGame();
    }
}

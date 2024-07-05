package views.console.menu;

import controllers.ExitController;

public class RestartGameCommand extends ExitCommandBase{
    private final ExitController controller;

    public RestartGameCommand(String title, ExitController controller) {
        super(title);
        this.controller = controller;
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

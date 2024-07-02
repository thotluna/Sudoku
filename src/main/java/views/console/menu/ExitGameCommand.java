package views.console.menu;

import controllers.ExitController;

public class ExitGameCommand extends ExitCommandBase {
    protected ExitGameCommand(String title, ExitController controller) {
        super(title, controller);
    }

    @Override
    public void execute() {
        controller.exit();
    }

    @Override
    public boolean isActive() {
        return true;
    }

}

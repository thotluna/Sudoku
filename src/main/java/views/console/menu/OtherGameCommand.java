package views.console.menu;

import controllers.ExitController;

public class OtherGameCommand extends ExitCommandBase {

    protected OtherGameCommand(String title, ExitController controller) {
        super(title, controller);
    }

    @Override
    public void execute() {
        controller.start();
    }

    @Override
    public boolean isActive() {
        return true;
    }
}

package views.console.menu;

import controllers.ExitController;

public class OtherGameCommand extends ExitCommandBase {

    public final ExitController controller;

    protected OtherGameCommand(String title, ExitController controller) {
        super(title);
        this.controller = controller;
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

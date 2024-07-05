package views.console.menu;

import controllers.Ejectable;

public class ExitGameCommand extends ExitCommandBase {

    public final Ejectable controller;
    protected ExitGameCommand(String title, Ejectable controller) {
        super(title);
        this.controller = controller;
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

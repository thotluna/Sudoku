package views.console.menu;

import controllers.LoadController;
import utils.menu.CommandBase;

public class LoadCommand extends CommandBase {
    private final LoadController controller;
    public LoadCommand(String title, LoadController controller) {
        super(title);
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.load(title);
        controller.nextState();
    }

    @Override
    public boolean isActive() {
        return controller.hasFiles();
    }

    @Override
    public boolean isSecret() {
        return false;
    }
}

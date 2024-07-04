package views.console.menu;

import controllers.StartController;
import utils.menu.CommandBase;

public abstract class StartCommandBase extends CommandBase {

    protected final StartController controller;

    protected StartCommandBase(String title, StartController controller) {
        super(title);
        this.controller = controller;
    }

    @Override
    public boolean isSecret() {
        return false;
    }
}

package views.console.menu;

import controllers.ExitController;
import utils.menu.CommandBase;

public abstract class ExitCommandBase extends CommandBase {

    protected ExitController controller;

    protected ExitCommandBase(String title, ExitController controller) {
        super(title);
        this.controller = controller;
    }

}

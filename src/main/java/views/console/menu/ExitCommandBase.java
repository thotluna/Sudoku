package views.console.menu;

import utils.menu.CommandBase;

public abstract class ExitCommandBase extends CommandBase {

    protected ExitCommandBase(String title) {
        super(title);
    }

    @Override
    public boolean isSecret() {
        return false;
    }

}

package views.console.menu;

import controllers.StartController;

public class LoadGameCommand extends StartCommandBase{
    protected LoadGameCommand(String title, StartController controller) {
        super(title, controller);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isActive() {
        return true;
    }
}

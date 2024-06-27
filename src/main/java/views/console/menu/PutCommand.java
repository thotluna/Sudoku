package views.console.menu;

import controllers.GameController;

public class PutCommand extends PlayCommandBase{

    protected PutCommand(String title, GameController controller) {
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

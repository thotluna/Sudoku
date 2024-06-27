package views.console.menu;

import controllers.StartController;

public class CancelStartGameCommand extends StartCommandBase {

    protected CancelStartGameCommand(String title, StartController controller) {
        super(title, controller);
    }

    @Override
    public void execute() {
        controller.endState();
    }

    @Override
    public boolean isActive() {
        return true;
    }
}

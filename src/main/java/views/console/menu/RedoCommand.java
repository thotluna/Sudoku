package views.console.menu;

import controllers.GameController;

public class RedoCommand extends PlayCommandBase{
    protected RedoCommand(String title, GameController controller) {
        super(title, controller);
    }

    @Override
    public void execute() {
        controller.redo();
    }

    @Override
    public boolean isActive() {
        return controller.isRedoable();
    }
}

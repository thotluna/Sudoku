package views.console.menu;

import controllers.GameController;

public class UndoCommand extends PlayCommandBase{

    protected UndoCommand(String title, GameController controller) {
        super(title, controller);
    }

    @Override
    public void execute() {
        controller.undo();
    }

    @Override
    public boolean isActive() {
        return controller.isUndoable();
    }
}

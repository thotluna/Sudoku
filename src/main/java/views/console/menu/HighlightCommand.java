package views.console.menu;

import controllers.GameController;
import views.console.HighlightView;

public class HighlightCommand extends PlayCommandBase{

    protected HighlightCommand(String title, GameController controller) {
        super(title, controller);
    }

    @Override
    public void execute() {
        new HighlightView(controller).interact();
    }

    @Override
    public boolean isActive() {
        return true;
    }
}

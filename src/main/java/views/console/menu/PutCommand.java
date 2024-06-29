package views.console.menu;

import controllers.GameController;
import views.console.PutView;

public class PutCommand extends PlayCommandBase{

    protected PutCommand(String title, GameController controller) {
        super(title, controller);
    }

    @Override
    public void execute() {
        new PutView(controller).interact();
    }

    @Override
    public boolean isActive() {
        return true;
    }
}

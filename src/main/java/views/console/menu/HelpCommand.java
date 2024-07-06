package views.console.menu;

import controllers.GameController;
import views.console.HelpView;

public class HelpCommand extends PlayCommandBase{

    protected HelpCommand(String title, GameController controller) {
        super(title, controller);
    }

    @Override
    public void execute() {
        new HelpView(controller).interact();
    }

    @Override
    public boolean isActive() {
        return controller.hasHelp();
    }
}

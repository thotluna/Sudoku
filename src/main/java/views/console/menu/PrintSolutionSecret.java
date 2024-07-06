package views.console.menu;

import controllers.GameController;
import views.console.BoardView;

public class PrintSolutionSecret extends PlayCommandBase {

    protected PrintSolutionSecret(String title, GameController controller) {
        super(title, controller);
    }

    @Override
    public void execute() {
        new BoardView().interact(controller.getSolution(), controller.getHelpAvailable());
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean isSecret() {
        return true;
    }
}

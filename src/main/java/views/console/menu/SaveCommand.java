package views.console.menu;

import controllers.SaveController;
import utils.menu.CommandBase;

public class SaveCommand extends CommandBase {
    private final SaveController controller;

    public SaveCommand(String title, SaveController controller) {
        super(title);
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.saveGame();
    }

    @Override
    public boolean isActive() {
        return controller.hasGame();
    }

    @Override
    public boolean isSecret() {
        return false;
    }
}

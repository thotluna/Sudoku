package views.console.menu;

import controllers.GameController;
import utils.menu.CommandBase;

public abstract class PlayCommandBase extends CommandBase {
    GameController controller;

    protected PlayCommandBase(String title, GameController controller) {
        super(title);
        this.controller = controller;
    }

}

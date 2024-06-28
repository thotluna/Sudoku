package views.console.menu;

import controllers.ExitController;
import utils.menu.Menu;
import views.console.MessageRepository;

public class ExitMenu extends Menu {

    public ExitMenu(String title, ExitController controller) {
        super(title);

        this.addCommand(new RestartGameCommand(MessageRepository.getInstance().get("sudoku.exit-menu.restart"),
                controller));
        this.addCommand(new OtherGameCommand(MessageRepository.getInstance().get("sudoku.exit-menu.other-game"),
                controller));
        this.addCommand(new ExitGameCommand(MessageRepository.getInstance().get("sudoku.exit-menu.exit-game"),
                controller));
    }
}

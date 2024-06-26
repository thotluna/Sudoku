package views.console.menu;

import controllers.StartController;
import utils.menu.Menu;
import views.console.MessageRepository;

public class StartMenu extends Menu {

    public StartMenu(String title, StartController controller) {
        super(title);
        assert controller != null;

        addCommand(new NewGameCommand(MessageRepository.getInstance().get("sudoku.start-menu.new"), controller));
        addCommand(new LoadGameCommand(MessageRepository.getInstance().get("sudoku.start-menu.load"), controller));
    }
}

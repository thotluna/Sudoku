package views.console.menu;

import controllers.GameController;
import utils.menu.Menu;
import views.console.MessageRepository;

public class PlayMenu extends Menu {



    public PlayMenu(String title, GameController controller) {
        super(title);

        this.addCommand(new PutCommand(MessageRepository.getInstance().get("sudoku.play-menu.put"), controller ));
        this.addCommand(new PrintSolutionSecret("", controller));
    }
}

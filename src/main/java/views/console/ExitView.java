package views.console;

import controllers.ExitController;
import utils.WithConsole;
import utils.menu.Menu;
import views.console.menu.ExitMenu;

public class ExitView extends WithConsole {
    public void interact(ExitController controller) {
        Menu menu = new ExitMenu(MessageRepository.getInstance().get("sudoku.start-menu"), controller);
        menu.setConsole(this.console);
        menu.execute();
    }

}

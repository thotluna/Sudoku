package views.console;

import controllers.ExitController;
import utils.menu.Menu;
import views.console.menu.ExitMenu;

public class ExitView {
    public void interact(ExitController controller) {
        Menu menu = new ExitMenu(MessageRepository.getInstance().get("sudoku.start-menu"), controller);
        menu.execute();
    }

}

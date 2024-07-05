package views.console;

import controllers.LoadController;
import utils.menu.Menu;
import views.console.menu.LoadMenu;


public class LoadView {
    public void interact(LoadController controller) {
        Menu menu = new LoadMenu(MessageRepository.getInstance().get("sudoku.start-menu"), controller);
        menu.execute();

    }
}

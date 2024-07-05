package views.console;

import controllers.StartController;
import utils.WithConsole;
import utils.menu.Menu;
import views.console.menu.StartMenu;

public class StartView extends WithConsole {

    private final WelcomeView welcomeView;

    public StartView() {
        this.welcomeView = new WelcomeView();
    }

    public void interact(StartController controller) {
        this.welcomeView.interact();
        Menu menu = new StartMenu(MessageRepository.getInstance().get("sudoku.start-menu"), controller);
        menu.setConsole(this.console);
        menu.execute();
    }



}

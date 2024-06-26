package views.console;

import controllers.ExitController;
import controllers.GameController;
import controllers.LoadController;
import controllers.StartController;
import views.PrintScreen;

public class ConsoleView implements PrintScreen {

    private final StartView startView;
    private final LoadView loadView;
    private final GameView gameView;
    private final ExitView exitView;

    public ConsoleView() {
        this.startView = new StartView();
        this.loadView = new LoadView();
        this.gameView = new GameView();
        this.exitView = new ExitView();
    }

    @Override
    public void visit(StartController controller) {
        startView.interact(controller);
    }

    @Override
    public void visit(LoadController controller) {
        loadView.interact(controller);
    }

    @Override
    public void visit(GameController controller) {
        this.gameView.interact(controller);
    }

    @Override
    public void visit(ExitController controller) {
        this.exitView.interact(controller);
    }
}

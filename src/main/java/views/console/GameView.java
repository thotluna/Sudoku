package views.console;

import controllers.GameController;
import models.Board;
import utils.Console;
import views.console.menu.PlayMenu;

public class GameView {

    private final BoardView boardView;

    Console console;

    public GameView() {
        boardView = new BoardView();
    }

    public void interact(GameController controller) {
        do{
            PlayMenu menu = new PlayMenu(MessageRepository.getInstance().get("sudoku.start-menu"), controller);
            if(console != null){
                menu.setConsole(console);
                boardView.setConsole(console);
            }
            Board board = controller.getBoard();
            boardView.interact(board);

            menu.execute();
        }while (controller.isNotGameOver());

        controller.resetIsComplete();

        if(controller.isComplete()){
            new GameOverView().interact();
        }

        controller.nextState();

    }

    void setConsole(Console console) {
        this.console = console;
    }
}

package views.console;

import controllers.GameController;
import models.Cell;
import views.console.menu.PlayMenu;

public class GameView {

    private final BoardView boardView;

    public GameView() {
        boardView = new BoardView();
    }

    public void interact(GameController controller) {
        PlayMenu menu = new PlayMenu(MessageRepository.getInstance().get("sudoku.start-menu"), controller);
        do{
            Cell[][] board = controller.getBoard();
            boardView.interact(board);

            menu.execute();
        }while (controller.isFinish());

        controller.nextState();

    }
}

package views.console;

import controllers.GameController;
import models.Board;
import views.console.menu.PlayMenu;

public class GameView {

    private final BoardView boardView;

    public GameView() {
        boardView = new BoardView();
    }

    public void interact(GameController controller) {
        do{
            PlayMenu menu = new PlayMenu(MessageRepository.getInstance().get("sudoku.start-menu"), controller);
            Board board = controller.getBoard();
            boardView.interact(board);

            menu.execute();
        }while (controller.isNotGameOver());

        new GameOverView().interact();

        controller.nextState();

    }
}

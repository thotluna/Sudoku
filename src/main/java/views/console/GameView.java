package views.console;

import controllers.GameController;
import views.console.menu.PlayMenu;

public class GameView {

    private final BoardView boardView;

    public GameView() {
        boardView = new BoardView();
    }

    public void interact(GameController controller) {
        do{
            PlayMenu menu = new PlayMenu(MessageRepository.getInstance().get("sudoku.start-menu"), controller);
            boardView.interact(controller.getBoard(), controller.getHelpAvailable());

            menu.execute();
        }while (controller.isNotGameOver());

        controller.resetIsComplete();

        if(controller.isComplete()){
            new GameOverView().interact();
        }

        controller.nextState();

    }

}

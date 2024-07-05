package views.console.menu;

import controllers.GameController;
import utils.menu.Menu;
import views.console.MessageRepository;

public class PlayMenu extends Menu {



    public PlayMenu(String title, GameController controller) {
        super(title);

        this.addCommand(
                new PutCommand(
                        MessageRepository.getInstance().get("sudoku.play-menu.put")
                        , controller ));

        this.addCommand(
                new UndoCommand(
                        MessageRepository.getInstance().get("sudoku.play-menu.undo")
                        , controller));

        this.addCommand(
                new RedoCommand(
                        MessageRepository.getInstance().get("sudoku.play-menu.redo"),
                        controller));

        this.addCommand(
                new SaveCommand(MessageRepository.getInstance().get("sudoku.play-menu.save"),
                        controller.getSaveController()));
        this.addCommand(
                new ExitGameCommand(MessageRepository.getInstance().get("sudoku.exit-menu.exit-game"),
                        controller));

        this.addCommand(new PrintSolutionSecret("", controller));

    }
}

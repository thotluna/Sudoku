package views.console;

import controllers.GameController;
import controllers.validators.DataInputValidator;
import controllers.validators.DataInputValidatorFactory;
import utils.Console;
import utils.models.Result;

public class HighlightView {

    private final GameController controller;
    private final DataInputValidator validator;

    public HighlightView(GameController controller) {
        this.controller = controller;
        validator = new DataInputValidatorFactory(controller).getValidator();
    }


    public void interact() {
        boolean error;
        String data;
        do{
            error = false;
            Console.getInstance().writeln(MessageRepository.getInstance().get("sudoku.highlight-view.highlight"));
            data = Console.getInstance().readString("-> ");

            Result<String, String> validate = validator.validate(data);

            if(validate.hasError()){
                error = true;
                Console.getInstance().writeError(validate.error());
            }

        }while (error);

        controller.addCellHighlight(data);
        controller.register();


    }
}

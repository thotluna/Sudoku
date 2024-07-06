package views.console;

import controllers.GameController;
import controllers.validators.CoordinateInputValidatorFactory;
import controllers.validators.DataInputValidator;
import utils.Console;
import utils.models.Result;

public class HelpView {
    private final GameController controller;
    private final DataInputValidator validator;

    public HelpView(GameController controller) {
        this.controller = controller;
        validator = new CoordinateInputValidatorFactory(controller).getValidator();
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

        controller.helpCell(data);
        controller.register();

    }
}

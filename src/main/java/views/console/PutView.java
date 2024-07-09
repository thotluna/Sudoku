package views.console;

import controllers.GameController;
import controllers.validators.DataInputValidatorFactory;
import controllers.validators.DataInputValidator;
import controllers.validators.PutInputValidator;
import utils.Console;
import utils.models.Result;


public class PutView {

    private final GameController controller;

    private final DataInputValidator validator;

    public PutView(GameController controller) {
        this.controller = controller;
        validator = new PutInputValidator(controller, new DataInputValidatorFactory(controller).getValidator());
    }

    public void interact(){
        boolean error;
        String data;
        do{
            error = false;
            Console.getInstance().writeln(MessageRepository.getInstance().get("sudoku.put-view.put"));
            data = Console.getInstance().readString("-> ");

            Result<String, String> validate = validator.validate(data);

            if(validate.hasError()){
                error = true;
                Console.getInstance().writeError(validate.error());
            }

        }while (error);

        controller.addCell(data);
        controller.register();

    }
}

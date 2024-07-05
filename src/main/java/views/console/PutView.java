package views.console;

import controllers.GameController;
import controllers.validators.PutValidatorFactory;
import controllers.validators.PutInputValidator;
import utils.WithConsole;
import utils.models.Result;


public class PutView extends WithConsole {

    private final GameController controller;

    private final PutInputValidator validator;

    public PutView(GameController controller) {
        this.controller = controller;
        validator = new PutValidatorFactory(controller).getValidator();
    }

    public void interact(){
        boolean error;
        String data;
        do{
            error = false;
            console.writeln(MessageRepository.getInstance().get("sudoku.put-view.put"));
            data = console.readString("-> ");

            Result<String, String> validate = validator.validate(data);

            if(validate.hasError()){
                error = true;
                console.writeError(validate.error());
            }

        }while (error);

        controller.addCell(data);
        controller.register();

    }
}

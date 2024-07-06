package controllers.validators;

import controllers.GameController;

public abstract class InputPutValidator extends DataInputValidator {

    protected final GameController controller;

    protected InputPutValidator(GameController controller) {
        this.controller = controller;
    }

    protected InputPutValidator(GameController controller, DataInputValidator next) {
        super(next);
        this.controller = controller;
    }
}

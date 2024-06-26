package controllers.validators.put;

import controllers.GameController;

public abstract class InputPutValidator extends PutInputValidator {

    protected final GameController controller;

    protected InputPutValidator(GameController controller) {
        this.controller = controller;
    }

    protected InputPutValidator(GameController controller, PutInputValidator next) {
        super(next);
        this.controller = controller;
    }
}

package controllers;

public interface ControllerVisitor {

    void visit(StartController controller);
    void visit(LoadController controller);
    void visit(GameController controller);
    void visit(ExitController controller);

}

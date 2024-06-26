package controllers;

public interface Controller {
    void accept(ControllerVisitor view);
}

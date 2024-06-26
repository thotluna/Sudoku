import controllers.Controller;
import controllers.Logic;
import views.PrintScreen;

public abstract class SudokuMain {

    protected Logic logic;
    protected PrintScreen view;

    protected SudokuMain() {
        this.logic = new Logic();
        this.view = createView();
    }

    protected abstract PrintScreen createView();

    public void play(){

        Controller controller;
        do{
            controller = logic.getController();
            if(controller != null){
                controller.accept(this.view);
            }

        }while (logic.hasController());
    }

}

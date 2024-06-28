import views.PrintScreen;
import views.console.ConsoleView;

public class SudokuConsole extends SudokuMain {

    @Override
    protected PrintScreen createView() {
        return new ConsoleView();
    }

    public static void main(String[] args) {
        new SudokuConsole().play();
    }
}

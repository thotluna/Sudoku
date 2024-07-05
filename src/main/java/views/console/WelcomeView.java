package views.console;

import utils.Console;

public class WelcomeView {

    void interact(){

        String lateral = "-".repeat(5);
        String title = String.format("%s %S %s", lateral, "welcome to Sudoku Game", lateral);
        String top = "_".repeat(title.length());
        String bottom = "-".repeat(title.length());
        Console.getInstance().writeln(top);
        Console.getInstance().writeln(top);
        Console.getInstance().writeln(title);
        Console.getInstance().writeln(bottom);
        Console.getInstance().writeln(bottom + "\n");
    }
}

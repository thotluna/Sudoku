package views.console;

import utils.WithConsole;

public class WelcomeView extends WithConsole {

    void interact(){

        String lateral = "-".repeat(5);
        String title = String.format("%s %S %s", lateral, "welcome to Sudoku Game", lateral);
        String top = "_".repeat(title.length());
        String bottom = "-".repeat(title.length());
        console.writeln(top);
        console.writeln(top);
        console.writeln(title);
        console.writeln(bottom);
        console.writeln(bottom + "\n");
    }
}

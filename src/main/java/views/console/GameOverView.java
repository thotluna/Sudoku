package views.console;

import utils.Colors;
import utils.Console;

public class GameOverView {
    public void interact() {

        String banner = """
                   ██╗   ██╗ ██████╗ ██╗   ██╗    ██╗  ██╗ █████╗ ██╗   ██╗███████╗    ██╗    ██╗ ██████╗ ███╗   ██╗
                   ╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║  ██║██╔══██╗██║   ██║██╔════╝    ██║    ██║██╔═══██╗████╗  ██║
                    ╚████╔╝ ██║   ██║██║   ██║    ███████║███████║██║   ██║█████╗      ██║ █╗ ██║██║   ██║██╔██╗ ██║
                     ╚██╔╝  ██║   ██║██║   ██║    ██╔══██║██╔══██║╚██╗ ██╔╝██╔══╝      ██║███╗██║██║   ██║██║╚██╗██║
                      ██║   ╚██████╔╝╚██████╔╝    ██║  ██║██║  ██║ ╚████╔╝ ███████╗    ╚███╔███╔╝╚██████╔╝██║ ╚████║
                      ╚═╝    ╚═════╝  ╚═════╝     ╚═╝  ╚═╝╚═╝  ╚═╝  ╚═══╝  ╚══════╝     ╚══╝╚══╝  ╚═════╝ ╚═╝  ╚═══╝
                                                                                                                   \s
                """;

        Console.getInstance().writeln("");
        Console.getInstance().writeln("");
        Console.getInstance().writeln("");

        Console.getInstance().writeln(Colors.BLUE.get() + banner + Colors.DEFAULT.get());

    }

    public static void main(String[] args) {
        new GameOverView().interact();
    }

}

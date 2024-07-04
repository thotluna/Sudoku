package views.console;

import utils.Colors;
import utils.WithConsole;

public class GameOverView extends WithConsole {
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

        console.writeln("");
        console.writeln("");
        console.writeln("");

        console.writeln(Colors.BLUE.get() + banner + Colors.DEFAULT.get());

    }

    public static void main(String[] args) {
        new GameOverView().interact();
    }

}

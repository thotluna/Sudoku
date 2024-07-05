package views.console.menu;

import utils.Colors;

public class CommandSupport {

    public static String createTitleCommand(String title, int order){
        return String.format("%s%d.- %s%S.%s", Colors.CYAN.get(), order, Colors.BLUE.get(),
                title, Colors.DEFAULT.get());
    }
}

package views.console;

import models.Board;
import models.Cell;
import utils.Colors;
import utils.Console;

public class BoardView {

    public void interact(Board boardNow, int helpAvailable) {

        Console.getInstance().writeln(Colors.YELLOW.get() + "HELP: " + helpAvailable + Colors.DEFAULT.get());

        Cell[][] board = boardNow.get();
        String head = Colors.BLUE.get() + "-".repeat(41) + Colors.DEFAULT.get();

        Console.getInstance().writeln(head);
        for (int row = 0; row < 9; row++) {
            int letterNumber = 65 + row;
            String line = String.format("%s %s",  (char) letterNumber, formatLine(board[row]));
            Console.getInstance().writeln(line);

            if((row + 1) % 3 == 0){
                Console.getInstance().writeln(head);
            }
        }

        Console.getInstance().writeln("     1   2   3    4   5   6    7   8   9  ");


    }

    private String formatLine(Cell[] column){
        StringBuilder lineFormat = new StringBuilder(  Colors.BLUE.get() + "||" + Colors.DEFAULT.get());
        for (int i = 0; i < column.length; i++) {
            lineFormat.append(formatCell(column[i]));
            if((i + 1) % 3 == 0) {
                lineFormat.append(formatBase("||"));
            }else{
                lineFormat.append(formatBase("|"));
            }
        }

        return lineFormat.toString();
    }

    private String formatCell(Cell cell){
        StringBuilder cellValue = new StringBuilder();

        switch (cell.type()){
            case FIXED -> cellValue.append(Colors.BLUE.get())
                    .append(" ")
                    .append(cell.value())
                    .append(" ")
                    .append(Colors.DEFAULT.get());
            case CANDIDATE -> cellValue.append(Colors.DEFAULT.get())
                    .append(" ")
                    .append(cell.value() == 0 ? "-" : cell.value())
                    .append(" ")
                    .append(Colors.DEFAULT.get());
            case HIGHLIGHT -> cellValue.append(Colors.BLUE_BACKGROUND.get())
                    .append(Colors.BLACK.get())
                    .append(" ")
                    .append(cell.value())
                    .append(" ")
                    .append(Colors.DEFAULT.get());
            case HELP -> cellValue.append(Colors.YELLOW.get())
                    .append(" ")
                    .append(cell.value())
                    .append(" ")
                    .append(Colors.DEFAULT.get());
            case ERROR -> cellValue.append(Colors.RED.get())
                    .append(" ")
                    .append(cell.value())
                    .append(" ")
                    .append(Colors.DEFAULT.get());

        }

        return cellValue.toString();
    }

    private String formatBase(String word){
        return Colors.BLUE.get() + word + Colors.DEFAULT.get();
    }
}

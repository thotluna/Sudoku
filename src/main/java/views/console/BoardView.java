package views.console;

import models.Cell;
import utils.Colors;
import utils.WithConsole;

public class BoardView extends WithConsole {

    public void interact(Cell[][] board) {
        String head = Colors.BLUE.get() + "-".repeat(41) + Colors.DEFAULT.get();

        console.writeln(head);
        for (int row = 0; row < 9; row++) {
            console.writeln(formatLine(board[row]));

            if((row + 1) % 3 == 0){
                console.writeln(head);
            }
        }
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

        switch (cell.getType()){
            case FIXED -> cellValue.append(Colors.BLUE.get())
                    .append(" ")
                    .append(cell.getValue())
                    .append(" ")
                    .append(Colors.DEFAULT.get());
            case CANDIDATE -> cellValue.append(Colors.DEFAULT.get())
                    .append(" ")
                    .append(cell.getValue())
                    .append(" ")
                    .append(Colors.DEFAULT.get());
            case HIGHLIGHT -> cellValue.append(Colors.BLUE_BACKGROUND.get())
                    .append(" ")
                    .append(cell.getValue())
                    .append(" ")
                    .append(Colors.DEFAULT.get());
            case HELP -> cellValue.append(Colors.YELLOW.get())
                    .append(" ")
                    .append(cell.getValue())
                    .append(" ")
                    .append(Colors.DEFAULT.get());
            case ERROR -> cellValue.append(Colors.RED.get())
                    .append(" ")
                    .append(cell.getValue())
                    .append(" ")
                    .append(Colors.DEFAULT.get());

        }

        return cellValue.toString();
    }

    private String formatBase(String word){
        return Colors.BLUE.get() + word + Colors.DEFAULT.get();
    }
}

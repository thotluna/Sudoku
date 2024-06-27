package views.console;

import utils.Colors;
import utils.Console;

public class BoardView {

    Console console;

    public BoardView() {
        console = Console.getInstance();
    }

    public void interact(int[][] board) {


        String head = Colors.BLUE.get() + "-".repeat(41) + Colors.DEFAULT.get();


        console.writeln(head);
        for (int row = 0; row < 9; row++) {
            console.writeln(formatLine(board[row]));

            if((row + 1) % 3 == 0){
                console.writeln(head);
            }
        }
    }

    private String formatLine(int[] line){
        StringBuilder lineFormat = new StringBuilder(  Colors.BLUE.get() + "||" + Colors.DEFAULT.get());
        for (int i = 0; i < line.length; i++) {
            lineFormat.append(formatCell(line[i]));
            if((i + 1) % 3 == 0) {
                lineFormat.append(formatBase("||"));
            }else{
                lineFormat.append(formatBase("|"));
            }
        }

        return lineFormat.toString();
    }

    private String formatCell(int value){
        StringBuilder cellValue = new StringBuilder();
        if(value > 0){
            cellValue.append(Colors.BLUE.get()).append(" ").append(value).append(" ").append(Colors.DEFAULT.get());
        }
        else {
            cellValue.append(" ").append(value).append(" ");
        }
        return cellValue.toString();
    }

    private String formatBase(String word){
        return Colors.BLUE.get() + word + Colors.DEFAULT.get();
    }
}

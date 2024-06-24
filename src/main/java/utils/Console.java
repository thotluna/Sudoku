package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Console {

    private static Console instance;

    private Console(){}

    public static Console getInstance(){
        if(instance == null){
            instance = new Console();
        }
        return instance;
    }

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public String readString(String title) {
        String input = null;
        this.write(title);
        try {
            input = this.bufferedReader.readLine();
        } catch (Exception ex) {
            System.err.print(ex.getMessage());
        }
        return input;
    }

    @SuppressWarnings("unused")
    public String readString() {
        return this.readString("");
    }

    @SuppressWarnings("unused")
    public int readInt(String title) {
        int input = 0;
        boolean ok = false;
        do {
            try {
                input = Integer.parseInt(this.readString(title));
                ok = true;
            } catch (Exception ex) {
                this.writeError("integer");
            }
        } while (!ok);
        return input;
    }

    public char readChar(String title) {
        char charValue = ' ';
        boolean ok = false;
        do {
            String input = this.readString(title);
            if (input.length() != 1) {
                this.writeError("character");
            } else {
                charValue = input.charAt(0);
                ok = true;
            }
        } while (!ok);
        return charValue;
    }

    public void write(String message, Object ... args){
        System.out.printf(message, args);
    }

    public void writeln(String message, Object ... args){
        this.write(message, args);
        System.out.println();
    }


    public void writeError(String errorMessage){
        System.out.println(Colors.RED.get() + errorMessage + Colors.DEFAULT.get());
    }
}

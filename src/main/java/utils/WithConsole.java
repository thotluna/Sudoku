package utils;

public abstract class WithConsole {

    protected Console console;

    protected WithConsole() {
        console = new Console();
    }

    public void setConsole(Console console){
        this.console = console;
    }
}

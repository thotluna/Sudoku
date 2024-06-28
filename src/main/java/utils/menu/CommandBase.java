package utils.menu;

public abstract class CommandBase {

    protected final String title;

    protected CommandBase(String title) {
        this.title = title;
    }

    public abstract void execute();
    public abstract boolean isActive();

    public String getTitle() {
        return title;
    }
}

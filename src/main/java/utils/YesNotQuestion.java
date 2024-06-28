package utils;

@SuppressWarnings("unused")
public class YesNotQuestion extends WithConsole {

    private char question;


    public YesNotQuestion read(String ask) {
        do {
            question = console.readChar(ask + " (y/n):");
        } while (question != 'y' && question != 'Y' && question != 'n' && question != 'N');

        return this;
    }

    public boolean isAffirmative() {
        return question == 'y' || question == 'Y';
    }

}

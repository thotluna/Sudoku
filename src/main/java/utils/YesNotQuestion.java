package utils;

public class YesNotQuestion {

    private char question;

    public YesNotQuestion read(String ask) {
        do {
            question = Console.getInstance().readChar(ask + " (y/n):");
        } while (question != 'y' && question != 'Y' && question != 'n' && question != 'N');

        return this;
    }

    public boolean isAffirmative() {
        return question == 'y' || question == 'Y';
    }

}

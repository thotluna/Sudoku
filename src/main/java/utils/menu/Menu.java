package utils.menu;

import utils.Colors;
import utils.WithConsole;
import utils.controllers.InRageValidator;
import utils.controllers.IsNumberValidator;
import utils.controllers.Validator;

import java.util.ArrayList;
import java.util.List;

public class Menu extends WithConsole {
    private final List<CommandBase> commandList;
    private final String title;

    String errorNonNumber;
    String errorOutRage;

    static final String ERROR_NON_NUMBER = "Only numbers allowed";
    static final String ERROR_OUT_RAGE = "only numbers allowed between 1 and";


    public Menu(String title){
        assert title != null && !title.isBlank() && !title.isEmpty();
        this.title = title;
        this.commandList = new ArrayList<>();
        this.errorNonNumber = ERROR_NON_NUMBER;
        this.errorOutRage = ERROR_OUT_RAGE;
    }

    public Menu(String title, String errorNonNumber, String errorOutRange) {
        this(title);

        assert errorNonNumber != null && !title.isBlank() && !title.isEmpty();
        assert errorOutRange != null && !title.isBlank();

        this.errorNonNumber = errorNonNumber;
        this.errorOutRage = errorOutRange;
    }

    public void addCommand(CommandBase command){
        commandList.add(command);
    }

    public void execute(){
        List<CommandBase> commands =getCommandListActive();
        assert !commands.isEmpty();

        printCommands(commands);
        console.writeln("");
        console.writeln(title);

        int optionSelected = getOptionSelected();

        commands.get(optionSelected -1).execute();
    }

    public int getOptionSelected(){
        String error;
        String errorValidator = String.format("%s %d",this.errorOutRage,
                commandList.stream().filter(CommandBase::isActive).toList().size());
        Validator validator = getValidator(this.errorNonNumber, errorValidator);
        String possibleCommand;
        do{
            possibleCommand = console.readString("-> ");
            error = validator.validate(possibleCommand);
            if(error != null){
                console.writeError(error);
                console.writeln("");
            }
        }while (error != null);
        return Integer.parseInt(possibleCommand);
    }

    private void printCommands(List<CommandBase> commands) {
        for (int i = 0; i < commands.size(); i++) {
            String titleCommand = String.format("%s%d.- %s%S.%s", Colors.CYAN.get(), i+1, Colors.BLUE.get(),
                    commands.get(i).getTitle(), Colors.DEFAULT.get());
            console.writeln(titleCommand);
        }
    }

    private List<CommandBase> getCommandListActive(){
        return commandList.stream().filter(CommandBase::isActive).toList();
    }

    private Validator getValidator(String errorNonNumber, String errorRage){
        return new IsNumberValidator(errorNonNumber, new InRageValidator( commandList.stream().filter(CommandBase::isActive).toList().size(),errorRage));
    }
}

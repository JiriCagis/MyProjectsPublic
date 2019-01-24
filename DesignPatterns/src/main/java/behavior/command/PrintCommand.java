package behavior.command;

/**
 * Created by osboxes on 08/01/18.
 */
public class PrintCommand implements Command {

    private final String text;

    public PrintCommand(String text){
        this.text = text;
    }

    @Override
    public void execute() {
        System.out.println(text);
    }
}

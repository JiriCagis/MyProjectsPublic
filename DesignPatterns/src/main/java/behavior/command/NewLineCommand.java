package behavior.command;

/**
 * Created by osboxes on 08/01/18.
 */
public class NewLineCommand implements Command {

    @Override
    public void execute() {
        System.out.println();
    }
}

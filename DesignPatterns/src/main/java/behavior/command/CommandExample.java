package behavior.command;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by osboxes on 08/01/18.
 */
public class CommandExample {

    public static void main(String args[]){
        List<Command> commands = new LinkedList<>();

        commands.add(new PrintCommand("First line."));
        commands.add(new NewLineCommand());
        commands.add(new PrintCommand("Second line."));
        commands.add(new NewLineCommand());
        commands.add(new PrintCommand("Third line."));

        for(Command command:commands){
            command.execute();
        }
    }
}

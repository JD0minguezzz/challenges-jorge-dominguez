import java.util.ArrayList;

public class Invoker {

    public ArrayList<Command> commands;

    public Invoker() {
        commands = new ArrayList<>();
    }

    public void takeCommand(Command command) {
        this.commands.add(command);
    }

    public void placeCommand(Message msg) {
        for (Command command : commands) {
            command.execute(msg);
        }
    }

}

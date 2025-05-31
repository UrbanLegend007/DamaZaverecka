package Logistics;

import java.util.Stack;

public class CommandManager {
    private Stack<Command> commandHistory = new Stack<>();

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    private String warning;

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.add(command);
    }

    public void undo() {
        if (!commandHistory.isEmpty()) {
            Command last = commandHistory.pop();
            last.undo();
            warning = "Move undone.";
        } else {
            warning = "No moves to undo.";
        }
    }
}

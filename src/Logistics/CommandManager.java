package Logistics;

import java.util.Stack;

/**
 * The {@code CommandManager} class is responsible for executing and managing
 * a history of commands that implement the {@link Command} interface.
 * <p>
 * This class enables support for undoing previously executed commands
 * using a stack-based structure.
 */
public class CommandManager {

    /**
     * A stack that keeps track of executed commands.
     * The most recently executed command is on top of the stack.
     */
    private Stack<Command> commandHistory = new Stack<>();

    /**
     * A warning or status message related to command execution or undoing.
     */
    private String warning;

    /**
     * Returns the current warning or status message.
     *
     * @return the warning message as a {@code String}
     */
    public String getWarning() {
        return warning;
    }

    /**
     * Sets the warning or status message.
     *
     * @param warning the warning message to set
     */
    public void setWarning(String warning) {
        this.warning = warning;
    }

    /**
     * Executes the given command and stores it in the history stack.
     *
     * @param command the {@code Command} to execute
     */
    public void executeCommand(Command command) {
        command.execute();
        commandHistory.add(command);
    }

    /**
     * Undoes the most recently executed command, if available.
     * <p>
     * Updates the {@code warning} message to reflect the outcome.
     * If there are no commands to undo, sets the warning accordingly.
     */
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

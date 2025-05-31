package Logistics;

/**
 * The {@code Command} interface defines a contract for executing and undoing operations.
 * <p>
 * It is part of the Command design pattern and is commonly used to encapsulate
 * requests as objects, allowing for parameterization, queuing, logging,
 * and undoable operations in a consistent way.
 * <p>
 * In the context of a board game (e.g., Checkers), this interface can be implemented
 * to represent player moves, including the ability to undo them.
 */
public interface Command {

    /**
     * Executes the command.
     * This method should contain the logic to apply the intended operation.
     */
    void execute();

    /**
     * Undoes the command.
     * This method should revert any changes made by the {@code execute()} method.
     */
    void undo();
}

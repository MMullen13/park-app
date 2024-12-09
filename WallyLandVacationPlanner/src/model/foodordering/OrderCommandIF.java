package model.foodordering;

/**
 * Interface to define the methods that the concrete command pattern classes
 * must implement
 * @author Mark
 */
public interface OrderCommandIF {
    
    /**
     * Executes a command
     */
    public void execute();
    
    /**
     * Undoes the command
     */
    public void undo();
    
}

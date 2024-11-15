/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

import java.util.Stack;

/**
 * Manages the commands for the command pattern
 * @author theme
 */
public class CommandManager {
    
    private Stack<OrderCommandIF> commandHistory = new Stack<>();
    private OrderCommandIF lastCommand;
    
    /**
     * Executes the command for the concrete command class. Adds the command
     * to a stack for undo functionality.
     * @param command The concrete command class to execute on
     */
    public void executeCommand(OrderCommandIF command){
        command.execute();
        commandHistory.push(command);
        lastCommand = command;
        
    }
    
    /**
     * Runs the undo method for the last item in the command history stack and 
     * if ran, removes the command from the stack
     * @return True of the undo method ran. False if it did not. 
     */
    public boolean undoLastCommand(){
        if (!commandHistory.isEmpty()){
            OrderCommandIF removeLastCommand = commandHistory.pop();
            removeLastCommand.undo();
            return true;
            
        }   
        return false;
    }
    
    /**
     * Gets the last command that was ran
     * @return The last command ran
     */
    public OrderCommandIF getLastCommand() {
        return lastCommand;
    }

    /**
     * Overrides to string method
     * @return The commands in the command history
     */
    @Override
    public String toString() {
        return "CommandManager{" + "commandHisotry=" + commandHistory + '}';
    }

}

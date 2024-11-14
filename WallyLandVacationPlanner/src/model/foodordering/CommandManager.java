/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

import java.util.Stack;

/**
 *
 * @author theme
 */
public class CommandManager {
    
    private Stack<OrderCommandIF> commandHisotry = new Stack<>();
    
    public void executeCommand(OrderCommandIF command){
        command.execute();
        commandHisotry.push(command);
        //System.out.println(commandHisotry);
    }
    
    public boolean undoLastCommand(){
        if (!commandHisotry.isEmpty()){
            OrderCommandIF lastCommand = commandHisotry.pop();
            lastCommand.undo();
            return true;
            
        }   
        return false;
    }

    @Override
    public String toString() {
        return "CommandManager{" + "commandHisotry=" + commandHisotry + '}';
    }
    
    
    
}

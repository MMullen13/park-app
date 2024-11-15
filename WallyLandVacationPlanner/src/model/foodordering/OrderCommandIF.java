/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.foodordering;

/**
 * Interface to define the methods that the concrete command pattern classes
 * must implement
 * @author theme
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

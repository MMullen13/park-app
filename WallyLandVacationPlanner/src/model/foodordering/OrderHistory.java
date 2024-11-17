/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

import controller.foodordering.FoodController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Class stores the past histories to a list which is saved to a file to persist
 * between runs
 * @author theme
 */
public class OrderHistory implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(FoodController.class.getName());
    private static ArrayList<OrderHistory> historyList = new ArrayList<>();
    private String orderNumber;
    private double totalCost;
    private String date;
    private Eatery eatery;
    
    /**
     * Constructor. Create an entry that will be added to the order history.
     * @param orderNumber The Order Number
     * @param date Date of the Order
     * @param eatery The Eatery for the order
     * @param totalCost The cost of the order
     */
    public OrderHistory(String orderNumber, String date, Eatery eatery, double totalCost) {
        this.orderNumber = orderNumber;
        this.date = date;
        this.eatery = eatery;
        this.totalCost = totalCost;
               
    }
    
    /**
     * Adds an order to the history list that will be saved to a file.
     */
    public void addToHistory(){
       historyList.add(this);
       
   }
   
   /**
    * Saves the current order history to a file "orderHistory.dat". Serializes the
    * history list and writes it to the file. This save files allows order history
    * to persist between runs and be loaded at a later time. If there is an issue
    * with the file, Sever log message is created.
    */
    public static void saveOrderHistory() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/orderHistory.dat"))) {
            out.writeObject(historyList);
            
            
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to save order history", e);
        }
    }

    /**
     * Loads and deserializes the order history from saved file "orderHistory.dat" to get the past
     * orders that were created. If file is not found a sever log mesage is created.
     */
    @SuppressWarnings("unchecked")
    public static void loadOrderHistory() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/orderHistory.dat"))) {
            historyList = (ArrayList<OrderHistory>) in.readObject();                       
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Failed to load order history", e);
        }
    }
    
    /**
     * Returns the ArrayList containing the order history
     * @return 
     */
    public static ArrayList<OrderHistory> getHistoryList() {
        return historyList;
    }

    /**
     * Gets the order number from the order in the history
     * @return Order Number
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * Gets the total cost of the order saved in the history
     * @return 
     */
    public double getTotalCost() {
        return totalCost;
    }
    
    /**
     * Gets the date of the order in the order history
     * @return Date of the order as a string
     */
    public String getDate() {
        return date;
    }
    
    /**
     * Gets the eatery for the order in the order history
     * @return 
     */
    public Eatery getEatery() {
        return eatery;
    }

    @Override
    public String toString() {
        return "OrderHistory{" + "orderNumber=" + orderNumber + ", totalCost=" + totalCost + ", date=" + date + ", eatery=" + eatery + '}';
    }
    
    
    
    
    

}

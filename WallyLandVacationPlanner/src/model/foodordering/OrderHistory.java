/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Keeps a list of past orders in the application
 * @author theme
 */
public class OrderHistory implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private static ArrayList<OrderHistory> historyList = new ArrayList<>();
    private String orderNumber;
    private double totalCost;
    private String date;
    private Eatery eatery;
    
    /**
     * Creates a new order history entry to track past orders
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
     * Adds an order to the history list
     */
    public void addToHistory(){
       historyList.add(this);
       
   }
   
   /**
    * Saves the orderHisotry so It can be loaded between app runs
    */
    public static void saveOrderHistory() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/orderHistory.dat"))) {
            out.writeObject(historyList);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the order history from saved file for user to view past orders
     */
    @SuppressWarnings("unchecked")
    public static void loadOrderHistory() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/orderHistory.dat"))) {
            historyList = (ArrayList<OrderHistory>) in.readObject();
            System.out.println(historyList);
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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

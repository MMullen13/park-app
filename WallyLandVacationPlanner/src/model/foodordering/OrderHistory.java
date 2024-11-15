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
 *
 * @author theme
 */
public class OrderHistory implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private static ArrayList<OrderHistory> historyList = new ArrayList<>();
    private String orderNumber;
    private double totalCost;
    private String date;
    private Eatery eatery;

    public OrderHistory(String orderNumber, String date, Eatery eatery, double totalCost) {
        this.orderNumber = orderNumber;
        this.date = date;
        this.eatery = eatery;
        this.totalCost = totalCost;
               
    }
    
   public void addToHistory(){
       historyList.add(this);
       System.out.println(historyList);
   }
   
   // Method to save order history to a file
    public static void saveOrderHistory() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/orderHistory.dat"))) {
            out.writeObject(historyList); // Save the history list
            System.out.println(historyList);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load order history from a file
    @SuppressWarnings("unchecked")
    public static void loadOrderHistory() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/orderHistory.dat"))) {
            historyList = (ArrayList<OrderHistory>) in.readObject();
            System.out.println(historyList);
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<OrderHistory> getHistoryList() {
        return historyList;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getDate() {
        return date;
    }

    public Eatery getEatery() {
        return eatery;
    }

    @Override
    public String toString() {
        return "OrderHistory{" + "orderNumber=" + orderNumber + ", totalCost=" + totalCost + ", date=" + date + ", eatery=" + eatery + '}';
    }
    
    
    
    
    

}

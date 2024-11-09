/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author theme
 */
public class OrderHistory {
    
    private static ArrayList<OrderHistory> historyList = new ArrayList<>();
    private String orderNumber;
    private double totalCost;
    private Date date;

    public OrderHistory(String orderNumber, double totalCost, Date date) {
        this.orderNumber = orderNumber;
        this.totalCost = totalCost;
        this.date = date;
    }
    
    public static void addHistoryEntry(OrderHistory orderHistory){
        historyList.add(orderHistory);
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

    public Date getDate() {
        return date;
    }
    
    
    
    
    
}

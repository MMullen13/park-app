package controller.foodordering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.foodordering.*;
import view.foodordering.*;

/**
 * Controller class. Works with Food Order Module and food order view classes
 * to populate user interface and update Food orders based on user selections.
 * @author Mark
 */
public class FoodController {
    

   private OrderViewForm orderView;
   private OrderConfirmationViewForm confirmationView;
   private Menu menu;
   private MenuItem menuItem;
   private ArrayList<Eatery> eateries;
   private CommandManager commandManager;
   private OrderHistory orderHistory;
   private boolean orderPickedUp = true;
   
   private Order currentOrder;
   private OrderHistory history;

   
   /**
    * Class Constructor. Populates the menus, sets the menus. Creates a list of
    * eateries, initializes a new order and the menu for the order. Starts the 
    * command manager for command pattern implementation.
    */
    public FoodController() {
        
        this.menu = new Menu();
        this.menuItem = new MenuItem();
        this.eateries = menu.getEateries();
        this.currentOrder = new Order();
        initializeMenus();
        this.commandManager = new CommandManager();
        
               
    }
    
    /**
     * Sets the order view for this controller. Associates the Order Form with 
     * the controller.
     * @param orderView The order view for this controller
     */
    public void setOrderView(OrderViewForm orderView){
        this.orderView = orderView;
    }
    
    /**
     * Sets the confirmation view for this controller. Associates the confirmation 
     * view with the controller.
     * @param confirmationView Confirmation view associated with this controller. 
     */
    public void setConfirmationView(OrderConfirmationViewForm confirmationView){
        this.confirmationView = confirmationView;
    }

    /**
     * Gets the current list of eateries
     * @return A list of all the eateries
     */
    public ArrayList<Eatery> getEateries(){
        return menu.getEateries();
    }
    
    /**
     * Creates categorized map of menu items for each eatery. Item types are
     * linked with a list of menu items that are of that type.
     * @param eateryName The name of the eatery to get the menu items for each category
     * @return A map of Menu Items where the key is the item type and the values are
     * a list of menu items in that category for the selected eatery
     */
    public Map<String, ArrayList<MenuItem>> getMenuItems(String eateryName){
        
        Map<String, ArrayList<MenuItem>> itemByCategory = new HashMap<>();
        
        //types of items that can be ordered
        itemByCategory.put("Drinks", new ArrayList<>());
        itemByCategory.put("Appetizers", new ArrayList<>());
        itemByCategory.put("Mains", new ArrayList<>());
        itemByCategory.put("Sides", new ArrayList<>());
        itemByCategory.put("Desserts", new ArrayList<>());
        
        //populates the item types for users to select for each type and eatery.
        for(Eatery eatery : eateries){
            if(eatery.getEateryName().equals(eateryName)){
                itemByCategory.get("Drinks").addAll(MenuItem.getDrinks(eateryName));
                itemByCategory.get("Appetizers").addAll(MenuItem.getApps(eateryName));
                itemByCategory.get("Mains").addAll(MenuItem.getMains(eateryName));
                itemByCategory.get("Sides").addAll(MenuItem.getSides(eateryName));
                itemByCategory.get("Desserts").addAll(MenuItem.getDesserts(eateryName));
                break;
            }
        }
        return itemByCategory;
        
    }
    
    /**
     * Adds a new item to the order
     * @param item Item to be added to the order
     */
    public void addItemToOrder(MenuItem item){
        currentOrder.addItem(item);
    }
    
    /**
     * Removes the last added item to the order list.
     */
    public void removeLastItem(){
        RemoveLastItemCommand removeCommand = new RemoveLastItemCommand(currentOrder);
        commandManager.executeCommand(removeCommand);
        
    }
    
    /**
     * Clears all of the items in the current order.
     */
    public void clearAllItems(){
        ClearAllItemsCommand clearCommand = new ClearAllItemsCommand(currentOrder);
        commandManager.executeCommand(clearCommand);
        
    }
    
    /**
     * Undo method for the last command executed
     * @return True or False if the undo has been completed
     */
    public boolean undoLastCommand(){
        return commandManager.undoLastCommand();
    }
    
    /**
     * Gets the last command executed by the command manager for undo method
     * @return The last executed command
     */
    public OrderCommandIF getLastCommand(){
        return commandManager.getLastCommand();
    }
    
    /**
     * Gets the item that was last removed from the order for Command Manager
     * @return the menu item that was removed
     */
    public MenuItem getremovedItem(){
        MenuItem itemRemoved = currentOrder.getLastItemRemoved();
        return itemRemoved;
              
    }
    
    /**
     * Checks that the confirmation for is not null. Sets the confirmation view
     * visible.
     */
    public void showOrderConfirmationView(){
        if (confirmationView != null){
            confirmationView.setVisible(true)
;        }
        else{
            
            System.out.println("Null");
        }
    }
    
    /**
     * Checks if an order has been marked as picked up. 
     * @return 
     */
    public boolean isOrderPickedUp(){
        return orderPickedUp;
    }
    
    /**
     * Marks and order as picked up.
     */
    public void markedOrderAsPickedUp(){
        orderPickedUp = true;
    }
    
    /**
     * Marks and order as not picked up.
     */
    public void markedOrderAsNotPickedUp(){
        orderPickedUp = false;
    }
    
    /**
     * Calculates the order total with tax of 7%
     * @param quantity Number of the items
     * @param item Price of the item
     */
    public void calculateTotal(int quantity, double item){
        currentOrder.calculateTotal(quantity, item);
    }
    
    /**
     * Resets the total of the order to $0.00
     */
    public void resetTotal(){
        currentOrder.resetTotal();
    }
    
    /**
     * Gets the order total
     * @return double for the total of the order
     */
    public double getTotal(){
        return currentOrder.getTotal();
    }
    
    /**
     * Creates a new order
     * @param eatery The eatery that the order will be created for
     */
    public void newOrder(Eatery eatery){
        if (orderPickedUp){
            currentOrder = new Order(eatery);
            //orderPickedUp = false;
        }
                
    }
    
    /**
     * Creates a new entry into the order history list for tracking past orders.
     * @param orderNumber The order number of the order to save
     * @param orderDate The date of the order to be saved
     * @param eatery The eatery associated with the order
     * @param totalCost The total cost of the order to be saved.
     */
    public void createNewHistoryEntry(String orderNumber, String orderDate, Eatery eatery, double totalCost){
        orderHistory = new OrderHistory(orderNumber, orderDate, eatery, totalCost);
        orderHistory.addToHistory();
    }
    
    /**
     * Saves an order entry to the order history for persistence to a file.
     */
    public void saveOrderHistory(){
        OrderHistory.saveOrderHistory();
    }
    
    /**
     * Loads the order history from a file
     */
    public void loadOrderHistory(){
        OrderHistory.loadOrderHistory();
    }
    
    /**
     * The list of past orders.
     * @return An ArrayList of past orders
     */
    public ArrayList<OrderHistory> getOrderHistory(){
        return OrderHistory.getHistoryList();
    }
    
    /**
     * Gets the order number 
     * @return A String of the random generated order number
     */
    public String getOrderNumber(){
        return currentOrder.getOrderID();
    }
    
    /**
     * Creates a new eatery without a menu
     * @param newEatery Eatery to be created
     */
    public void updateEatery(Eatery newEatery){
        currentOrder.setEatery(newEatery);
    }
    
    /**
     * Gets the information on the selected eatery
     * @return Eatery information
     */
    public Eatery getEatery(){
        return currentOrder.getEatery();
    }
    
    /**
     * Gets the order items in the current order
     * @return An ArrayList of the items
     */
    public ArrayList<MenuItem> getOrderItems(){
        return currentOrder.getOrderItems();
    }
    
    /**
     * Sets the pickup time selected by the user
     * @param pickupTime The time order is to be picked up
     * @param pickupDate The date the order is to be picked up
     */
    public void setPickupInfo(String pickupTime, String pickupDate){
        currentOrder.setPickupTime(pickupTime);
        currentOrder.setPickupDate(pickupDate);
    }
    
    /**
     * Gets the pickup date for the order
     * @return String of the pickup date
     */
    public String getPickupDate(){
        return currentOrder.getPickupDate();
        
    }
    
    /**
     * Gets the order pickup time
     * @return String of the pickup time.
     */
    public String getPickupTime(){
        return currentOrder.getPickupTime();
    }
   
    /**
     * Creates the default eateries and menus from model package
     */
    private void initializeMenus(){
        menu.createDefaults();
    }
    
    // Getter method for the OrderViewForm
    public OrderViewForm getOrderView() {
        return orderView;
    }

    // Getter method for the OrderConfirmationViewForm
    public OrderConfirmationViewForm getConfirmationView() {
        return confirmationView;
    }

}

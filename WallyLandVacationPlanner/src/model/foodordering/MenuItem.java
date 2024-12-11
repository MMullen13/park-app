package model.foodordering;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates and manages menu items for the application
 * @author Mark
 */
public class MenuItem {
    
    private String itemName;
    private double price;
    private int quantity;

    /**
     * Empty class constructor to create an instance of class without an item 
     * or price associated with it.
     */
    public MenuItem() {
    }
    
    /**
     * Parameterized class constructor. Does not require a quantity to be created.
     * used for making new items and prices.
     * @param itemName Name of the item
     * @param price Price of the item
     */
    public MenuItem(String itemName, double price){
        this.itemName = itemName;
        this.price = price;
    }

    
    /**
     * MenuItem Constructor. Allows for a new menu item to be added to an order
     * with a quantity associated with it.
     * @param itemName Name of the menu item
     * @param price price for the item
     * @param quantity number of the items
     */
    public MenuItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
    
    /**
     * Creates default drinks for eatery. Separates the items by item type of
     * Drinks, apps, mains, sides, and desserts for each eatery.
     * @param eateryName Name of the eatery
     * @return list of the drinks and the prices
     */
    public static List<MenuItem> getDrinks(String eateryName){
        List<MenuItem> drinks = new ArrayList<>();
        
        switch (eateryName) {
            case "Bistro Bella" -> {
                drinks.add(new MenuItem("Sparkling Water", 3.99));
                drinks.add(new MenuItem("Mango Smoothie", 6.99));
                drinks.add(new MenuItem("Soda Pop", 5.99));
                drinks.add(new MenuItem("Coffee", 2.99));
            }
            case "Grill & Chill" -> {
                drinks.add(new MenuItem("Soda Pop", 5.99));
                drinks.add(new MenuItem("Slushie", 6.99));
                drinks.add(new MenuItem("Beer", 8.99));
                drinks.add(new MenuItem("Bottled Water", 2.99));
            }
            case "Chicken Kitchen" -> {
                drinks.add(new MenuItem("Soda Pop", 5.99));
                drinks.add(new MenuItem("Sweet Tea", 4.99));
                drinks.add(new MenuItem("Lemonade", 6.99));
                drinks.add(new MenuItem("Fruit Punch", 6.99));
            }
            default -> {
            }
        }
        return drinks;
    }
    
    /**
     * Creates default mains for eatery
     * @param eateryName Name of the eatery
     * @return list of mains and the prices
     */
    public static List<MenuItem> getMains(String eateryName){
        List<MenuItem> mains = new ArrayList<>();
        
        switch (eateryName) {
            case "Bistro Bella" -> {
                mains.add(new MenuItem("Shrimp and Grits", 16.99));
                mains.add(new MenuItem("Mushroom Risotto", 14.99));
                mains.add(new MenuItem("Lobster Tail", 26.99));
                mains.add(new MenuItem("Carbonara", 19.99));
            }
            case "Grill & Chill" -> {
                mains.add(new MenuItem("Double Bacon Cheeseburger", 12.99));
                mains.add(new MenuItem("Footlong Hotdog", 8.99));
                mains.add(new MenuItem("Bratwurst", 11.99));
                mains.add(new MenuItem("Cheesesteak", 13.99));
            }
            case "Chicken Kitchen" -> {
                mains.add(new MenuItem("Chicken Tenders", 11.99));
                mains.add(new MenuItem("Chicken Sandwich", 13.99));
                mains.add(new MenuItem("Chicken and Waffles", 16.99));
                mains.add(new MenuItem("Whole Roasted Chicken", 14.99));
            }
            default -> {
            }
        }
        return mains;
    }
    
    /**
     * Creates default Apps for eatery
     * @param eateryName Name of the eatery
     * @return List of Apps and the prices
     */
    public static List<MenuItem> getApps(String eateryName){
        List<MenuItem> apps = new ArrayList<>();
        
        switch (eateryName) {
            case "Bistro Bella" -> {
                apps.add(new MenuItem("Chopped Salad", 9.99));
                apps.add(new MenuItem("French Onion Soup", 6.99));
                apps.add(new MenuItem("Fried Mushrooms", 8.99));
                apps.add(new MenuItem("Shrimp Cocktail", 12.99));                
            }
            case "Grill & Chill" -> {
                apps.add(new MenuItem("Onion Rings", 6.99));
                apps.add(new MenuItem("Loaded Fries", 8.99));
                apps.add(new MenuItem("Nachos", 5.99));
                apps.add(new MenuItem("Corndog", 4.99));
            }
            case "Chicken Kitchen" -> {
                apps.add(new MenuItem("Popcorn Chicken", 8.99));
                apps.add(new MenuItem("Chicken Wings", 13.99));
                apps.add(new MenuItem("Sampler Basket", 14.99));
                apps.add(new MenuItem("Mozerella Sticks", 7.99));
            }
            default -> {
            }
        }
        return apps;
    }
    
    /**
     * Creates default sides for eatery
     * @param eateryName Name of the eatery
     * @return List of sides and the prices
     */
    public static List<MenuItem> getSides(String eateryName){
        List<MenuItem> sides = new ArrayList<>();
        
        switch (eateryName) {
            case "Bistro Bella" -> {
                sides.add(new MenuItem("Baked Potato", 3.99));
                sides.add(new MenuItem("Asparagus", 6.99));
                sides.add(new MenuItem("Side Salad", 2.99));
                sides.add(new MenuItem("French Fries", 3.99));
            }
            case "Grill & Chill" -> {
                sides.add(new MenuItem("French Fries", 3.99));
                sides.add(new MenuItem("Onion Rings", 4.99));
                sides.add(new MenuItem("Side Salad", 2.99));
                sides.add(new MenuItem("Mac and Cheese", 5.99));
            }
            case "Chicken Kitchen" -> {
                sides.add(new MenuItem("French Fries", 3.99));
                sides.add(new MenuItem("Mashed Potatoes", 2.99));
                sides.add(new MenuItem("Coleslaw", 3.99));
                sides.add(new MenuItem("Mexican Street Corn", 5.99));
            }
            default -> {
            }
        }
        return sides;
    }
    
    /**
     * Creates default desserts for eatery
     * @param eateryName Name of eatery
     * @return List of desserts and the prices
     */
    public static List<MenuItem> getDesserts(String eateryName){
        List<MenuItem> desserts = new ArrayList<>();
        
        switch (eateryName) {
            case "Bistro Bella" -> {
                desserts.add(new MenuItem("Cheesecake", 4.99));
                desserts.add(new MenuItem("Tiramisu", 5.99));
                desserts.add(new MenuItem("Black Forest Cake", 4.99));
                desserts.add(new MenuItem("Baked Alaska", 6.99));
            }
            case "Grill & Chill" -> {
                desserts.add(new MenuItem("Ice Cream", 4.99));
                desserts.add(new MenuItem("Sherbert", 5.99));
                desserts.add(new MenuItem("Frozen Yogurt", 6.99));
                desserts.add(new MenuItem("Banana Split", 7.99));
            }
            case "Chicken Kitchen" -> {
                desserts.add(new MenuItem("Coconut Cake", 4.99));
                desserts.add(new MenuItem("Ice Cream Sandwich", 5.99));
                desserts.add(new MenuItem("Cupcake", 2.99));
                desserts.add(new MenuItem("Beignets", 6.99));
            }
            default -> {
            }
        }
        return desserts;
    }

    /**
     * Gets the name of an item menu
     * @return itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Get the price of an item
     * @return item price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the quantity of an item that was added to an order
     * @return Number of items
     */
    public int getQuantity() {
        return quantity;
    }
  
}

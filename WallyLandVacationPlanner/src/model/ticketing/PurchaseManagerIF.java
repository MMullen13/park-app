package model.ticketing;

/**
 * PurchaseManager Interface handles ticket and pass purchases.
 * 
 * @author Ana
 */
public interface PurchaseManagerIF {

    public void addToCart();

    public void makePayment();

    public void deleteItems();
}

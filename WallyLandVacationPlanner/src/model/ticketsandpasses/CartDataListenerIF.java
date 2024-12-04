package model.ticketsandpasses;

import java.util.Map;

/**
 *
 * @author Ana
 */
public interface CartDataListenerIF {
    void updateCart(Map<String, Integer> cartItems, int totalPassQuantity, double totalPrice);
}

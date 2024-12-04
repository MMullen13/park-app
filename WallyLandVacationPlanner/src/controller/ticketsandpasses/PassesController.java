package controller.ticketsandpasses;

import model.ticketsandpasses.Pass;
import model.ticketsandpasses.PurchasePassEvent;
import model.ticketsandpasses.PurchaseTicketEvent;
import view.passes.tiketsandpasses.PassesView;
import view.passes.cart.CartView;

/**
 *
 * @author Ana
 */
public class PassesController {

    protected PassesView passView;
    protected CartView cartView;

    public CartView getCartView() {
        return cartView;
    }

    public void setCartView(CartView cartView) {
        this.cartView = cartView;
    }
    protected Pass pass;

     public PassesController() {
        pass = new Pass();
    }

    public PassesView getPassView() {
        return passView;
    }

    public void setPassView(PassesView passView) {
        this.passView = passView;
    }

    public Pass getPass() {
        return pass;
    }

    public void setPass(Pass pass) {
        this.pass = pass;
    }

    public void handlePurchasePasses(PurchasePassEvent ev) {

    }
    
    public void handlePurchaseTickets(PurchaseTicketEvent ev) {

    }
}

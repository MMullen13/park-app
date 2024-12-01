package controller.ticketing;

import java.util.ArrayList;
import java.util.List;
import model.ticketing.ObserverIF;
import model.ticketing.Pass;
import model.ticketing.PassSubject;
import view.ticketing.passes.PassView;
import view.ticketing.tickets.TicketView;
import model.ticketing.PurchaseFormEventIF;
import model.ticketing.Ticket;
import model.ticketing.TicketSubject;

/**
 * Controller class responsible for managing ticket and pass purchases.
 * Communicates between the views (TicketView, PassView) and models (Ticket, Pass).
 * Implements the Observer pattern to notify observers about updates.
 * 
 * @author Ana
 */
public class TicketController {

    protected TicketView ticketView; // View for ticket-related operations
    protected PassView passView;     // View for pass-related operations
    private Ticket ticketModel;      // Model representing ticket data
    private Pass passModel;          // Model representing pass data
    private List<ObserverIF> observers; // List of observers for notifications
    private TicketSubject ticketSubject;
    public PassSubject passSubject;
    
    
    /**
     * Constructor to initialize the controller with an empty list of observers.
     */
    public TicketController() {
        this.observers = new ArrayList<>();
        this.ticketSubject = new TicketSubject(new Ticket()); // Initialize TicketSubject with a Ticket model
        this.passSubject = new PassSubject(new Pass());
    }

    /**
     * Retrieves the current ticket model.
     *
     * @return The Ticket model.
     */
    public Ticket getTicketModel() {
        return ticketModel;
    }

    /**
     * Retrieves the current pass model.
     *
     * @return The Pass model.
     */
    public Pass getPassModel() {
        return passModel;
    }

    /**
     * Sets the ticket model to be used by this controller.
     *
     * @param ticketModel The Ticket model.
     */
    public void setTicketModel(Ticket ticketModel) {
        this.ticketModel = ticketModel;
         ticketSubject = new TicketSubject(ticketModel);
    }

    /**
     * Sets the pass model to be used by this controller.
     *
     * @param passModel The Pass model.
     */
    public void setPassModel(Pass passModel) {
        this.passModel = passModel;
        passSubject = new PassSubject(passModel);
    }

    /**
     * Adds an observer to the TicketSubject.
     *
     * @param observer The observer to be added.
     */
    public void addObserver(ObserverIF observer) {
        ticketSubject.addObserver(observer);
    }
/**
     * Removes an observer from the TicketSubject.
     *
     * @param observer The observer to be removed.
     */
    public void removeObserver(ObserverIF observer) {
        ticketSubject.removeObserver(observer);
    }

    /**
     * Notifies all registered observers with a given message.
     *
     * @param message The message to notify observers about.
     */
    private void notifyObservers(String message) {
        for (ObserverIF observer : observers) {
            observer.update(message);
        }
    }

    /**
     * Sets the ticket view to be used by this controller.
     *
     * @param ticketView The TicketView object.
     */
    public void setTicketView(TicketView ticketView) {
        this.ticketView = ticketView;
    }

    /**
     * Retrieves the current ticket view.
     *
     * @return The TicketView object.
     */
    public TicketView getTicketView() {
        return ticketView;
    }

    /**
     * Sets the pass view to be used by this controller.
     *
     * @param passView The PassView object.
     */
    public void setPassView(PassView passView) {
        this.passView = passView;
    }

    /**
     * Retrieves the current pass view.
     *
     * @return The PassView object.
     */
    public PassView getPassView() {
        return passView;
    }

    /**
     * Handles ticket purchase operations through TicketSubject.
     *
     * @param ev The PurchaseFormEventIF containing ticket type and quantity details.
     */
    public void handlePurchaseTickets(PurchaseFormEventIF ev) {
        ticketSubject.addTickets(ev.getTicketType(), ev.getQuantity());
    }

    /**
     * Handles pass purchase operations.
     *
     * @param ev The PurchaseFormEventIF containing pass type and quantity details.
     */
    public void handlePurchasePasses(PurchaseFormEventIF ev) {
        passSubject.addPasses(ev.getTicketType(), ev.getQuantity());
    }
}

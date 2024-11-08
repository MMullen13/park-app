package view.loginsignup;

import java.util.EventListener;
import java.util.EventObject;

/**
 * Interface for listening to form events. This interface defines the method 
 * that must be implemented by any class that wants to handle form-related events.
 * The class implementing this interface will be notified when a form event occurs.
 * 
 * Author: Ana
 */
public interface FormListenerIF extends EventListener {

    /**
     * Method to handle form events.
     * This method will be called when an event related to the form occurs.
     * The method accepts a general EventObject, which can contain any event information.
     * 
     * @param e The event object containing details of the event that occurred.
     */
    void formEventOccured(EventObject e); // Accepts any EventObject
}

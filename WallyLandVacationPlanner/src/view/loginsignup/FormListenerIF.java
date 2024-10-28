package view.loginsignup;

import java.util.EventListener;
import model.loginsignup.User;

/**
 * FormListenerIF interface extends EventListener 
 and listens for events to transmit data from view to model
 * @author Ana
 */
public interface FormListenerIF extends EventListener{
    public void formEventOccured(User e);
}

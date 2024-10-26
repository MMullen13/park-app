package view.loginsignup;

import model.loginsignup.ProfileUser;
import java.util.EventListener;

/**
 * FormListenerIF interface extends EventListener 
 and listens for events to transmit data from view to model
 * @author Ana
 */
public interface FormListenerIF extends EventListener{
    public void formEventOccured(ProfileUser e);
}

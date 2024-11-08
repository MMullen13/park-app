package view.loginsignup;

import java.util.EventListener;
import model.loginsignup.RegisterFormEvent;

/**
 * SigninFormListenerIF interface extends EventListener and listens for events
 * to transmit data from view to model
 *
 * @author Ana
 */
public interface RegisterFormListenerIF extends EventListener {

    public void formEventOccured(RegisterFormEvent e);
}

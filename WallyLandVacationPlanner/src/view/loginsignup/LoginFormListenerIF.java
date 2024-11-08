package view.loginsignup;

import java.util.EventListener;
import model.loginsignup.LoginFormEvent;

/**
 * LoginFormListenerIF interface extends EventListener and listens for events to
 * transmit data from view to model
 *
 * @author Ana
 */
public interface LoginFormListenerIF extends EventListener {

    public void formEventOccured(LoginFormEvent e);
}

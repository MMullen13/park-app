package view.loginsignup;

import java.util.EventListener;
import java.util.EventObject;

/**
 *
 * @author Ana
 */
public interface FormListenerIF extends EventListener {
    void formEventOccured(EventObject e); // Accepts any EventObject
}


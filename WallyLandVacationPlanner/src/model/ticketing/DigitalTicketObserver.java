package model.ticketing;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class DigitalTicketObserver implements ObserverIF {
    private JFrame digitalTicketFrame;

    public DigitalTicketObserver(JFrame digitalTicketFrame) {
        this.digitalTicketFrame = digitalTicketFrame;
    }

    @Override
    public void update(String message) {
        JOptionPane.showMessageDialog(digitalTicketFrame, message, "Ticket Update", JOptionPane.INFORMATION_MESSAGE);
    }
}

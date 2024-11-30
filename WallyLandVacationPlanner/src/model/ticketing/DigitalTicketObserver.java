package model.ticketing;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class DigitalTicketObserver implements Observer{
    
    private JFrame digitalTicketFrame;
    
    public DigitalTicketObserver(JFrame digitalTicketObserver){
        this.digitalTicketFrame = digitalTicketFrame;
    }

    @Override
    public void update(String message) {
       JOptionPane.showMessageDialog(digitalTicketFrame, message, "Ticket Update", JOptionPane.INFORMATION_MESSAGE);
    }
    
}

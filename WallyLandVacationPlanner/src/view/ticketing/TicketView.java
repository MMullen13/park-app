package view.ticketing;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.MainPagePanel;
import view.MainPageView;

/**
 *
 * @author Ana
 */
public class TicketView extends JFrame{
    private TicketPanel ticketPanel;
    private ImageIcon wallylandIcon;

    public TicketView() {
        super("WallyLand");
        ticketPanel = new TicketPanel();
        
        wallylandIcon = createIcon("/images/theme-park.png", 200, 200);
        if (wallylandIcon != null) {
            setIconImage(wallylandIcon.getImage()); // This should work if class extends JFrame
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new MainPagePanel());

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(TicketView.this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.OK_CANCEL_OPTION);
                if (confirm == JOptionPane.OK_OPTION) {
                    dispose();
                }
            }
        });

        setSize(600, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);     
    }
    
    private ImageIcon createIcon(String path, int w, int l) {
        URL url = getClass().getResource(path);

        if (url == null) {
            System.err.println("Unable to load image icon: " + path);
            return null;
        }

        ImageIcon icon = new ImageIcon(url);
        Image scaledImage = icon.getImage().getScaledInstance(w, l, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    
}

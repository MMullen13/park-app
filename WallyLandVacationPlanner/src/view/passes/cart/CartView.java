package view.passes.cart;


import controller.ticketsandpasses.PassesController;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import view.ImageUtils;

/**
 *
 * @author Ana
 */
public class CartView extends JFrame {

    private ImageIcon wallylandIcon;
    private CartPanel cartPanel;
    private PassesController controller;

    public CartView() {
        super("Wallyland");
        
        this.controller = new PassesController();

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        
        cartPanel = new CartPanel(controller);       
        
        add(cartPanel);
        // Set icon
        wallylandIcon = ImageUtils.createIcon("/images/theme-park.png", 200, 200);
        if (wallylandIcon != null) {
            setIconImage(wallylandIcon.getImage());
        }

        setVisible(true);
    }
    
    public void closeWindow() {
       this.setVisible(false);
    }

    public void refresh() {
//        cartPanel.removeAll();
        cartPanel.refreshTicketCart();
        cartPanel.refreshPassCart();
        cartPanel.updateTotalLabel();
        cartPanel.updateEmptyCartLabel();
        cartPanel.updateButtonStates();
        cartPanel.revalidate();
        cartPanel.repaint();
    }
}

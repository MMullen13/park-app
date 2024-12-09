package view.passes.tiketsandpasses;

import controller.ticketsandpasses.PassesController;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import view.ImageUtils;

/**
 *
 * @author Ana
 */
public class PassesView extends JFrame {

    private ImageIcon wallylandIcon;
    private PassesPanel passPanel;
    private PassesController controller;

    public PassesView() {
        super("Wallyland");
        
        this.controller = new PassesController();
        controller.setPassView(this);

  // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setResizable(false);
//        getContentPane().setBackground(new Color(240, 248, 255));

        // Set icon
        wallylandIcon = ImageUtils.createIcon("/images/theme-park.png", 200, 200);
        if (wallylandIcon != null) {
            setIconImage(wallylandIcon.getImage());
        }

        // Add ticket panel
        this.passPanel = new PassesPanel(controller);
        add(passPanel);

        setVisible(true);
    }

    public void closeWindow() {
       this.setVisible(false);
    }
}

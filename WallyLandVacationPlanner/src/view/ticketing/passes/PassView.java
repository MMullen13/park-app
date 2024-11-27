package view.ticketing.passes;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Ana
 */
public class PassView extends JFrame {

    private ImageIcon wallylandIcon;

    public PassView() {
        super("Wallyland");

  // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setResizable(false);
//        getContentPane().setBackground(new Color(240, 248, 255));

        // Set icon
        ImageIcon wallylandIcon = createIcon("/images/theme-park.png", 200, 200);
        if (wallylandIcon != null) {
            setIconImage(wallylandIcon.getImage());
        }

        // Add ticket panel
        PassPanel passPanel = new PassPanel();
        add(passPanel);

        setVisible(true);
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

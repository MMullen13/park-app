package view;

import java.awt.Image;
import java.net.URL;
import javax.swing.*;

/**
 * Main Page View containing the main page panel.
 * @author Ana
 */
public class ComingSoonView extends JFrame {

    private ImageIcon wallylandIcon;
    
    
    public ComingSoonView() {      
        super("WallyLand");
        wallylandIcon = createIcon("/images/theme-park.png", 200, 200);
        if (wallylandIcon != null) {
            setIconImage(wallylandIcon.getImage()); // This should work if class extends JFrame
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        ComingSoonPanel csPanel = new ComingSoonPanel();
        add(csPanel);

        setSize(400, 600);
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

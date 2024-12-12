package view;

import javax.swing.*;

/**
 * Main Page View containing the main page panel.
 * @author Ana
 */
public class ComingSoonView extends JFrame {

    private ImageIcon wallylandIcon;
    
    
    public ComingSoonView() {      
        super("WallyLand");
        wallylandIcon = ImageUtils.createIcon("/images/theme-park.png", 200, 200);
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
}

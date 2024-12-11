package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

/**
 * Main Page View containing the main page panel.
 *
 * @author Ana
 */
public class MainPageView extends JFrame {

    private ImageIcon wallylandIcon;

    public MainPageView() {
        super("WallyLand");
        wallylandIcon = ImageUtils.createIcon("/images/theme-park.png", 200, 200);
        if (wallylandIcon != null) {
            setIconImage(wallylandIcon.getImage()); // This should work if class extends JFrame
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new MainPagePanel());

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Create a custom panel with graphics
                JPanel customPanel = new JPanel(new BorderLayout());
                customPanel.setBackground(new Color(233, 233, 234));

                // Title
                JLabel titleLabel = new JLabel("Are you sure you want to exit?");
                titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
                titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                titleLabel.setForeground(new Color(40, 95, 150));

                // Icon or Graphic (Example: a warning icon)
                JLabel iconLabel = new JLabel(UIManager.getIcon("OptionPane.warningIcon"));
                iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
                iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                // Add components to the panel
                customPanel.add(iconLabel, BorderLayout.NORTH);
                customPanel.add(titleLabel, BorderLayout.CENTER);

                // Show the dialog with the custom panel
                int action = JOptionPane.showConfirmDialog(
                        null,
                        customPanel,
                        "Exit Confirmation",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                // Handle user selection
                if (action == JOptionPane.OK_OPTION) {
                    dispose();
                }
            }
        });

        setSize(850, 950);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    
    public void closewindow(){
        this.dispose();
    }
}
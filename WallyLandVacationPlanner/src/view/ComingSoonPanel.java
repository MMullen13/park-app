package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Coming Soon Panel
 *
 * @author Ana
 */
public class ComingSoonPanel extends JPanel {

    private JPanel backgroundPanel;
    private JPanel footerPanel;
    private JPanel headerPanel;
    private Footer footer;
    private Header header;

    /**
     * Constructor
     */
    public ComingSoonPanel() {
        setLayout(new BorderLayout());

        footer = new Footer();
        header = new Header();

        headerPanel = header.createHeaderPanel(" ", null);
        add(headerPanel, BorderLayout.NORTH);
        backgroundPanel = new JPanel();

        JLabel contactLabel = new JLabel("Coming Soon");

        contactLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contactLabel.setForeground(Color.BLACK);
        contactLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        backgroundPanel.add(contactLabel);
        add(backgroundPanel, BorderLayout.CENTER);

        String msgOne = "Contact Us: 123-456-7890 | Email: info@wallyland.com";
        String msgTwo = "Address: 123 WallyLand Ave, Fun City, USA";
        footerPanel = footer.createFooterPanel(msgOne, msgTwo);
        add(footerPanel, BorderLayout.SOUTH);

        Border innerBorder = BorderFactory.createTitledBorder("");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }

}

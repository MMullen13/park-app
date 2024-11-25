package view.ticketing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Ana
 */
public class TicketView extends JFrame{
    
    private ImageIcon wallylandIcon;

    public TicketView() {
        super("Tickets");
        
        wallylandIcon = createIcon("/images/theme-park.png", 200, 200);
        if (wallylandIcon != null) {
            setIconImage(wallylandIcon.getImage()); // This should work if class extends JFrame
        }
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the main panel for the tickets
        JPanel ticketsPanel = new JPanel();
        ticketsPanel.setLayout(new GridLayout(3, 1, 20, 20)); // 3 rows, 1 column with spacing
        ticketsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

        // Add ticket cards to the panel
        ticketsPanel.add(createTicketCard("Day Pass", "$50", "Access to all rides and attractions for one day."));
        ticketsPanel.add(createTicketCard("Season Pass", "$200", "Unlimited visits for the entire season."));
        ticketsPanel.add(createTicketCard("VIP Pass", "$350", "Priority access, exclusive lounges, and unlimited visits."));

        // Wrap the tickets panel in a scroll pane (for better UX if more tickets are added)
        JScrollPane scrollPane = new JScrollPane(ticketsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        add(scrollPane, BorderLayout.CENTER);

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
    
    
    private JPanel createTicketCard(String header, String price, String description) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(58, 115, 169), 2, true)); // Rounded border
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setPreferredSize(new Dimension(500, 150));
        
        // Header (Name + Price)
        JLabel headerLabel = new JLabel(header + " - " + price, JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(58, 115, 169));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setPreferredSize(new Dimension(500, 40));
        cardPanel.add(headerLabel, BorderLayout.NORTH);

        // Description
        JLabel descriptionLabel = new JLabel("<html><div style='text-align: center;'>" + description + "</div></html>", JLabel.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        cardPanel.add(descriptionLabel, BorderLayout.CENTER);

        // Button
        JButton chooseButton = new JButton("Choose This Ticket");
        chooseButton.setFont(new Font("Arial", Font.BOLD, 14));
        chooseButton.setBackground(new Color(58, 115, 169));
        chooseButton.setForeground(Color.WHITE);
        chooseButton.setFocusPainted(false);
        chooseButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chooseButton.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(cardPanel, "You have chosen the " + header + ".");
        });
        cardPanel.add(chooseButton, BorderLayout.SOUTH);

        return cardPanel;
    }
}

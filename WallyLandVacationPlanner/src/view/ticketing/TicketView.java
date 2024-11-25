package view.ticketing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Ana
 */
public class TicketView extends JFrame {

    private ImageIcon wallylandIcon;

    public TicketView() {
        super("Wallyland");

        wallylandIcon = createIcon("/images/theme-park.png", 200, 200);
        if (wallylandIcon != null) {
            setIconImage(wallylandIcon.getImage()); // This should work if class extends JFrame
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        getContentPane().setBackground(new Color(240, 248, 255));

        // Create the main panel for the tickets
        JPanel ticketsPanel = new JPanel();
        ticketsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30)); // Horizontal layout with spacing
        ticketsPanel.setOpaque(false);
        ticketsPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40)); // Padding

        // Add ticket cards to the panel
        ticketsPanel.add(createTicketCard("Child Day Pass", "$20", "Access to all rides and attractions for one day."));
        ticketsPanel.add(createTicketCard("Adult Day Pass", "$35", "Access to all rides and attractions for one day."));
        ticketsPanel.add(createTicketCard("Senior Day Pass", "$30", "Access to all rides and attractions for one day."));

        // Wrap the tickets panel in a scroll pane (for better UX if more tickets are added)
        JScrollPane scrollPane = new JScrollPane(ticketsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        
        add(scrollPane, BorderLayout.CENTER);

        setSize(800, 600);
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

    /**
     * Creates a ticket card panel.
     */
    private JPanel createTicketCard(String header, String price, String description) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(90, 145, 204), 2, true)); // Rounded border
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setPreferredSize(new Dimension(200, 400)); // Adjusted size for horizontal alignment

        // Header (Name + Price)
        JLabel headerLabel = new JLabel(header + " - " + price, JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(90, 145, 204));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setPreferredSize(new Dimension(220, 40));
        cardPanel.add(headerLabel, BorderLayout.NORTH);

        // Description
        JLabel descriptionLabel = new JLabel("<html><div style='text-align: center;'>" + description + "</div></html>", JLabel.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionLabel.setForeground(new Color(40, 95, 150));
        cardPanel.add(descriptionLabel, BorderLayout.CENTER);

        // Button
        JButton chooseButton = new JButton("Buy Ticket");
        chooseButton.setFont(new Font("Arial", Font.BOLD, 14));
        chooseButton.setBackground(new Color(58, 115, 169));
        chooseButton.setForeground(Color.WHITE);
        chooseButton.setFocusPainted(false);
        
        chooseButton.setPreferredSize(new Dimension(130, 40)); // Smaller button
        chooseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                chooseButton.setBackground(new Color(40, 95, 150)); // Darker blue on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                chooseButton.setBackground(new Color(58, 115, 169)); // Original blue
            }
            
            @Override
            public void mousePressed(MouseEvent e){
              chooseButton.setForeground(new Color(40, 95, 150));  
            }
            
            @Override
            public void mouseReleased(MouseEvent e){
              chooseButton.setForeground(Color.WHITE);  
            }
        });

        // Add the button to a container to center it horizontally
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(chooseButton);

        cardPanel.add(buttonPanel, BorderLayout.SOUTH);

        return cardPanel;
    }
}

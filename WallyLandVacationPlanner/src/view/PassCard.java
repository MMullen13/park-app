package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

/**
 *
 * @author Ana
 */
public class PassCard extends JPanel {
    private String header;
    private String price;
    private String description;
    private JSpinner quantitySpinner;
    private JLabel quantityLabel;

    public PassCard(String header, String price, String description, Map<String, Integer> cartItems, JLabel passLabel) {
        this.header = header;
        this.price = price;
        this.description = description;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Stack components vertically
        setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 6, true)); // Rounded border
        setBackground(new Color(170, 187, 192)); // Gray background
        setPreferredSize(new Dimension(200, 300)); // Uniform size for all cards

        // Add hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(255, 255, 255)); // Change background on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(170, 187, 192)); // Revert to original background
            }
        });

        // Header
        JLabel headerLabel = new JLabel(header + " - " + price, JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(58, 115, 169));
        add(headerLabel);

        // Description
        JLabel descriptionLabel = new JLabel("<html><div style='text-align: left; '>" + description + "</div></html>", JLabel.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(descriptionLabel);

        // Quantity Selector
        JPanel quantityPanel = new JPanel();
        quantityPanel.setBackground(new Color(152, 175, 197));
        quantityPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        JLabel quantityTitleLabel = new JLabel("Qty:");
        quantityTitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        quantityTitleLabel.setForeground(Color.WHITE);
        quantityPanel.add(quantityTitleLabel);

        quantitySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        quantitySpinner.setFont(new Font("Arial", Font.PLAIN, 14));
        quantitySpinner.setPreferredSize(new Dimension(60, 30));
        quantitySpinner.addChangeListener(e -> {
            int quantity = (int) quantitySpinner.getValue();
            cartItems.put(header, quantity);
            passLabel.setText(header + " Passes: " + quantity);
        });

        quantityPanel.add(quantitySpinner);
        add(quantityPanel);
    }
}


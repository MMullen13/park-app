package view;

import javax.swing.*;
import java.awt.*;

/**
 * The Card class represents a custom JPanel component that displays a card-like
 * UI element. The card includes a header with a name and price, a description,
 * and a footer. The card components are styled with gradients, rounded corners,
 * and text centering. This class extends JPanel and uses BoxLayout to stack
 * components vertically.
 *
 * @author Ana
 */
public class Card extends JPanel {

    /**
     * Constructs a new Card object with the specified header, price,
     * description, width, and height. It sets up the card layout, styles the
     * header with a gradient background, displays the description in a label,
     * and includes a footer with a gradient background.
     *
     * @param header The header text (name) to display at the top of the card.
     * @param price The price to display next to the header.
     * @param description The description text to display below the header.
     * @param width The preferred width of the card.
     * @param height The preferred height of the card.
     */
    public Card(String header, String price, String description, int width, int height) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Stack components vertically
        setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 6, true)); // Rounded border
        setBackground(new Color(58, 115, 169));
        setPreferredSize(new Dimension(width, height));

        // Header (Name + Price)
        JLabel headerLabel = new JLabel(header + price, JLabel.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Gradient for header
                GradientPaint gradient = new GradientPaint(0, 0, new Color(58, 115, 169), getWidth(), 0, new Color(17, 138, 200));
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5); // Rounded corners

                // Draw the text
                g2d.setColor(Color.WHITE); // Text color
                g2d.setFont(getFont()); // Use the label's font
                FontMetrics fm = g2d.getFontMetrics();
                String text = getText();
                int x = (getWidth() - fm.stringWidth(text)) / 2; // Center horizontally
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2; // Center vertically
                g2d.drawString(text, x, y);
            }
        };
        headerLabel.setOpaque(false); // Let the gradient show
        headerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        headerLabel.setPreferredSize(new Dimension(40, 40));
        add(headerLabel);

        // Description with fixed height
        JLabel descriptionLabel = new JLabel("<html><div style='text-align: left; '>" + description + "</div></html>", JLabel.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        descriptionLabel.setForeground(new Color(72, 95, 117));
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionLabel.setAlignmentY(Component.TOP_ALIGNMENT);

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setBackground(Color.WHITE);
//        descriptionPanel.setPreferredSize(new Dimension(10, 40));
        descriptionPanel.setLayout(new BorderLayout());
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        descriptionPanel.add(descriptionLabel, BorderLayout.CENTER);
        add(descriptionPanel);

        // footer Panel to add space
        JPanel footerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Gradient for header
                GradientPaint gradient = new GradientPaint(0, 0, new Color(17, 138, 200), getWidth(), 0, new Color(58, 115, 169));
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5); // Rounded corners
            }
        };
        footerPanel.setBackground(new Color(70, 130, 180));
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        footerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        footerPanel.setPreferredSize(new Dimension(16, 16));

        add(footerPanel);
    }

}

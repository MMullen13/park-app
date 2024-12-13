package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A utility class for creating a header panel with two labels displaying custom
 * messages. The header has a gradient background and is designed to be used as
 * a part of a larger UI.
 *
 * @author Ana
 */
public class Header {

    /**
     * Creates a header panel with two labels displaying the specified messages.
     * The panel has a gradient background and the labels are centered within
     * it.
     *
     * @param msgOne The first message to display in the header.
     * @param msgTwo The second message to display in the header.
     * @return A JPanel containing the header with the two messages.
     */
    public JPanel createHeaderPanel(String msgOne, String msgTwo) {
        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Create a gradient background for the footer
                GradientPaint gradient = new GradientPaint(0, 0, new Color(17, 138, 200), getWidth(), 0, new Color(17, 138, 200));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setPreferredSize(new Dimension(600, 30)); // Adjust height for the footer

        JLabel firstLabel = new JLabel(msgOne);
        JLabel secondLabel = new JLabel(msgTwo);

        firstLabel.setFont(new Font("Arial", Font.BOLD, 18));
        firstLabel.setForeground(Color.WHITE);
        firstLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(Box.createRigidArea(new Dimension(0, 5))); // Add spacing at the top
        header.add(firstLabel);
        header.add(Box.createRigidArea(new Dimension(0, 5)));
        header.add(secondLabel);
        header.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing at the bottom

        return header;
    }

}

package view.loginsignup;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPasswordField;

/**
 *
 * @author Ana
 */
public class RoundedPasswordField extends JPasswordField {
        private static final int ARC_WIDTH = 15;
        private static final int ARC_HEIGHT = 15;

        public RoundedPasswordField(int columns) {
            super(columns);
            setOpaque(false); // Make the password field non-opaque
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Fill the background with the password field background color
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);

            super.paintComponent(g2); // Paint the password field contents
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw the rounded border
            g2.setColor(new Color(50, 50, 50)); // Border color
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);

            g2.dispose();
        }
    }

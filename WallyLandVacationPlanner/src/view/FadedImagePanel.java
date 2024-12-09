package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Ana
 */
class FadedImagePanel extends JPanel {

    private ImageIcon imageIcon;

    public FadedImagePanel(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing for smoother rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the image on the panel
        if (imageIcon != null) {
            g2d.drawImage(imageIcon.getImage(), 0, 0, this);
        }

        // Apply a faded effect (semi-transparent overlay)
        GradientPaint fadeGradient = new GradientPaint(
                0, 0, new Color(220, 220, 220, 150), // Light color at the top (faint white)
                0, getHeight(), new Color(240, 240, 240, 200) // Slightly darker gray at the bottom
        );
        g2d.setPaint(fadeGradient);
        g2d.fillRect(0, 0, getWidth(), getHeight()); // Overlay the gradient on top of the image

        drawText(g2d);
    }

    private void drawText(Graphics2D g2d) {
        // First line (larger text)
        String largeText = "Welcome to WallyLand";
        // Second line (smaller text)
        String smallText = "where all your adventures begin!";

        // Set the color for both texts
        g2d.setColor(new Color(40, 95, 150));

        // Set the font for the larger text
        g2d.setFont(new Font("Arial", Font.BOLD, 44)); // Larger font size for the first line
        FontMetrics fmLarge = g2d.getFontMetrics();
        int xLarge = (getWidth() - fmLarge.stringWidth(largeText)) / 2; // Center the large text
        int yLarge = getHeight() / 6; // Position it higher on the screen

        // Draw the large text
        g2d.drawString(largeText, xLarge, yLarge);

        // Set the font for the smaller text
        g2d.setFont(new Font("Arial", Font.PLAIN, 25)); // Smaller font size for the second line
        FontMetrics fmSmall = g2d.getFontMetrics();
        int xSmall = (getWidth() - fmSmall.stringWidth(smallText)) / 2; // Center the smaller text
        int ySmall = yLarge + fmLarge.getHeight(); // Position it below the first line

        // Draw the smaller text
        g2d.drawString(smallText, xSmall, ySmall);
    }

}

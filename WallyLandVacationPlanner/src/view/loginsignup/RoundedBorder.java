package view.loginsignup;

import javax.swing.border.AbstractBorder;
import java.awt.*;

/**
 *
 * @author Ana
 */
public class RoundedBorder extends AbstractBorder {

    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.GRAY);
        g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}

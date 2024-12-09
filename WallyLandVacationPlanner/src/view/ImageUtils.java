package view;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Ana
 */
public class ImageUtils {
    public ImageUtils() {
        // Prevent instantiation
    }

    public static ImageIcon createIcon(String path, int width, int height) {
        URL url = ImageUtils.class.getResource(path); // Static context uses the utility class itself

        if (url == null) {
            System.err.println("Unable to load image icon: " + path);
            return null; // Return null to handle missing resources gracefully
        }

        ImageIcon icon = new ImageIcon(url);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}


package view;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * A utility class for handling image-related operations, such as creating and
 * scaling image icons. This class provides static methods for loading and
 * scaling images to the specified dimensions.
 *
 * @author Ana
 */
public class ImageUtils {

    /**
     * Private constructor to prevent instantiation of the utility class. This
     * class should be used statically, so instances cannot be created.
     */
    public ImageUtils() {
        // Prevent instantiation
    }

    /**
     * Creates an ImageIcon from the specified image path and scales it to the
     * given width and height. If the image cannot be found at the specified
     * path, an error message is printed and null is returned.
     *
     * @param path The path to the image file.
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @return An ImageIcon object containing the scaled image, or null if the
     * image could not be loaded.
     */
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

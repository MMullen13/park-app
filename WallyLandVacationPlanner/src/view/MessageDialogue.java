package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * The MessageDialogue class provides utility methods to display custom
 * confirmation dialogs with various messages and options. It includes methods
 * for displaying simple confirmation messages as well as dialog boxes with
 * customizable messages and "OK" / "Cancel" options.
 *
 * @author Ana
 */
public class MessageDialogue {

    /**
     * Displays a confirmation message indicating that items have been added to
     * the cart. The user is informed that they can view the cart by navigating
     * to the menu bar on the home page.
     *
     * @param frame The JFrame instance that will be closed upon user
     * confirmation.
     */
    public static void displayConfirmationtMsg(JFrame frame) {
        // Create a custom panel with graphics
        JPanel customPanel = new JPanel(new BorderLayout());
        customPanel.setBackground(new Color(233, 233, 234));

        // Title
        JLabel titleLabel = new JLabel("<html><div style='text-align:center;'><h2>Items added to the cart!</h2>"
                + "<br>View cart by navigating to the menu bar on the home page.</div></html>");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titleLabel.setForeground(new Color(40, 95, 150));

        // Icon or Graphic (Example: a warning icon)
        JLabel iconLabel = new JLabel(UIManager.getIcon("OptionPane.informationIcon"));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add components to the panel
        customPanel.add(iconLabel, BorderLayout.NORTH);
        customPanel.add(titleLabel, BorderLayout.CENTER);

        // Show the dialog with the custom panel
        int action = JOptionPane.showConfirmDialog(
                null,
                customPanel,
                "Cart Confirmation",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.PLAIN_MESSAGE
        );

        // Handle user selection
        if (action == JOptionPane.OK_OPTION) {
            JFrame topFrame = frame;
            topFrame.dispose();
        }
    }

    /**
     * Displays a customizable confirmation dialog with an "OK" and "Cancel"
     * option. The dialog shows a title and a message, and upon clicking "OK",
     * the provided frame is disposed of.
     *
     * @param frame The JFrame instance that will be disposed upon user
     * confirmation.
     * @param title The title to be displayed in the dialog.
     * @param msg The message to be displayed in the dialog.
     */
    public static void okCancelConfirmationDialogue(JFrame frame, String title, String msg) {
        // Create a custom panel with graphics
        JPanel customPanel = new JPanel(new BorderLayout());
        customPanel.setBackground(new Color(233, 233, 234));

        // Title
        JLabel titleLabel = new JLabel(msg);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titleLabel.setForeground(new Color(40, 95, 150));

        // Icon or Graphic (Example: a warning icon)
        JLabel iconLabel = new JLabel(UIManager.getIcon("OptionPane.warningIcon"));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add components to the panel
        customPanel.add(iconLabel, BorderLayout.NORTH);
        customPanel.add(titleLabel, BorderLayout.CENTER);

        // Show the dialog with the custom panel
        int action = JOptionPane.showConfirmDialog(
                null,
                customPanel,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        // Handle user selection
        if (action == JOptionPane.OK_OPTION) {
            frame.dispose();
        }
    }

}

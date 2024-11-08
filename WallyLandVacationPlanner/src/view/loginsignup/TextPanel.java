package view.loginsignup;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A JPanel that contains a JTextArea for displaying and appending text. This
 * panel provides a scrollable area for text, which is useful for displaying
 * long or dynamic content.
 *
 * Author: Ana
 */
public class TextPanel extends JPanel {

    private JTextArea textArea; // The text area where text will be displayed and appended

    /**
     * Constructor that initializes the TextPanel with a JTextArea. The text
     * area is wrapped in a JScrollPane to allow scrolling if the text exceeds
     * the visible area.
     */
    public TextPanel() {
        textArea = new JTextArea(); // Initialize the text area

        setLayout(new BorderLayout()); // Set the layout of the panel to BorderLayout
        add(new JScrollPane(textArea), BorderLayout.CENTER); // Add the text area to the center of the panel inside a scroll pane
    }

    /**
     * Appends text to the JTextArea. This method is used to add new text to the
     * existing content in the text area.
     *
     * @param text The text to be appended to the text area.
     */
    public void appendText(String text) {
        textArea.append(text); // Append the provided text to the text area
    }
}

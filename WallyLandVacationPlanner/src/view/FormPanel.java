package view;

import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Ana
 */
public class FormPanel extends JPanel{
    
    public FormPanel(){
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
    }
}

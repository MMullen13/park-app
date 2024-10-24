package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Ana
 */
public class Toolbar extends JPanel implements ActionListener{
    
    private JButton hi;
    private JButton by;
    private StringListener textListener;
    
   public Toolbar(){
       setBorder(BorderFactory.createTitledBorder("Toolbar")); 
       hi = new JButton("Hello");
       by = new JButton("GoodBuy");
       setLayout(new FlowLayout(FlowLayout.LEFT));
//       setLayout(new FlowLayout(FlowLayout.CENTER));
       add(hi);
       add(by);
       
       hi.addActionListener(this);
       by.addActionListener(this);
   } 

    void setStringListener(StringListener listener){
        this.textListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      JButton clickedBtn = (JButton)e.getSource();
      
      if(clickedBtn == hi){
        if(textListener != null){
            textListener.textEmmited("Hello\n");
        }
      }
      
      if(clickedBtn == by){
        if(textListener != null){
            textListener.textEmmited("Goodbuy\n");
        }
      }
    }
}

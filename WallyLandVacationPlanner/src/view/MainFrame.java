package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Ana
 */
public class MainFrame extends JFrame {
    
    private TextPanel textPanel;
    private JButton btn; //login button
    private Toolbar toolbar; //top position toolbar
    private FormPanel formPanel;

    public MainFrame() {
        super("WallyLand Park Application");
        
        setLayout(new BorderLayout());
        toolbar = new Toolbar();
        textPanel = new TextPanel();
        btn = new JButton("Login");
        formPanel = new FormPanel();
        
        toolbar.setStringListener(new StringListener(){
            @Override
            public void textEmmited(String text) {
               textPanel.appendText(text);
            }
            
        });
        
        add(toolbar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);
        add(formPanel, BorderLayout.WEST);
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textPanel.appendText("Log In\n");
            }
            
        });
        
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}

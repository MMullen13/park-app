/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wallylandvacationplanner;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author aniut
 */
public class MainFrame extends JFrame {
    
    private TextPanel textPanel;
    private JButton btn;

    public MainFrame() {
        super("WallyLand Park Application");
        
        setLayout(new BorderLayout());
        textPanel = new TextPanel();
        btn = new JButton();
        
        add(textPanel, BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textPanel.appendText("Hello\n");
            }
            
        });
        
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}

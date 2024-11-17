/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.foodordering;

import controller.foodordering.FoodController;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import model.foodordering.MenuItem;

/**
 *
 * @author theme
 */
public class OrderConfirmationViewForm extends javax.swing.JFrame implements ActionListener {
    
    private FoodController cntl;

    /**
     * Creates new form OrderConfirmationViewForm
     * @param cntl
     */
    public OrderConfirmationViewForm(FoodController cntl) {
        initComponents();
        this.cntl = cntl;
        this.cntl.setConfirmationView(this);
        populateConfirmationForm();
        setActionListeners();
        this.setVisible(false);
        
    }
    
    private void populateConfirmationForm(){
        DefaultTableModel tableModel = (DefaultTableModel) orderSummaryTable.getModel();
        ImageIcon barcodeIcon = new ImageIcon(getClass().getResource("/FoodImages/barcode-306926_1280.png"));
        orderNumber.setText(cntl.getOrderNumber());
        pickupTime.setText(cntl.getPickupTime());
        totalCost.setText("$" + String.format("%.2f", cntl.getTotal()));
        
        for (MenuItem item : cntl.getOrderItems()){
            tableModel.addRow(new Object[] {item.getItemName(), item.getPrice(), item.getQuantity()});
        }
        
        Image image = barcodeIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 100, Image.SCALE_SMOOTH); 
        ImageIcon scaledBarcodeIcon = new ImageIcon(scaledImage); 
        barCode.setIcon(scaledBarcodeIcon);
        barCode.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
    
    private void setActionListeners(){
        exitButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton){
            OrderHistoryView form = new OrderHistoryView();
            form.setVisible(true);
            //return to landing page
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        orderConfirmation = new javax.swing.JLabel();
        orderNumber = new javax.swing.JLabel();
        orderNumberLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderSummaryTable = new javax.swing.JTable();
        totalLabel = new javax.swing.JLabel();
        totalCost = new javax.swing.JLabel();
        pickupTime = new javax.swing.JLabel();
        pickupTimeLabel = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        barCode = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));

        orderConfirmation.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        orderConfirmation.setText("Order Confirmation");

        orderNumber.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        orderNumber.setText("123456");

        orderNumberLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        orderNumberLabel.setText("Order Number:");

        orderSummaryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Price", "Quantity"
            }
        ));
        jScrollPane1.setViewportView(orderSummaryTable);

        totalLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        totalLabel.setText("Total:");

        totalCost.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        totalCost.setText("123456");

        pickupTime.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        pickupTime.setText("10:00 AM");

        pickupTimeLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        pickupTimeLabel.setText("Pickup Time:");

        exitButton.setText("Close");

        jLabel1.setText("Show this Bar Code to Cashier at Pick Up");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(orderConfirmation)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(orderNumberLabel)
                        .addGap(18, 18, 18)
                        .addComponent(orderNumber)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(107, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pickupTimeLabel)
                            .addComponent(totalLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalCost)
                            .addComponent(pickupTime))
                        .addGap(51, 51, 51)
                        .addComponent(exitButton)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(barCode, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(102, 102, 102))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(orderConfirmation)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderNumberLabel)
                    .addComponent(orderNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(barCode, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(exitButton)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalCost)
                            .addComponent(totalLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pickupTimeLabel)
                            .addComponent(pickupTime))
                        .addGap(12, 12, 12))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(OrderConfirmationViewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(OrderConfirmationViewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(OrderConfirmationViewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(OrderConfirmationViewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                FoodController cntl = new FoodController();
//                new OrderConfirmationViewForm(cntl).setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel barCode;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel orderConfirmation;
    private javax.swing.JLabel orderNumber;
    private javax.swing.JLabel orderNumberLabel;
    private javax.swing.JTable orderSummaryTable;
    private javax.swing.JLabel pickupTime;
    private javax.swing.JLabel pickupTimeLabel;
    private javax.swing.JLabel totalCost;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration//GEN-END:variables

    
}

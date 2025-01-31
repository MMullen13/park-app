/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.foodordering;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.foodordering.*;
import java.text.SimpleDateFormat;
import model.foodordering.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;


/**
 * This is the order view form where users can create a new order. 
 * @author theme
 */
public class OrderViewForm extends javax.swing.JFrame implements ActionListener {
    
    private FoodController cntl;
    //All form attributes can be found below
    

    /**
     * Constructor for the order view form class. Initializes the components,
     * sets up the food controller, loads the order history, and sets up 
     * the UI for a user to place an order.
     * @param cntl Food Controller instance to manage this view
     */
    public OrderViewForm(FoodController cntl) {
        initComponents();
        this.cntl = cntl;
        this.cntl.setOrderView(this);
        cntl.loadOrderHistory();
        initManualComponents();
        setListeners();
        setLocationRelativeTo(null);
        this.setVisible(false);
        
    }
    
    /**
     * Helper method to set the action listeners. 
     */
    private void setListeners(){
        eaterySelector.addActionListener(this);
        foodType.addActionListener(this);
        addToOrder.addActionListener(this);
        checkoutButton.addActionListener(this);
        undoButton.addActionListener(this);
        removeLastItem.addActionListener(this);
        clearAllItems.addActionListener(this);
    }
    
    /**
     * Helper method to populate the UI components. Sets up the spinner for pickup
     * time and date. Adds the eateries. Populates the food type drop down. Sets 
     * default quantity number of items. Creates a new order.
     */
    private void initManualComponents(){
        optionPane.setVisible(false);
        
        pickupTimeSpinner.setModel(new SpinnerDateModel(new Date(),null, null, Calendar.MINUTE));
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(pickupTimeSpinner, "MM/dd/yy hh:mm a");
        pickupTimeSpinner.setEditor(timeEditor);
        pickupTimeSpinner.setValue(new Date());
        
        eaterySelector.addItem(new Eatery("Bistro Bella"));
        eaterySelector.addItem(new Eatery("Grill & Chill"));
        eaterySelector.addItem(new Eatery("Chicken Kitchen"));
       
        foodType.removeAllItems();
        foodType.addItem("Drinks");
        foodType.addItem("Appetizers");
        foodType.addItem("Mains");
        foodType.addItem("Sides");
        foodType.addItem("Desserts");
        
        populateItems();
        
        quantity.setText("1");
        
        createNewOrder();
        
        orderTable.getTableHeader().setResizingAllowed(false);
        orderTable.getTableHeader().setReorderingAllowed(false);
        priceInfoField.setEditable(false);

    }
    
    /**
     * Creates a new order, sets the order number that is randomly generated in
     * model class.
     */
    public void createNewOrder(){
        cntl.newOrder((Eatery) eaterySelector.getSelectedItem());
        orderNumber.setText(cntl.getOrderNumber());
    }
    
    /**
     * Populates the option drop down with items and prices based on selected eatery and item type
     */
    public void populateItems(){
        Eatery selectedEatery = (Eatery) eaterySelector.getSelectedItem();
        String selectedType = (String) foodType.getSelectedItem();
        
        options.removeAllItems(); //clears the options selection when eatery or food type changes
        
        Map<String, ArrayList<MenuItem>> itemByCategory = cntl.getMenuItems(selectedEatery.getEateryName());
        
        if (itemByCategory.containsKey(selectedType)){
            for (MenuItem item : itemByCategory.get(selectedType)){
                options.addItem(item.getItemName() + "  $" + String.format("%.2f", item.getPrice()));
            }
        
        }
        
    }
    
    
    /**
     * Handles all of the action events for the UI components
     * @param e The event that triggers this method
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == eaterySelector || e.getSource() == foodType){
            populateItems(); //sets the available items
            Eatery updatedEatery = (Eatery) eaterySelector.getSelectedItem();
            cntl.updateEatery(updatedEatery);
            
        }
        
        
        if (e.getSource() == addToOrder){
            DefaultTableModel tableModel = (DefaultTableModel) orderTable.getModel();
            String selectedItem = (String) options.getSelectedItem();
            
            if(selectedItem != null){
                String[] parts = selectedItem.split("  \\$"); //Splits the menu item into a string item name and price for the table
                String itemName = parts[0];
                double price = Double.parseDouble(parts[1]);
                
                try{
                int numberOfItems = Integer.parseInt(quantity.getText());
                
                    if (numberOfItems <= 0) { //must have at least 1 type of item.
                        JOptionPane.showMessageDialog(this, "Please enter a quantity greater than zero");
                        return; //Exits method if quantity is less then 1
                    }
                    else if (numberOfItems >= 20){
                        JOptionPane.showMessageDialog(this, "Please enter a quantity less than 20");
                        return; //Exits method if quantity is greater then 20
                    }
                    
                tableModel.addRow(new Object[]{itemName, price, numberOfItems}); //adds a new row with menu item
                cntl.calculateTotal(numberOfItems, price);
                totalCost.setText("$" + String.format("%.2f", cntl.getTotal()));
                MenuItem menuItem = new MenuItem(itemName, price, numberOfItems);
                cntl.addItemToOrder(menuItem);
                eaterySelector.setEnabled(false); //ensures one eatery per order
                } catch (NumberFormatException n){
                    JOptionPane.showMessageDialog(this, "Please enter a valid number for the quantity"); //ensures int and not string or double
                }
                
            }
            
        }
        
        if (e.getSource() == checkoutButton){
            if (!cntl.getOrderItems().isEmpty()){ //Must have one item in order to place the order. This verifies it
                Date orderDate = (Date) pickupTimeSpinner.getValue();
                SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yy");
                SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm a");

                String confirmedPickupDate = formatDate.format(orderDate);
                String confirmedPickupTime = formatTime.format(orderDate);

                cntl.setPickupInfo(confirmedPickupTime, confirmedPickupDate);

                cntl.createNewHistoryEntry(cntl.getOrderNumber(), cntl.getPickupDate(),cntl.getEatery(), cntl.getTotal());
                cntl.saveOrderHistory();

                cntl.markedOrderAsNotPickedUp();

                OrderConfirmationViewForm confirmationView = new OrderConfirmationViewForm(cntl);
                confirmationView.setVisible(true);
                this.setVisible(false); //Hides Order View Form
            }
            else{
                JOptionPane.showMessageDialog(this, "You must add items to the order first");
            }
            
        }
        
        //clears the items in the order
        if (e.getSource() == clearAllItems){
            DefaultTableModel tableModel = (DefaultTableModel) orderTable.getModel();
            
            cntl.clearAllItems();
            tableModel.setRowCount(0);
            cntl.resetTotal();
            totalCost.setText("$0.00");
        }
        
        //undoes the last command from remove last item or clear all items
        if (e.getSource() == undoButton){
            DefaultTableModel tableModel = (DefaultTableModel) orderTable.getModel();
            boolean commandCompleted = cntl.undoLastCommand();
            
            if(commandCompleted){ //checks if undo was already ran
                
                OrderCommandIF lastCommand = cntl.getLastCommand();
                
                tableModel.setRowCount(0); //resets the table to zero rows to repopulate after undo
                cntl.resetTotal();
                
                //if else if to check which last command was executed to complete proper undo
                if(lastCommand instanceof ClearAllItemsCommand){
                    for (MenuItem item : cntl.getOrderItems()){
                        tableModel.addRow(new Object[] {item.getItemName(), item.getPrice(), item.getQuantity()});
                        cntl.calculateTotal(item.getQuantity(), item.getPrice());
                    }
                }
                else if (lastCommand instanceof RemoveLastItemCommand){
                    
                    for (MenuItem item : cntl.getOrderItems()){
                        tableModel.addRow(new Object[] {item.getItemName(), item.getPrice(), item.getQuantity()});
                        cntl.calculateTotal(item.getQuantity(), item.getPrice());
                    }
                }
                
            }
            
            totalCost.setText("$" + String.format("%.2f", cntl.getTotal()));
            
                 
        }
        
        //Removes the last item that was added to the order
        if (e.getSource() == removeLastItem){
            DefaultTableModel tableModel = (DefaultTableModel) orderTable.getModel();
            
            
            try{
                cntl.removeLastItem();
                tableModel.removeRow(tableModel.getRowCount() - 1);
                cntl.resetTotal();
         
                for (MenuItem item : cntl.getOrderItems()){
                    
                    cntl.calculateTotal(item.getQuantity(), item.getPrice());
                    
                }
            
                totalCost.setText("$" + String.format("%.2f", cntl.getTotal()));
                }
            catch(ArrayIndexOutOfBoundsException a){
                JOptionPane.showMessageDialog(this, "Order is empty, nothing to remove");
            }
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        priceInfoField = new javax.swing.JTextArea();
        foodType = new javax.swing.JComboBox<>();
        options = new javax.swing.JComboBox<>();
        quantity = new javax.swing.JTextField();
        quantityLabel = new javax.swing.JLabel();
        addToOrder = new javax.swing.JButton();
        optionPane = new javax.swing.JOptionPane();
        jPanel3 = new javax.swing.JPanel();
        orderDetails = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        total = new javax.swing.JLabel();
        totalCost = new javax.swing.JLabel();
        checkoutButton = new javax.swing.JButton();
        pickupTimeSpinner = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        removeLastItem = new javax.swing.JButton();
        clearAllItems = new javax.swing.JButton();
        undoButton = new javax.swing.JButton();
        eaterySelector = new javax.swing.JComboBox<>();
        orderText = new javax.swing.JLabel();
        orderNumber = new javax.swing.JLabel();

        setTitle("WallyLand - Create Order");
        setIconImage(new ImageIcon(getClass().getResource("/images/theme-park.png")).getImage());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(58, 115, 169));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Eatery:");

        jPanel2.setBackground(new java.awt.Color(227, 236, 241));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Menu");
        jLabel2.setToolTipText("");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        priceInfoField.setBackground(new java.awt.Color(227, 236, 241));
        priceInfoField.setColumns(20);
        priceInfoField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        priceInfoField.setRows(5);
        priceInfoField.setText("Prices include all taxes. Order will be charged\nto the credit card that was used to purchase \npark tickets.");
        priceInfoField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(priceInfoField);

        foodType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        options.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        quantity.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        quantity.setPreferredSize(new java.awt.Dimension(22, 22));

        quantityLabel.setText("Quantity (Limit 20)");

        addToOrder.setText("Add to Order");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(optionPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(152, 152, 152)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(foodType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(124, 124, 124)
                            .addComponent(quantityLabel)
                            .addGap(18, 18, 18)
                            .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(addToOrder)
                            .addGap(28, 28, 28)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(options, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(foodType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(options, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityLabel)
                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addComponent(addToOrder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(optionPane, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        jPanel3.setBackground(new java.awt.Color(227, 236, 241));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        orderDetails.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        orderDetails.setText("Order Details");

        orderTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Price ($)", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(orderTable);

        total.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        total.setText("Total:");

        totalCost.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        totalCost.setText("$0.00");

        checkoutButton.setText("Complete Order");

        pickupTimeSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MINUTE));

        jLabel3.setText("Chose Pickup Time");

        removeLastItem.setText("Remove Last Item Added");

        clearAllItems.setText("Clear All Items");

        undoButton.setText("Undo");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(orderDetails)
                        .addGap(125, 125, 125))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(total)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalCost)
                            .addComponent(pickupTimeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(82, 82, 82))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(checkoutButton))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(removeLastItem)
                                .addGap(18, 18, 18)
                                .addComponent(clearAllItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(undoButton)
                .addGap(158, 158, 158))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(orderDetails)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeLastItem)
                    .addComponent(clearAllItems))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(undoButton)
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(total)
                    .addComponent(totalCost))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pickupTimeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(checkoutButton)
                .addGap(25, 25, 25))
        );

        orderText.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        orderText.setForeground(new java.awt.Color(255, 255, 255));
        orderText.setText("Order #: ");

        orderNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        orderNumber.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(orderText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orderNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eaterySelector, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(285, 285, 285))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(eaterySelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderText)
                    .addComponent(orderNumber))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
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
//            java.util.logging.Logger.getLogger(OrderViewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(OrderViewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(OrderViewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(OrderViewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new OrderViewForm().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToOrder;
    private javax.swing.JButton checkoutButton;
    private javax.swing.JButton clearAllItems;
    private javax.swing.JComboBox<Eatery> eaterySelector;
    private javax.swing.JComboBox<String> foodType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JOptionPane optionPane;
    private javax.swing.JComboBox<String> options;
    private javax.swing.JLabel orderDetails;
    private javax.swing.JLabel orderNumber;
    private javax.swing.JTable orderTable;
    private javax.swing.JLabel orderText;
    private javax.swing.JSpinner pickupTimeSpinner;
    private javax.swing.JTextArea priceInfoField;
    private javax.swing.JTextField quantity;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JButton removeLastItem;
    private javax.swing.JLabel total;
    private javax.swing.JLabel totalCost;
    private javax.swing.JButton undoButton;
    // End of variables declaration//GEN-END:variables

    
}

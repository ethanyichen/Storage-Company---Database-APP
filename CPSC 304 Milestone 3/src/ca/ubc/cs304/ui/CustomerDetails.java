package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.CustomerController;
import ca.ubc.cs304.model.Customer;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class CustomerDetails implements ActionListener {

    private JFrame cFrame;
    private JPanel cPanel;
    private JButton currentStorage;
    private JButton currentMembership;
    private JButton newMemberShip;
    private JButton newStorage;
    private JTextArea resultDisplay;
    private JLabel nameDisplay;
    private  JLabel cIDDisplay;

    private  Customer customer;
    private int CUSTOMER_ID_DB;
    private String CUSTOMER_NAME_DB;
    private String CUSTOMER_PHONE_DB;
    private CustomerController customerController;

    public CustomerDetails(CustomerController customerController,Customer customer) {
        this.customerController = customerController;
        this.customer = customer;
        CUSTOMER_ID_DB = customer.getCustomerID();
        CUSTOMER_NAME_DB = customer.getCustomerName();
        CUSTOMER_PHONE_DB = customer.getCustomerPhoneNum();
    }


    public void customerDetials(){
        cPanel = new JPanel();
        cFrame = new JFrame("Customer Details");
        cFrame.add(cPanel, BorderLayout.CENTER);

        cPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        cPanel.setLayout(null);

        JLabel title = new JLabel("Customer Details");
        title.setBounds(10,15,400,40);
        Font f = new Font("serif", Font.BOLD, 30);
        title.setFont(f);
        cPanel.add(title);

        JLabel name = new JLabel("Name ");
        name.setBounds(430,5,300,20);
        cPanel.add(name);

        nameDisplay = new JLabel("");
        nameDisplay.setBounds(475,5,300,20);
        cPanel.add(nameDisplay);

        JLabel cID = new JLabel("ID ");
        cID.setBounds(430,30,300,25);
        cPanel.add(cID);

        cIDDisplay = new JLabel("");
        cIDDisplay.setBounds(455,30,300,25);
        cPanel.add(cIDDisplay);

        currentStorage = new JButton("Current Storage");
        currentStorage.setBounds(35,100,200,50);
        currentStorage.addActionListener(this);
        currentStorage.setFont(currentStorage.getFont().deriveFont(Font.BOLD));
        cPanel.add(currentStorage);

        currentMembership = new JButton("Current Membership");
        currentMembership.setBounds(35,170,200,50);
        currentMembership.addActionListener(this);
        currentMembership.setFont(currentStorage.getFont());
        cPanel.add(currentMembership);

        newMemberShip = new JButton("Start a new membership");
        newMemberShip.setBounds(35,240,200,50);
        newMemberShip.addActionListener(this);
        newMemberShip.setFont(currentStorage.getFont());
        cPanel.add(newMemberShip);

        newStorage = new JButton("Start a new storage");
        newStorage.setBounds(35,310,200,50);
        newStorage.addActionListener(this);
        newStorage.setFont(currentStorage.getFont());
        cPanel.add(newStorage);

        JPanel rPanel = new JPanel();
        rPanel.setBounds(280,100,300,310);
        rPanel.setBorder ( new TitledBorder( new EtchedBorder(), "Result:", 0, 0, newStorage.getFont(), Color.decode("#222D6D") ) );
        resultDisplay = new JTextArea(16,23);
        resultDisplay.setLocation(280,100);
        resultDisplay.setEditable (false);
        JScrollPane scroll = new JScrollPane (resultDisplay);
        rPanel.add(scroll);
        cPanel.add(rPanel);

        title.setForeground(Color.decode("#222D6D"));
        name.setForeground(Color.decode("#222D6D"));
        cID.setForeground(Color.decode("#222D6D"));

        cPanel.setBackground(Color.decode("#E5F1F6"));
        rPanel.setBackground(Color.decode("#E5F1F6"));

        currentStorage.setForeground(Color.decode("#222D6D"));
        newMemberShip.setForeground(Color.decode("#222D6D"));
        currentMembership.setForeground(Color.decode("#222D6D"));
        newStorage.setForeground(Color.decode("#222D6D"));
        rPanel.setForeground(Color.decode("#222D6D"));


        //TODO set name and ID to the customer currently in progress
        nameDisplay.setText(CUSTOMER_NAME_DB);
        cIDDisplay.setText(Integer.toString(CUSTOMER_ID_DB));

        cFrame.pack();
        cFrame.setSize(600,500);
        cFrame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == currentStorage) {
            HashMap<Integer,Integer> unitIDAndCount = customerController.currentStorageCount();
            if(unitIDAndCount != null) {
                ArrayList unitList = new ArrayList<>(unitIDAndCount.keySet());
                resultDisplay.setText("Current Storage:\n");
                //TODO deal with current Storage result
                for (int i = 0; i < unitList.size(); i++) {
                    resultDisplay.append("Unit " + unitList.get(i) + ": Currently storing  " +
                            Integer.toString(unitIDAndCount.get(unitList.get(i))) + "  box(es)\n");
                }
            }
            }

        if (e.getSource() == currentMembership) {
            resultDisplay.setText("Current Membership:");
            //TODO deal with current Storage result
        }
        if (e.getSource() == newMemberShip) {
            //TODO trigger new Membership Screen
            CustomerNew customerNew = new CustomerNew();
            customerNew.CustomerNew();
//            customerNew.run();
        }
        if (e.getSource() == newStorage) {
            //TODO trigger new Storage Screen
            CustomerStorage customerStorage = new CustomerStorage();
            customerStorage.CustomerStorage();
        }
    }
}

package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerManagement implements ActionListener {

    private JFrame cFrame;
    private JLabel cLabel;
    private JPanel cPanel;
    private JLabel name;
    private JLabel phoneNum;
    private JTextField cName;
    private JTextField cPhoneNum;
    private JButton submit;
    private JLabel submitMessege;
    private JButton search;
    private JLabel searchError;
    private JTextField cID;
    private JLabel submitIDMessege;
    private JButton activeCustomer;
    private JButton cDirec;
    private JButton memberOfAll;

    private int CUSTOMER_ID_DB;
    private Color submitMsgColorRed = Color.decode("#990000");


    public void customerManagement() {

        cPanel = new JPanel();
        cFrame = new JFrame("Customer Management");
        cFrame.add(cPanel, BorderLayout.CENTER);

        cPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        cPanel.setLayout(null);

        JLabel title = new JLabel("Customer Mangement");
        title.setBounds(150,15,300,40);
        Font f = new Font("serif", Font.BOLD, 30);

        title.setFont(f);
        cPanel.add(title);

        //add new customer
        JLabel newCustomer = new JLabel("New Customer");
        newCustomer.setBounds(10,50,165,25);
        newCustomer.setFont(newCustomer.getFont().deriveFont(Font.BOLD));
        cPanel.add(newCustomer);

        name = new JLabel("Name:");
        name.setBounds(10,80,80,25);
        cPanel.add(name);

        cName = new JTextField();
        cName.setBounds(120,80,180,25);
        cPanel.add(cName);

        phoneNum = new JLabel("Phone Number:");
        phoneNum.setBounds(10,110,180,25);
        cPanel.add(phoneNum);

        cPhoneNum = new JTextField();
        cPhoneNum.setBounds(120,110,180,25);
        cPanel.add(cPhoneNum);

        submit = new JButton("Submit");
        submit.setBounds(310,110,80,25);
        submit.setFont(newCustomer.getFont().deriveFont(Font.BOLD));
        cPanel.add(submit);
        cLabel = new JLabel();
        cPanel.add(cLabel);
        //backend submit, and generate cID
        submit.addActionListener(this);


        submitMessege = new JLabel("");
        submitMessege.setBounds(75,135,475,15);
        cPanel.add(submitMessege);

        submitIDMessege = new JLabel("");
        submitIDMessege.setBounds(150,150,200,15);
        cPanel.add(submitIDMessege);

        //Horizontal Line
        JLabel line = new JLabel("_______________________________________________________________________________________");
        line.setBounds(10,160,600,15);
        cPanel.add(line);

        //Search Customer
        JLabel searchCustomer = new JLabel("Search Existing Customer");
        searchCustomer.setBounds(10,180,400,25);
        searchCustomer.setFont(newCustomer.getFont().deriveFont(Font.BOLD));
        cPanel.add(searchCustomer);

        JLabel customerIDLabel = new JLabel("CustomeID:");
        customerIDLabel.setBounds(10,210,100,25);
        cPanel.add(customerIDLabel);

        cID = new JTextField();
        cID.setBounds(120,210,180,25);
        cPanel.add(cID);

        search = new JButton("Search");
        search.setBounds(300,210,80,25);
        search.addActionListener(this);
        search.setFont(newCustomer.getFont().deriveFont(Font.BOLD));
        cPanel.add(search);

        searchError = new JLabel("");
        searchError.setBounds(150,240,200,15);
        cPanel.add(searchError);

        cDirec = new JButton("Customer Directories");
        cDirec.setBounds(100,260,370,50);
        cDirec.setFont(newCustomer.getFont().deriveFont(Font.BOLD));
        cDirec.addActionListener(this);
        cPanel.add(cDirec);

        memberOfAll = new JButton("Customer That is Member of Every Warehouse");
        memberOfAll.setBounds(100,320,370,50);
        memberOfAll.setFont(newCustomer.getFont().deriveFont(Font.BOLD));
        memberOfAll.addActionListener(this);
        cPanel.add(memberOfAll);

        activeCustomer = new JButton("Active Customers");
        activeCustomer.setBounds(100,380,370,50);
        activeCustomer.setFont(newCustomer.getFont().deriveFont(Font.BOLD));
        activeCustomer.addActionListener(this);
        cPanel.add(activeCustomer);


        title.setForeground(Color.decode("#222D6D"));
        cPanel.setBackground(Color.decode("#E5F1F6"));
        submit.setForeground(Color.decode("#222D6D"));
        search.setForeground(Color.decode("#222D6D"));
        activeCustomer.setForeground(Color.decode("#222D6D"));
        cDirec.setForeground(Color.decode("#222D6D"));
        memberOfAll.setForeground(Color.decode("#222D6D"));
        newCustomer.setForeground(Color.decode("#222D6D"));
        customerIDLabel.setForeground(Color.decode("#222D6D"));
        name.setForeground(Color.decode("#222D6D"));
        phoneNum.setForeground(Color.decode("#222D6D"));
        line.setForeground(Color.decode("#222D6D"));
        searchCustomer.setForeground(Color.decode("#222D6D"));

        submitMessege.setForeground(submitMsgColorRed);
        submitIDMessege.setForeground(Color.GREEN);

        cFrame.pack();
        cFrame.setSize(600,500);
        cFrame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        //search button
        if (e.getSource() == search) {
            searchError.setText("");
            int cID_INT = -1;
            String cID_TEXT = cID.getText();
            if (cID_TEXT.matches("[0-9]+") && cID_TEXT.length() > 0) {
                cID_INT = Integer.parseInt(cID_TEXT);
            }
            //TODO deal with search no results
//            if (cID_INT == -1 OR not found) {
//                searchError.setText("Invalid input");
//            } else{
                CUSTOMER_ID_DB = cID_INT;
//            }

            //TODO trigger new screen
            cID.setText("");
            CustomerDetails customerDetails = new CustomerDetails(CUSTOMER_ID_DB);
            customerDetails.customerDetials();
        }

        //submit button
        if (e.getSource() == submit) {
            String cNameText = cName.getText();
            String cPhoneNumText = cPhoneNum.getText();
            int cphoneNumInt = -1;
            if (cPhoneNumText.matches("[0-9]+") && cPhoneNumText.length() > 0) {
                cphoneNumInt= Integer.parseInt(cPhoneNumText);
            }
            //TODO generate new CustomerID, how to deal with duplicated adds
            String submitMsg;
            String submitMsgID;
            if (cNameText.equals("")|cphoneNumInt==-1) {
                submitMessege.setForeground(submitMsgColorRed);
                submitMsg = "Unsuccessful. Missing Fields or incorrect input. no CustomerID generated.";
                submitMsgID="";
            }else {
                submitMessege.setForeground(Color.GREEN);
                submitMsg = "Customer \"" + cNameText + "\" with phone number" + cphoneNumInt + " successfully added.";
                int generatedCID = -1;
                submitMsgID = "customerID Assigned = " + generatedCID ;
            }
            submitMessege.setText(submitMsg);
            cName.setText("");
            cPhoneNum.setText("");
            submitIDMessege.setText(submitMsgID);
        }


        //directory button
        if (e.getSource() == cDirec) {
            Directory customerDirectory = new Directory("Customer Directories:");
            customerDirectory.directory();
        }

        if (e.getSource() == memberOfAll) {
            Directory customerDirectory = new Directory("Customer That is Member of Every Warehouse");
            customerDirectory.directory();
        }

        if (e.getSource() == activeCustomer) {
            Directory customerDirectory = new Directory("Active Customer Directories:");
            customerDirectory.directory();
        }

    }
}

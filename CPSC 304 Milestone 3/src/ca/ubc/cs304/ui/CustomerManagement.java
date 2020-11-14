package ca.ubc.cs304.ui;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerManagement implements ActionListener {

    public static void main(String[] args) {
        CustomerManagement customerManagement = new CustomerManagement();
        customerManagement.customerManagement();
    }

    private int count = 0;
    private JFrame cFrame;
    private JLabel cLabel;
    private JPanel cPanel;
    private JLabel name;
    private JLabel phoneNum;
    private JTextField cName;
    private JTextField cPhoneNum;
    private JButton submit;
    private JLabel addConfirmation;
    private JLabel submitMessege;
    private JButton search;
    private JLabel searchError;
    private JTextField cID;
    private JLabel submitIDMessege;
    private JButton activeCustomer;
    private JButton cDirec;
    private JButton memberOfAll;


    public void customerManagement() {

        cPanel = new JPanel();
        cFrame = new JFrame("Customer Management");
        cFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cFrame.add(cPanel, BorderLayout.CENTER);

        cPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        cPanel.setLayout(null);

        JLabel title = new JLabel("Customer Mangement");
        title.setBounds(150,15,300,30);
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
        cPanel.add(search);

        searchError = new JLabel("");
        searchError.setBounds(150,240,200,15);
        cPanel.add(searchError);

        cDirec = new JButton("Customer Directories");
        cDirec.setBounds(150,270,300,25);
        cDirec.addActionListener(this);
        cPanel.add(cDirec);

        memberOfAll = new JButton("Customer that is member of all warehouse");
        memberOfAll.setBounds(150,300,300,25);
        memberOfAll.addActionListener(this);
        cPanel.add(memberOfAll);

        activeCustomer = new JButton("Active Customers");
        activeCustomer.setBounds(150,330,300,25);
        activeCustomer.addActionListener(this);
        cPanel.add(activeCustomer);

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
            //TODO trigger new screen
//            CustomerDetails employeeDetails = new CustomerDetails(cID_INT);
            //TODO deal with search no results
            if (cID_INT == -1) {
                searchError.setText("Invalid input");
            }
            cID.setText("");
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
                submitMsg = "Unsuccessful. Missing Fields or incorrect input. no CustomerID generated.";
                submitMsgID="";
            }else {
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
            //TODO trigger directory screen
//            customerDirectory customerDirectory = new CustomerDirectory("Directory");
        }

        if (e.getSource() == memberOfAll) {
            //TODO trigger directory screen
//            customerDirectory customerDirectory = new CustomerDirectory("memberOfAll");
        }

        if (e.getSource() == activeCustomer) {
            //TODO trigger directory screen
//            customerDirectory customerDirectory = new CustomerDirectory("activeCustomer");
        }

    }
}

package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.CustomerController;
import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.exceptions.ServerErrorException;
import ca.ubc.cs304.model.Box;
import ca.ubc.cs304.model.Customer;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class Directory {

    private JFrame cFrame;
    private JPanel cPanel;
    private JTextArea resultDisplay;

    private String DIRECTORY_OPTION;
    private DatabaseConnectionHandler db;
    private CustomerController customerController;

    public Directory(String DIRECTORY_OPTION,DatabaseConnectionHandler db) {
        this.DIRECTORY_OPTION = DIRECTORY_OPTION;
        this.db = db;
    }

    public void directory(){
        cPanel = new JPanel();
        cFrame = new JFrame("Directory");
        cFrame.add(cPanel, BorderLayout.CENTER);

        cPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        cPanel.setLayout(null);

        JLabel title = new JLabel("Directory");
        title.setBounds(10, 15, 300, 40);
        Font f = new Font("serif", Font.BOLD, 30);
        title.setFont(f);
        cPanel.add(title);


        // TODO fetch each option result
        JPanel rPanel = new JPanel();
        rPanel.setBounds(10, 70, 580, 390);
        rPanel.setBorder(new TitledBorder(new EtchedBorder(), DIRECTORY_OPTION, 0, 0,
                new JLabel().getFont(), Color.decode("#222D6D")));
        resultDisplay = new JTextArea(21, 46);
        resultDisplay.setEditable(false);
        JScrollPane scroll = new JScrollPane(resultDisplay);
        rPanel.add(scroll);
        cPanel.add(rPanel);

        cPanel.setBackground(Color.decode("#E5F1F6"));
        rPanel.setBackground(Color.decode("#E5F1F6"));
        title.setForeground(Color.decode("#222D6D"));

        cFrame.pack();
        cFrame.setSize(600, 500);
        cFrame.setVisible(true);

        listCustomerDisplay();
    }
        private void listCustomerDisplay() {
            try {
                customerController = new CustomerController(db);
                ArrayList<Customer> customerList = customerController.allCustomer();
                for (int i = 0; i < customerList.size(); i++) {
                    Customer curr = customerList.get(i);
                    resultDisplay.append("Customer ID: " + curr.getCustomerID() + "|  Name: " +
                            curr.getCustomerName() + "|  Phone Number: " + curr.getCustomerPhoneNum() + " \n");
                }
            } catch (ServerErrorException serverErrorException) {
                resultDisplay.append("Server Error");
                serverErrorException.printStackTrace();
            }
        }
    }


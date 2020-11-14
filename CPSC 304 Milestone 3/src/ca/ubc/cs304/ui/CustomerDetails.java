package ca.ubc.cs304.ui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerDetails implements ActionListener {

    private JFrame cFrame;
    private JPanel cPanel;
    private JButton currentStorage;
    private JButton currentMembership;
    private JButton newMemberShip;
    private JButton newStorage;
    private JTextArea resultDisplay;

    public static void main(String[] args) {
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.customerDetials();
    }
    public void customerDetials(){
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

        currentStorage = new JButton("Current Storage");
        currentStorage.setBounds(35,100,200,50);
        currentStorage.addActionListener(this);
        cPanel.add(currentStorage);

        currentMembership = new JButton("Current Membership");
        currentMembership.setBounds(35,170,200,50);
        currentMembership.addActionListener(this);
        cPanel.add(currentMembership);

        newMemberShip = new JButton("Start a new membership");
        newMemberShip.setBounds(35,240,200,50);
        newMemberShip.addActionListener(this);
        cPanel.add(newMemberShip);

        newStorage = new JButton("Start a new storage");
        newStorage.setBounds(35,310,200,50);
        newStorage.addActionListener(this);
        cPanel.add(newStorage);

        JPanel rPanel = new JPanel();
        rPanel.setBounds(280,100,300,310);
        rPanel.setBorder ( new TitledBorder( new EtchedBorder(), "Result:" ) );
        resultDisplay = new JTextArea(16,23);
        resultDisplay.setLocation(280,100);
        resultDisplay.setEditable (false);
        JScrollPane scroll = new JScrollPane (resultDisplay);
        rPanel.add(scroll);
        cPanel.add(rPanel);


        cFrame.pack();
        cFrame.setSize(600,500);
        cFrame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

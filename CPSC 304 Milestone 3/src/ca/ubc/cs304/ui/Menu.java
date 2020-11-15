package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Menu implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private  JButton employee;
    private  JButton customer;

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.menu();
    }

    public void menu(){
        panel = new JPanel();
        frame = new JFrame("Storage Management -- Main Menu");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(null);

        JLabel title = new JLabel("Storage Management");
        title.setBounds(175,15,300,30);
        Font f = new Font("Serif", Font.BOLD, 30);

        title.setFont(f);
        panel.add(title);

        Font f2 = new Font("Arial", Font.BOLD, 15);
        employee = new JButton("Employee");
        employee.setBounds(200,280,200,75);
        employee.setFont(f2);
        employee.addActionListener(this);
        panel.add(employee);

        customer = new JButton("Customer");
        customer.setBounds(200,120,200,75);
        customer.setFont(f2);
//        customer.setFont(new JLabel().getFont().deriveFont(25));
        customer.addActionListener(this);
        panel.add(customer);

        title.setForeground(Color.decode("#222D6D"));
        customer.setForeground(Color.decode("#222D6D"));
        employee.setForeground(Color.decode("#222D6D"));
        panel.setBackground(Color.decode("#E5F1F6"));

        frame.pack();
        frame.setSize(600,500);
        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == customer) {
            CustomerManagement customerManagement = new CustomerManagement();
            customerManagement.customerManagement();
        }

        if (e.getSource() == employee) {
            EmployeeManagement employeeManagement = new EmployeeManagement();
            employeeManagement.employeeManagement();
        }
    }
}

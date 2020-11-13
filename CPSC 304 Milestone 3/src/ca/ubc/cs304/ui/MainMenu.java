package ca.ubc.cs304.ui;

import com.sun.tools.javac.comp.Flow;

import javax.swing.*;
import java.awt.*;

public class MainMenu implements Runnable {

    private JFrame frame;
    private JPanel panel;


    @Override
    public void run() {
        frame = new JFrame("Main Screen");
        panel = new JPanel();
        frame.add(panel);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }


    private void createComponents(Container container) {
        // Buttons
        JButton find = new JButton("Find a box");
        JButton employee = new JButton("Employee");
        JButton specialQueries = new JButton("Special Queries");
        JButton customer = new JButton("Customer");

//        find.setBounds(10,40,100,20);
//        employee.setBounds(100,500,100,20);
//        specialQueries.setBounds(300,500,100,20);
//        customer.setBounds(500,500,100,20);

        panel.add(employee);
        panel.add(customer);
        panel.add(specialQueries);

        JPanel panel1 = new JPanel();
        panel1.add(find);

        container.add(panel1, BorderLayout.CENTER);
        container.add(panel, BorderLayout.SOUTH);
    }

    public JFrame getFrame() {
        return frame;
    }
}

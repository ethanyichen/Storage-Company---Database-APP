package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;

public class EmployeeDirectory {
    private JFrame frame;
    private JPanel panel;

    EmployeeDirectory(){

        panel = new JPanel();
        frame = new JFrame("Employee Directory");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(null);

        JLabel header = new JLabel ("Employee Directory");
        header.setBounds(10,10,300,25);
        panel.add(header);

        JLabel line = new JLabel("_______________________________________________________________________________________");
        line.setBounds(10,30,580,25);
        panel.add(line);

        frame.pack();
        frame.setSize(600,500);
        frame.setVisible(true);

    }
}

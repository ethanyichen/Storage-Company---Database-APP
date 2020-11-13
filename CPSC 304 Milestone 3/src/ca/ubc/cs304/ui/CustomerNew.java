package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CustomerNew implements Runnable {

    private JFrame frame;
    private JPanel panel;

    @Override
    public void run() {
        frame = new JFrame("Customer Management - New Membership");
        panel = new JPanel();
        frame.add(panel);
        frame.setPreferredSize(new Dimension(600, 175));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        container.add(createStart(), BorderLayout.NORTH);
        container.add(warehouseSelect(), BorderLayout.CENTER);
        container.add(dateSelect(), BorderLayout.SOUTH);

    }

    private JPanel warehouseSelect() {
        JPanel panel = new JPanel(new FlowLayout());

        JTextField start = new JTextField("Select a Warehouse");
        start.setEditable(false);
        JComboBox<String> jc = new JComboBox<String>();
        panel.add(start);
        panel.add(jc);

        return panel;
    }

    private JPanel dateSelect() {
        JPanel panel = new JPanel(new FlowLayout());
        JTextField start = new JTextField("Insert Starting Date");
        start.setEditable(false);
        JTextField insert = new JTextField("   date   ");
        panel.add(start);
        panel.add(insert);
        return panel;
    }

    private JPanel createStart() {
        JPanel panel = new JPanel(new FlowLayout());
        JTextField start = new JTextField("Start a new membership");
        start.setEditable(false);

        JTextField name = new JTextField("   name   ");
        JTextField id = new JTextField("   id   ");
        panel.add(start);
        panel.add(name);
        panel.add(id);

        return panel;
    }
}

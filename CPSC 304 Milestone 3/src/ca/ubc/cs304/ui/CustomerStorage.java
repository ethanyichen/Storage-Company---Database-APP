package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;

public class CustomerStorage implements Runnable {
    private JFrame frame;
    private JPanel panel;

    @Override
    public void run() {
        frame = new JFrame("Customer Management - New Storage");
        panel = new JPanel();
        frame.add(panel);
        frame.setPreferredSize(new Dimension(600, 200));
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        container.add(createStorage(),BorderLayout.NORTH);
        container.add(warehouseSelect(), BorderLayout.CENTER);
        container.add(details(), BorderLayout.SOUTH);
    }

    private JPanel details() {
        JPanel panel = new JPanel(new GridLayout(3,2));
        JTextField feeIn = new JTextField("   1   ");
        JTextField fee = new JTextField("Monthly Fee");
        fee.setEditable(false);
        JTextField start = new JTextField("Start Date");
        start.setEditable(false);
        JTextField startIn = new JTextField("   2   ");
        JTextField end = new JTextField("End date");
        JTextField endIn = new JTextField("   3   ");
        end.setEditable(false);

        panel.add(fee);
        panel.add(feeIn);
        panel.add(start);
        panel.add(startIn);
        panel.add(end);
        panel.add(endIn);
        return panel;
    }


    private JPanel warehouseSelect() {
        JPanel panel = new JPanel(new FlowLayout());
        JTextField start = new JTextField("Select Warehouse");
        start.setEditable(false);
        JComboBox<String> jc = new JComboBox<String>();
        panel.add(start);
        panel.add(jc);

        return panel;
    }


    private JPanel createStorage() {
        JPanel panel = new JPanel(new FlowLayout());
        JTextField start = new JTextField("New Storage");
        start.setEditable(false);

        JTextField name = new JTextField("   name   ");
        JTextField id = new JTextField("   id   ");
        panel.add(start);
        panel.add(name);
        panel.add(id);

        return panel;
    }
}

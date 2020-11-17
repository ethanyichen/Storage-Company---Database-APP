package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerStorage implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JTextField name;
    private JTextField id;
    private JButton submit;
    private JButton submit1;
    private JTextField fee;
    private JTextField start;
    private JTextField end;
    private JLabel out;
    private JLabel out1;


    public void CustomerStorage(){
        panel = new JPanel();
        frame = new JFrame("Customer Management - New Storage");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(null);

        JLabel title = new JLabel("Add a New Storage");
        title.setBounds(150,15,500,40);
        Font f = new Font("Serif", Font.BOLD, 30);
        title.setFont(f);
        panel.add(title);

        JLabel nameDesc = new JLabel("Name:");
        nameDesc.setBounds(10, 80, 50, 25);
        panel.add(nameDesc);

        name = new JTextField();
        name.setBounds(70, 80, 180,25);
        panel.add(name);

        JLabel idDesc = new JLabel("ID:");
        idDesc.setBounds(10, 110, 30, 25);
        panel.add(idDesc);

        id = new JTextField();
        id.setBounds(70,110,180,25);
        panel.add(id);


        submit = new JButton("Submit");
        submit.setBounds(260,110,80,25);
        panel.add(submit);
        submit.addActionListener(this);

        out = new JLabel("");
        out.setBounds(200, 135, 300, 25);
        panel.add(out);

        JLabel line = new JLabel("_______________________________________________________________________________________");
        line.setBounds(10,150,600,15);
        panel.add(line);

        JLabel selectWare = new JLabel("Select Warehouse:");
        selectWare.setBounds(10, 190, 120, 25);
        panel.add(selectWare);

        JComboBox<String> choose = new JComboBox<String>();
        choose.setBounds(140, 190, 100, 25);
        panel.add(choose);

        JLabel feeDesc = new JLabel("Monthly Fee:");
        feeDesc.setBounds(10, 220, 100, 25);
        panel.add(feeDesc);

        fee = new JTextField();
        fee.setBounds(140, 220, 150, 25);
        panel.add(fee);

        JLabel startDesc = new JLabel("Start Date:");
        startDesc.setBounds(10, 250, 100, 25);
        panel.add(startDesc);

        start = new JTextField();
        start.setBounds(140, 250, 150, 25);
        panel.add(start);

        JLabel endDesc = new JLabel("End Date:");
        endDesc.setBounds(10, 280, 100, 25);
        panel.add(endDesc);

        end = new JTextField();
        end.setBounds(140, 280, 150, 25);
        panel.add(end);

        submit1 = new JButton("Submit");
        submit1.setBounds(310,280,80,25);
        panel.add(submit1);
        submit1.addActionListener(this);

        out1 = new JLabel("");
        out1.setBounds(200, 300, 300, 25);
        panel.add(out1);


        title.setForeground(Color.decode("#222D6D"));
        out.setForeground(Color.decode("#990000"));
        out1.setForeground(Color.decode("#990000"));
        panel.setBackground(Color.decode("#E5F1F6"));
        submit.setForeground(Color.decode("#222D6D"));
        submit1.setForeground(Color.decode("#222D6D"));
        name.setForeground(Color.decode("#222D6D"));
        start.setForeground(Color.decode("#222D6D"));
        id.setForeground(Color.decode("#222D6D"));
        end.setForeground(Color.decode("#222D6D"));
        fee.setForeground(Color.decode("#222D6D"));
        selectWare.setForeground(Color.decode("#222D6D"));
        nameDesc.setForeground(Color.decode("#222D6D"));
        idDesc.setForeground(Color.decode("#222D6D"));
        startDesc.setForeground(Color.decode("#222D6D"));
        endDesc.setForeground(Color.decode("#222D6D"));
        feeDesc.setForeground(Color.decode("#222D6D"));
        choose.setForeground(Color.decode("#222D6D"));
        line.setForeground(Color.decode("#222D6D"));


        frame.pack();
        frame.setSize(600,500);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO add cases for wrong inputs
//        if (e.getSource() == submit) {
//            if (name.getText().equals("") || id.getText().equals("")) {
//
//            }
//        }
    }
}

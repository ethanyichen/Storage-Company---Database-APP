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

        JLabel selectWare = new JLabel("Select Warehouse:");
        selectWare.setBounds(10, 170, 120, 25);
        panel.add(selectWare);

        JComboBox<String> choose = new JComboBox<String>();
        choose.setBounds(140, 170, 100, 25);
        panel.add(choose);

        JLabel feeDesc = new JLabel("Monthly Fee:");
        feeDesc.setBounds(10, 200, 100, 25);
        panel.add(feeDesc);

        fee = new JTextField();
        fee.setBounds(140, 200, 150, 25);
        panel.add(fee);

        JLabel startDesc = new JLabel("Start Date:");
        startDesc.setBounds(10, 230, 100, 25);
        panel.add(startDesc);

        start = new JTextField();
        start.setBounds(140, 230, 150, 25);
        panel.add(start);

        JLabel endDesc = new JLabel("End Date:");
        endDesc.setBounds(10, 260, 100, 25);
        panel.add(endDesc);

        end = new JTextField();
        end.setBounds(140, 260, 150, 25);
        panel.add(end);

        submit1 = new JButton("Submit");
        submit1.setBounds(310,260,80,25);
        panel.add(submit1);
        submit1.addActionListener(this);


        title.setForeground(Color.decode("#222D6D"));
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


        frame.pack();
        frame.setSize(600,500);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == submit) {
//            if (name.getText().equals("") || id.getText().equals("")) {
//
//            }
//        }
    }
}

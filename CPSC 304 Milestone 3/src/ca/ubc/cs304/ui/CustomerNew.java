package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerNew implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JTextField name;
    private JTextField id;
    private JButton submit;
    private JButton submit1;
    private JTextField date;

    public void CustomerNew(){
        panel = new JPanel();
        frame = new JFrame("Customer Management - New Membership");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(null);

        JLabel title = new JLabel("Start a new Membership");
        title.setBounds(150,15,500,40);
        Font f = new Font("Serif", Font.BOLD, 30);
        title.setFont(f);
        panel.add(title);

        Font f2 = new Font("Arial", Font.BOLD, 15);

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

        JLabel line = new JLabel("_______________________________________________________________________________________");
        line.setBounds(10,140,600,15);
        panel.add(line);

        JLabel selectWare = new JLabel("Select Warehouse:");
        selectWare.setBounds(10, 170, 120, 25);
        panel.add(selectWare);

        JComboBox<String> choose = new JComboBox<String>();
        choose.setBounds(140, 170, 60, 25);
        panel.add(choose);

        JLabel dateDesc = new JLabel("Start Date:");
        dateDesc.setBounds(10, 200, 120, 25);
        panel.add(dateDesc);

        date = new JTextField();
        date.setBounds(140,200,120,25);
        panel.add(date);

        submit1 = new JButton("Submit");
        submit.setBounds(280,200,80,25);
        panel.add(submit1);
        submit1.addActionListener(this);

        title.setForeground(Color.decode("#222D6D"));
        panel.setBackground(Color.decode("#E5F1F6"));
        submit.setForeground(Color.decode("#222D6D"));
        submit1.setForeground(Color.decode("#222D6D"));
        name.setForeground(Color.decode("#222D6D"));
        date.setForeground(Color.decode("#222D6D"));
        id.setForeground(Color.decode("#222D6D"));
        selectWare.setForeground(Color.decode("#222D6D"));
        nameDesc.setForeground(Color.decode("#222D6D"));
        idDesc.setForeground(Color.decode("#222D6D"));
        dateDesc.setForeground(Color.decode("#222D6D"));
        line.setForeground(Color.decode("#222D6D"));
        choose.setForeground(Color.decode("#222D6D"));


        frame.pack();
        frame.setSize(600,500);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}

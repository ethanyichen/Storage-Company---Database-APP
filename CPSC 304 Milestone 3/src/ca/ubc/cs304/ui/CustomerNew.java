package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.CustomerController;
import ca.ubc.cs304.exceptions.ServerErrorException;
import ca.ubc.cs304.model.Membership;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

public class CustomerNew implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JLabel name;
    private JLabel id;
    private JButton submit;
    private JButton submit1;
    private JTextField date;
    private JLabel out;
    private JComboBox<String> choose;
    private String start;
    private int CUSTOMER_ID_DB;
    private String CUSTOMER_NAME_DB = "NULL";
    CustomerController customerController;
    HashSet<String> checkMem;

    public CustomerNew(int CUSTOMER_ID_DB, String CUSTOMER_NAME_DB, CustomerController customerController) {
        this.CUSTOMER_ID_DB = CUSTOMER_ID_DB;
        this.CUSTOMER_NAME_DB = CUSTOMER_NAME_DB;
        this.customerController = customerController;
    }

    public void customerNew() {
        start = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        panel = new JPanel();
        frame = new JFrame("Customer Management - New Membership");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(null);

        JLabel title = new JLabel("Start a new Membership");
        title.setBounds(150, 15, 500, 40);
        Font f = new Font("Serif", Font.BOLD, 30);
        title.setFont(f);
        panel.add(title);

        Font f2 = new Font("Arial", Font.BOLD, 15);

        JLabel nameDesc = new JLabel("Name:");
        nameDesc.setBounds(10, 80, 50, 25);
        panel.add(nameDesc);

        name = new JLabel(CUSTOMER_NAME_DB);
        name.setBounds(70, 80, 180, 25);
        panel.add(name);

        JLabel idDesc = new JLabel("ID:");
        idDesc.setBounds(10, 110, 30, 25);
        panel.add(idDesc);

        id = new JLabel(String.valueOf(CUSTOMER_ID_DB));
        id.setBounds(70, 110, 180, 25);
        panel.add(id);

        submit = new JButton("Submit");
        submit.setBounds(260, 110, 80, 25);
        panel.add(submit);
        submit.addActionListener(this);


        JLabel line = new JLabel("_______________________________________________________________________________________");
        line.setBounds(10, 150, 600, 15);
        panel.add(line);

        JLabel selectWare = new JLabel("Select Warehouse:");
        selectWare.setBounds(10, 190, 120, 25);
        panel.add(selectWare);

        choose = new JComboBox<String>();
        choose.setBounds(140, 190, 87, 25);
        panel.add(choose);

        JLabel dateDesc = new JLabel("Start Date:");
        dateDesc.setBounds(10, 220, 120, 25);
        panel.add(dateDesc);

        date = new JTextField(start);
        date.setBounds(140, 220, 120, 25);
        panel.add(date);

        submit1 = new JButton("Submit");
        submit.setBounds(280, 220, 80, 25);
        panel.add(submit1);
        submit1.addActionListener(this);

        out = new JLabel("");
        out.setBounds(30, 350, 500, 25);
        panel.add(out);

        title.setForeground(Color.decode("#222D6D"));
        out.setForeground(Color.decode("#990000"));
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

        ArrayList<String> temp = customerController.currentWarehouse();
        for (String s : temp) {
            choose.addItem(s);
        }

        checkMem = customerController.checkWarehouseMember();


        frame.pack();
        frame.setSize(600, 500);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String dropDown = choose.getSelectedItem().toString();
            String newName = name.getText();
            String newID = id.getText();
            if (!checkMem.contains(dropDown + newID)) {
                if (isValidDate(date.getText())) {
                    out.setForeground(Color.decode("#0e6b0e"));
                    out.setText("Customer: " + newName + ", with ID: " + newID + " created at " + dropDown + " at " + date.getText());
                    customerController.addMember(new Membership(dropDown, newID, date.getText()));
                    checkMem.add(dropDown + newID);
                } else {
                    out.setForeground(Color.decode("#990000"));
                    out.setText("Invalid input, please verify the fields");
                }
            } else {
                out.setForeground(Color.decode("#990000"));
                out.setText("Customer is already member at " + dropDown + ", no changes done");
            }
        }
    }

    public boolean isValidDate(String inDate) {
        String check = "";
        for (int i = 6; i < inDate.length(); i++) {
            check += inDate.charAt(i);
        }
        Integer temp = Integer.parseInt(check);
        if (temp < 2020 || temp > 2100) {
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}

package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.CustomerController;
import ca.ubc.cs304.model.Membership;
import ca.ubc.cs304.model.Rent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class CustomerStorage implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JLabel name;
    private JLabel id;
    private JButton submit;
    private JTextField fee;
    private JTextField start;
    private JTextField end;
    private JLabel out;
    private JLabel out1;
    private JComboBox<String> choose;
    private int CUSTOMER_ID_DB;
    private String CUSTOMER_NAME_DB;
    private CustomerController customerController;
    private HashSet<String> checkRent;
    private HashSet<String> checkMem;

    public CustomerStorage(int CUSTOMER_ID_DB, String CUSTOMER_NAME_DB, CustomerController customerController) {
        this.CUSTOMER_ID_DB = CUSTOMER_ID_DB;
        this.CUSTOMER_NAME_DB = CUSTOMER_NAME_DB;
        this.customerController = customerController;
    }


    public void customerStorage() {
        String startDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

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

        name = new JLabel(CUSTOMER_NAME_DB);
        name.setBounds(70, 80, 180,25);
        panel.add(name);

        JLabel idDesc = new JLabel("ID:");
        idDesc.setBounds(10, 110, 30, 25);
        panel.add(idDesc);

        id = new JLabel(String.valueOf(CUSTOMER_ID_DB));
        id.setBounds(70,110,180,25);
        panel.add(id);

        out = new JLabel("");
        out.setBounds(200, 135, 300, 25);
        panel.add(out);

        JLabel line = new JLabel("_______________________________________________________________________________________");
        line.setBounds(10,150,600,15);
        panel.add(line);

        JLabel selectWare = new JLabel("Select Unit:");
        selectWare.setBounds(10, 190, 120, 25);
        panel.add(selectWare);

        choose = new JComboBox<String>();
        choose.setBounds(140, 190, 87, 25);
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

        start = new JTextField(startDate);
        start.setBounds(140, 250, 150, 25);
        panel.add(start);

        JLabel endDesc = new JLabel("End Date:");
        endDesc.setBounds(10, 280, 100, 25);
        panel.add(endDesc);

        end = new JTextField();
        end.setBounds(140, 280, 150, 25);
        panel.add(end);

        submit = new JButton("Submit");
        submit.setBounds(310,280,80,25);
        panel.add(submit);
        submit.addActionListener(this);

        out1 = new JLabel("");
        out1.setBounds(10, 400, 570, 25);
        panel.add(out1);

        title.setForeground(Color.decode("#222D6D"));
        out.setForeground(Color.decode("#990000"));
        out1.setForeground(Color.decode("#990000"));
        panel.setBackground(Color.decode("#E5F1F6"));
        submit.setForeground(Color.decode("#222D6D"));
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

        ArrayList<String> temp = customerController.currentWareUnit();

        checkMem = customerController.checkMemberNew();

        for (String s : temp) {
            choose.addItem(s);
        }
        checkRent = customerController.checkRent();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String feeCurr = fee.getText();
            String endCurr = end.getText();
            String startCurr = start.getText();
            String cusID = id.getText();
            String unitID = choose.getSelectedItem().toString();
            if (!checkRent.contains(unitID+cusID)) {
                try {
                    if (feeCurr.matches("[0-9]+") && isValidDate(endCurr) && isValidDate(startCurr) && compare(startCurr,endCurr) && customerController.checkMember(unitID, cusID)) {
                        out1.setForeground(Color.decode("#0e6b0e"));
                        out1.setText("Storage for : " + CUSTOMER_NAME_DB + ", ID: " + CUSTOMER_ID_DB + " from " + start.getText() + " to " + end.getText() + " in " + choose.getSelectedItem() + " created ");
                        customerController.addRent(new Rent(unitID, cusID, feeCurr, startCurr, endCurr));
                        checkRent.add(unitID+cusID);
                    } else {
                        out1.setForeground(Color.decode("#990000"));
                        out1.setText("Invalid input, please verify the fields OR Customer is not a Member in selected Warehouse");
                    }
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            } else {
                out1.setForeground(Color.decode("#990000"));
                out1.setText("Customer " + CUSTOMER_NAME_DB+ " already renting at "+ unitID + " no changes done");
            }
        }
    }



    public boolean compare(String start, String end) throws ParseException {
        Date d = new SimpleDateFormat("dd-MM-yyyy").parse(start);
        Date d1 = new SimpleDateFormat("dd-MM-yyyy").parse(end);
        return d.before(d1);
    }



    public boolean isValidDate(String inDate) {
        String check = "";
        for (int i = 6; i < inDate.length(); i++) {
            check += inDate.charAt(i);
        }
        if (Integer.parseInt(check) < 2020) {
            System.out.println(check);
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

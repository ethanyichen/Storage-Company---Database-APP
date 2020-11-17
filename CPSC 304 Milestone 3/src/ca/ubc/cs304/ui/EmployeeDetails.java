package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeDetails implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private int eIDInt;
    private String eName;
    private JButton updateSal;
    private JTextField newSalary;
    private JLabel updateConfirmation;
    private JLabel nameDisplay;
    private JLabel eIDDisplay;

    private int eID;
    private String EMPLOYEE_NAME_DB = "NULL";


    EmployeeDetails(int emID){
        this.eID = emID;
        panel = new JPanel();
        frame = new JFrame("Employee Details");
        frame.add(panel, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(null);

        JLabel title = new JLabel("Employee Details");
        title.setBounds(10,15,500,40);
        Font f = new Font("serif", Font.BOLD, 30);
        title.setFont(f);
        panel.add(title);

        eIDInt=emID;

        //TODO invalid ID ??
        if(eIDInt==-1) {
            JLabel fail = new JLabel("invalid input");
            fail.setBounds(10, 300, 300, 50);
            fail.setFont(new Font(null, Font.PLAIN,30));
            fail.setForeground(Color.decode("#990000"));
            panel.add(fail);

        } else {

        //Display Employee Search Results
        JLabel header = new JLabel ("ID:");
        header.setBounds(460,40,300,25);
        panel.add(header);

        eIDDisplay = new JLabel("");
        eIDDisplay.setBounds(505,40,300,20);
        panel.add(eIDDisplay);

        //TODO add result of search for Name
        JLabel headerName = new JLabel ("Name:");
        headerName.setBounds(460,10,165,25);
        panel.add(headerName);

        nameDisplay = new JLabel("");
        nameDisplay.setBounds(505,10,300,20);
        panel.add(nameDisplay);

        //Horizontal Line3
        JLabel line = new JLabel("_______________________________________________________________________________________");
        line.setBounds(10,60,580,25);
        panel.add(line);

        //TODO display results for manager
        JLabel manages = new JLabel ("Manages: ");
        manages.setBounds(10,100,165,25);
        panel.add(manages);

        //Horizontal Line
        JLabel line1 = new JLabel("_______________________________________________________________________________________");
        line1.setBounds(10,340,580,25);
        panel.add(line1);

        //Update Salary
        JLabel updateS = new JLabel ("Update Salary ");
        updateS.setBounds(10,370,165,25);
        panel.add(updateS);

        JLabel newSalaryLabel = new JLabel ("New Salary ");
        newSalaryLabel.setBounds(10,400,100,25);
        panel.add(newSalaryLabel);

        newSalary = new JTextField();
        newSalary.setBounds(120, 400,180,25);
        panel.add(newSalary);

        updateSal = new JButton("update");
        updateSal.setBounds(310,400,80,25);
        updateSal.addActionListener(this);
        panel.add(updateSal);

        updateConfirmation = new JLabel("");
        updateConfirmation.setBounds(75,430,475,25);
        panel.add(updateConfirmation);

        panel.setBackground(Color.decode("#E5F1F6"));

        title.setForeground(Color.decode("#222D6D"));
        header.setForeground(Color.decode("#222D6D"));
        headerName.setForeground(Color.decode("#222D6D"));
        line.setForeground(Color.decode("#222D6D"));
        manages.setForeground(Color.decode("#222D6D"));
        line1.setForeground(Color.decode("#222D6D"));
        updateS.setForeground(Color.decode("#222D6D"));
        newSalaryLabel.setForeground(Color.decode("#222D6D"));

        updateConfirmation.setForeground(Color.decode("#990000"));


        //othrt nameDisplay eIDDisplay



        //TODO set name and ID to the customer currently in progres
        //nameDisplay.setText(EMPLOYEE_NAME_DB);
        eIDDisplay.setText(Integer.toString(eIDInt));

        frame.pack();
        frame.setSize(600,500);
        frame.setVisible(true);
        }
    }

    public void actionPerformed(ActionEvent e) {
        //update button
        if (e.getSource() == updateSal) {
            String newSal=newSalary.getText();
            int newSalInt = -1;
            String confirmation;
            if (newSal.matches("[0-9]+") && newSal.length() > 0) {
                newSalInt = Integer.parseInt(newSal);
            }
            if (newSalInt==-1)
                confirmation="Invalid input for Salary. Salary for employee (ID):" +eIDInt + " not updated.";
            else
                confirmation = "Salary updated to $" +newSalInt+ " for employee (ID): " +eIDInt ;
            updateConfirmation.setText(confirmation);
            newSalary.setText("");
        }
    }
}

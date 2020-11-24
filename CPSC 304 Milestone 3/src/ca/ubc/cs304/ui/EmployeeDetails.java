package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.CustomerController;
import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.database.EmployeeContoller;
import ca.ubc.cs304.exceptions.EmployeeDeleteException;
import ca.ubc.cs304.exceptions.ServerErrorException;
import ca.ubc.cs304.model.Customer;
import ca.ubc.cs304.model.Employee;
import ca.ubc.cs304.model.Unit;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private JLabel salaryDisplay;
    private JTextArea display;

    private String EMPLOYEE_NAME_DB = "NULL";
    private int EMPLOYEE_ID_DB = -1;
    private EmployeeContoller employeeContoller;
    private Employee employee;
    private DatabaseConnectionHandler db;

    public EmployeeDetails(EmployeeContoller employeeContoller, Employee employee, DatabaseConnectionHandler db) {
        this.employeeContoller = employeeContoller;
        this.employee = employee;
        this.EMPLOYEE_NAME_DB = employee.getEmployeeName();
        this.EMPLOYEE_ID_DB = employee.getEmployeeID();
        this.db = db;
    }

    public void employeeDetails() {
        panel = new JPanel();
        frame = new JFrame("Employee Details");
        frame.add(panel, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(null);

        JLabel title = new JLabel("Employee Details");
        title.setBounds(10, 15, 500, 40);
        Font f = new Font("serif", Font.BOLD, 30);
        title.setFont(f);
        panel.add(title);

        eIDInt = EMPLOYEE_ID_DB;

        //TODO invalid ID ??
        if (eIDInt == -1) {
            JLabel fail = new JLabel("invalid input");
            fail.setBounds(10, 300, 300, 50);
            fail.setFont(new Font(null, Font.PLAIN, 30));
            fail.setForeground(Color.decode("#990000"));
            panel.add(fail);

        } else {

            //Display Employee Search Results
            JLabel header = new JLabel("ID:");
            header.setBounds(320, 25, 300, 25);
            panel.add(header);

            eIDDisplay = new JLabel("");
            eIDDisplay.setBounds(380, 25, 300, 20);
            panel.add(eIDDisplay);

            JLabel headerName = new JLabel("Name:");
            headerName.setBounds(320, 5, 165, 25);
            panel.add(headerName);

            nameDisplay = new JLabel("");
            nameDisplay.setBounds(380, 5, 300, 20);
            panel.add(nameDisplay);

            JLabel headerSalary = new JLabel("Salary ($):");
            headerSalary.setBounds(320, 45, 165, 25);
            panel.add(headerSalary);

            salaryDisplay = new JLabel("");
            salaryDisplay.setBounds(380, 45, 300, 25);
            panel.add(salaryDisplay);

            //Horizontal Line3
            JLabel line = new JLabel("_______________________________________________________________________________________");
            line.setBounds(10, 60, 580, 25);
            panel.add(line);
            
            JLabel manages = new JLabel("Manages: ");
            manages.setBounds(10, 100, 165, 25);
            panel.add(manages);

            JPanel displayPanel = new JPanel();
            displayPanel.setBackground(Color.decode("#E5F1F6"));
            displayPanel.setBounds(10, 130, 580, 200);
            display = new JTextArea(12, 53);
            display.setEditable(false);
            JScrollPane scroll = new JScrollPane(display);
            displayPanel.add(scroll);
            panel.add(displayPanel);

            try {
                EmployeeContoller employeeContoller = new EmployeeContoller(db);
                ArrayList<Unit> unitsManagesList = employeeContoller.allUnitsManaged(EMPLOYEE_ID_DB);
                for (int i = 0; i < unitsManagesList.size(); i++) {
                    Unit curr = unitsManagesList.get(i);
                    display.append("UnitID: " + curr.getUnitID() + " |  Warehouse: " +
                            curr.getWarehouseID() + " |  Customer ID: " + curr.getCustomerID() + " \n");
                }
                if (unitsManagesList.size()==0)
                    display.append("no units currently managed by " + EMPLOYEE_NAME_DB);
            } catch (ServerErrorException serverErrorException) {
                display.append("Server Error");
                serverErrorException.printStackTrace();
            }


            //Horizontal Line
            JLabel line1 = new JLabel("_______________________________________________________________________________________");
            line1.setBounds(10, 340, 580, 25);
            panel.add(line1);

            //Update Salary
            JLabel updateS = new JLabel("Update Salary ");
            updateS.setBounds(10, 370, 165, 25);
            panel.add(updateS);

            JLabel newSalaryLabel = new JLabel("New Salary ");
            newSalaryLabel.setBounds(10, 400, 100, 25);
            panel.add(newSalaryLabel);

            newSalary = new JTextField();
            newSalary.setBounds(120, 400, 180, 25);
            panel.add(newSalary);

            updateSal = new JButton("update");
            updateSal.setBounds(310, 400, 80, 25);
            updateSal.addActionListener(this);
            panel.add(updateSal);

            updateConfirmation = new JLabel("");
            updateConfirmation.setBounds(75, 430, 475, 25);
            panel.add(updateConfirmation);

            panel.setBackground(Color.decode("#E5F1F6"));


            title.setForeground(Color.decode("#222D6D"));
            header.setForeground(Color.decode("#222D6D"));
            headerName.setForeground(Color.decode("#222D6D"));
            headerSalary.setForeground(Color.decode("#222D6D"));
            line.setForeground(Color.decode("#222D6D"));
            manages.setForeground(Color.decode("#222D6D"));
            line1.setForeground(Color.decode("#222D6D"));
            updateS.setForeground(Color.decode("#222D6D"));
            newSalaryLabel.setForeground(Color.decode("#222D6D"));

            updateConfirmation.setForeground(Color.decode("#990000"));


            nameDisplay.setText(EMPLOYEE_NAME_DB);
            eIDDisplay.setText(Integer.toString(eIDInt));
            int salary =-1;
            try{
                EmployeeContoller employeeContoller = new EmployeeContoller(db);
                salary = employeeContoller.getSalary(eIDInt);
            }catch(ServerErrorException serverErrorException){
                serverErrorException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (salary == -1)
                salaryDisplay.setText("No Salary. Update bellow.");
            else
                salaryDisplay.setText(Integer.toString(salary));

            frame.pack();
            frame.setSize(600, 500);
            frame.setVisible(true);
        }
    }


    public void actionPerformed(ActionEvent e) {
        //update button
        if (e.getSource() == updateSal) {
            String newSal= newSalary.getText();
            int newSalInt = -1;
            String confirmation;
            if (newSal.matches("[0-9]+") && newSal.length() > 0) {
                newSalInt = Integer.parseInt(newSal);
            }
            if (newSalInt<0) {
                confirmation = "Invalid input for Salary. Salary for employee (ID):" + eIDInt + " not updated.";
                updateConfirmation.setForeground(Color.decode("#990000"));
            }
            else {
                confirmation = "Salary updated to $" + newSalInt + " for employee: " + EMPLOYEE_NAME_DB;
                updateConfirmation.setForeground(Color.decode("#0e6b0e"));
                try{
                    EmployeeContoller employeeContoller = new EmployeeContoller(db);
                    employeeContoller.updateSalary(eIDInt,newSalInt);
                }catch(ServerErrorException serverErrorException){
                    serverErrorException.printStackTrace();
                }
            }
            updateConfirmation.setText(confirmation);
            newSalary.setText("");
            if (newSalInt>-1)
                salaryDisplay.setText(Integer.toString(newSalInt));
        }
    }
}

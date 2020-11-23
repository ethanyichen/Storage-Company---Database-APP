package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.CustomerController;
import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.database.EmployeeContoller;
import ca.ubc.cs304.exceptions.CustomerSearchException;
import ca.ubc.cs304.exceptions.EmployeeDeleteException;
import ca.ubc.cs304.exceptions.EmployeeSearchException;
import ca.ubc.cs304.exceptions.ServerErrorException;
import ca.ubc.cs304.model.Customer;
import ca.ubc.cs304.model.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


public class EmployeeManagement implements ActionListener {
    private int count = 0;
    private JLabel title;
    private JFrame eFrame;
    private JLabel eLabel;
    private JPanel ePanel;
    private JTextField eName;
    private JTextField eSalary;
    private JButton addB;
    private JLabel addConfirmation;
    private JLabel addConfirmationID;
    private JButton searchB;
    private JTextField eID;
    private JLabel searchError;
    private JButton deleteB;
    private JTextField eIDDel;
    private JLabel deleteConfirmation;
    private JButton eDirec;
    private JLabel searchT;
    private JLabel search;
    private JLabel line;
    private  JLabel addT;
    private JLabel addE;
    private  JLabel addES;
    private JLabel line1;
    private  JLabel deleteE;
    private JLabel deleteEName;
    private JComboBox<String> cb;

    private Color submitMsgColorRed = Color.decode("#990000");
    private Color submitMsgColorGreen = Color.decode("#0e6b0e");

    private DatabaseConnectionHandler db;
    public EmployeeManagement(DatabaseConnectionHandler db) {
        this.db = db;
    }

    public void employeeManagement() {

        ePanel = new JPanel();
        eFrame = new JFrame("Employee Management");
        eFrame.add(ePanel, BorderLayout.CENTER);

        ePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        ePanel.setLayout(null);

        title = new JLabel("Employee Management");
        title.setBounds(120,15,400,40);
        Font f = new Font("serif", Font.BOLD, 30);
        title.setFont(f);
        ePanel.add(title);


        //Search Employee
        searchT = new JLabel("Search Employee");
        searchT.setBounds(10,60,150,25);
        ePanel.add(searchT);

        search = new JLabel("EmployeeID");
        search.setBounds(10,90,80,25);
        ePanel.add(search);

        eID = new JTextField();
        eID.setBounds(120,90,180,25);
        ePanel.add(eID);

        searchB = new JButton("Search");
        searchB.setBounds(310,90,80,25);
        //change on submit
        searchB.addActionListener(this);
        ePanel.add(searchB);
        eLabel = new JLabel();
        ePanel.add(eLabel);

        searchError = new JLabel("");
        searchError.setBounds(170,110,300,25);
        ePanel.add(searchError);

        //Horizontal Line
        line = new JLabel("_______________________________________________________________________________________");
        line.setBounds(10,120,580,25);
        ePanel.add(line);

        //Add Employee
        addT = new JLabel("Add Employee");
        addT.setBounds(10,150,165,25);
        ePanel.add(addT);

        addE = new JLabel("Employee Name");
        addE.setBounds(10,180,100,25);
        ePanel.add(addE);

        eName = new JTextField();
        eName.setBounds(120,180,180,25);
        ePanel.add(eName);

        addES = new JLabel("Salary ($)");
        addES.setBounds(315,180,70,25);
        ePanel.add(addES);

        eSalary = new JTextField();
        eSalary.setBounds(385,180,120,25);
        ePanel.add(eSalary);

        addES = new JLabel("Warehouse");
        addES.setBounds(200,210,70,25);
        ePanel.add(addES);

        cb = new JComboBox<String>();
        try {
            EmployeeContoller employeeController = new EmployeeContoller(db);
            ArrayList<String> warehouses = employeeController.currentWarehouse();
            for (String s : warehouses)
                cb.addItem(s);
        } catch (ServerErrorException e) {
            e.printStackTrace();
        }
        cb.setVisible(true);
        cb.setBounds(270,210,120,25);
        ePanel.add(cb);

        addB = new JButton("Add");
        addB.setBounds(245,250,80,25);
        addB.addActionListener(this);
        ePanel.add(addB);

        addConfirmation = new JLabel("");
        addConfirmation.setBounds(75,280,475,25);
        ePanel.add(addConfirmation);

        addConfirmationID = new JLabel("");
        addConfirmationID.setBounds(150,310,200,25);
        ePanel.add(addConfirmationID);

        //Horizontal Line
        line1 = new JLabel("_______________________________________________________________________________________");
        line1.setBounds(10,320,580,25);
        ePanel.add(line1);

        //Delete employee
        deleteE = new JLabel("Delete Employee");
        deleteE.setBounds(10,340,165,25);
        ePanel.add(deleteE);

        deleteEName = new JLabel("Employee ID");
        deleteEName.setBounds(10,370,100,25);
        ePanel.add(deleteEName);

        eIDDel = new JTextField();
        eIDDel.setBounds(120,370,180,25);
        ePanel.add(eIDDel);

        deleteB  = new JButton("Delete");
        deleteB.setBounds(310,370,80,25);
        deleteB.addActionListener(this);
        ePanel.add(deleteB);

        deleteConfirmation = new JLabel("");
        deleteConfirmation.setBounds(120,400,400,25);
        ePanel.add(deleteConfirmation);

        eDirec = new JButton("Employee Directories");
        eDirec.setBounds(150,430,300,25);
        eDirec.addActionListener(this);
        ePanel.add(eDirec);

        ePanel.setBackground(Color.decode("#E5F1F6"));

        title.setForeground(Color.decode("#222D6D"));
        deleteB.setForeground(Color.decode("#222D6D"));
        eDirec.setForeground(Color.decode("#222D6D"));
        searchB.setForeground(Color.decode("#222D6D"));
        addB.setForeground(Color.decode("#222D6D"));
        search.setForeground(Color.decode("#222D6D"));
        searchT.setForeground(Color.decode("#222D6D"));
        line.setForeground(Color.decode("#222D6D"));
        addT.setForeground(Color.decode("#222D6D"));
        addE.setForeground(Color.decode("#222D6D"));
        addES.setForeground(Color.decode("#222D6D"));
        line1.setForeground(Color.decode("#222D6D"));
        deleteE.setForeground(Color.decode("#222D6D"));
        deleteEName.setForeground(Color.decode("#222D6D"));
        deleteConfirmation.setForeground(Color.decode("#222D6D"));
        searchError.setForeground(Color.decode("#222D6D"));
        eLabel.setForeground(Color.decode("#222D6D"));


        addConfirmation.setForeground(submitMsgColorRed);
        searchError.setForeground(submitMsgColorRed);
        deleteConfirmation.setForeground(submitMsgColorRed);
        addConfirmationID.setForeground(submitMsgColorGreen);


        eFrame.pack();
        eFrame.setSize(600,500);
        eFrame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        //search button
        if (e.getSource() == searchB) {
            searchError.setText("");
            int eID_INT = -1;
            String eID_TEXT = eID.getText();
            if (eID_TEXT.matches("[0-9]+") && eID_TEXT.length() > 0) {
                eID_INT = Integer.parseInt(eID_TEXT);
            }
            try {
                EmployeeContoller employeeController = new EmployeeContoller(db);
                if (eID_INT == -1) {
                    searchError.setText("Invalid input");
                } else {
                    Employee employee = employeeController.searchEmployee(eID_INT);
                    eID.setText("");
                    EmployeeDetails employeeDetails = new EmployeeDetails(employeeController,employee,db);
                    employeeDetails.employeeDetails();
                }
            } catch (EmployeeSearchException exception) {
                searchError.setText("Employee Not Found OR Duplicate Record");
                exception.printStackTrace();
            } catch (ServerErrorException serverErrorException){
                searchError.setText("Server Error");
                serverErrorException.printStackTrace();
            }
        }

        //add button
        if (e.getSource() == addB) {
            String eNameIn = eName.getText();
            String eSalaryString = eSalary.getText();
            String warehouseName = String.valueOf(cb.getSelectedItem());
            int warehouseID=-1;
            try {
                EmployeeContoller employeeController = new EmployeeContoller(db);
                warehouseID = employeeController.currentWarehouseID(warehouseName);
            } catch (ServerErrorException err) {
                err.printStackTrace();
            }
            int eSalaryIn=-1;
            if (eSalaryString.matches("[0-9]+") && eSalaryString.length() > 0) {
                eSalaryIn= Integer.parseInt(eSalaryString);
            }
            String addMsg;
            String addMsgID;
            if (eNameIn.equals("")|eSalaryIn==-1) {
                addMsg = "Unsuccessful. Missing Fields or incorrect input. no EmployeeID generated.";
                addMsgID="";
                addConfirmation.setForeground(submitMsgColorRed);

            }else {
                addMsg = "Employee \"" + eNameIn + "\" with salary $" + eSalaryIn + " successfully added.";
                addConfirmation.setForeground(submitMsgColorGreen);
                Random rand = new Random();
                //todo repeated ID
                int generatedEID = rand.nextInt(999);
                addMsgID = "EmployeeID Assigned = " + generatedEID;
                try{
                    EmployeeContoller employeeContoller = new EmployeeContoller(db);
                    employeeContoller.addEmployee(new Employee(generatedEID, eNameIn, warehouseID, eSalaryIn));
                }catch(ServerErrorException serverErrorException){
                    serverErrorException.printStackTrace();
                }
            }
            addConfirmation.setText(addMsg);
            eName.setText("");
            eSalary.setText("");
            addConfirmationID.setText(addMsgID);
        }

        //delete button
        if (e.getSource() == deleteB) {
            String eIDin_STRING = eIDDel.getText();
            int eIDin=-1;
            if (eIDin_STRING.matches("[0-9]+") && eIDin_STRING.length() > 0) {
                eIDin= Integer.parseInt(eIDin_STRING);
            }
            String deleteMsg;
            if(eIDin==-1) {
                deleteMsg = "Invalid input";
                deleteConfirmation.setForeground(submitMsgColorRed);
            }
            else {
                deleteMsg = "Employee with ID " + eIDin + " successfully deleted from Database.";
                deleteConfirmation.setForeground(submitMsgColorGreen);
                try {
                    EmployeeContoller employeeContoller = new EmployeeContoller(db);
                    employeeContoller.delete(eIDin);
                } catch (EmployeeDeleteException exception) {
                    deleteMsg = "Invalid input. Customer with id " + eIDin + " does not exist";
                    deleteConfirmation.setForeground(submitMsgColorRed);
                    exception.printStackTrace();
                } catch (ServerErrorException serverErrorException) {
                    serverErrorException.printStackTrace();
                }
            }
            deleteConfirmation.setText(deleteMsg);
            eIDDel.setText("");
        }

        //directory button
        if (e.getSource() == eDirec) {
            Directory directory = new Directory("Employee Directories",db);
            directory.directory();
        }

    }
}




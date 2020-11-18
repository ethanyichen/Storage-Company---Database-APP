package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
    private JTextField eNameDel;
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
        searchError.setBounds(170,110,200,25);
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

        addB = new JButton("Add");
        addB.setBounds(245,210,80,25);
        addB.addActionListener(this);
        ePanel.add(addB);

        addConfirmation = new JLabel("");
        addConfirmation.setBounds(75,240,475,25);
        ePanel.add(addConfirmation);

        addConfirmationID = new JLabel("");
        addConfirmationID.setBounds(150,270,200,25);
        ePanel.add(addConfirmationID);

        //Horizontal Line
        line1 = new JLabel("_______________________________________________________________________________________");
        line1.setBounds(10,280,580,25);
        ePanel.add(line1);

        //Delete employee
        deleteE = new JLabel("Delete Employee");
        deleteE.setBounds(10,300,165,25);
        ePanel.add(deleteE);

        deleteEName = new JLabel("Employee Name");
        deleteEName.setBounds(10,330,100,25);
        ePanel.add(deleteEName);

        eNameDel = new JTextField();
        eNameDel.setBounds(120,330,180,25);
        ePanel.add(eNameDel);

        deleteB  = new JButton("Delete");
        deleteB.setBounds(310,330,80,25);
        deleteB.addActionListener(this);
        ePanel.add(deleteB);

        deleteConfirmation = new JLabel("");
        deleteConfirmation.setBounds(120,360,400,25);
        ePanel.add(deleteConfirmation);

        eDirec = new JButton("Employee Directories");
        eDirec.setBounds(150,410,300,25);
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
            int emID = -1;
            String stringID = eID.getText();
            if (stringID.matches("[0-9]+") && stringID.length() > 0) {
                emID = Integer.parseInt(stringID);
            }
            EmployeeDetails employeeDetails = new EmployeeDetails(emID);
            //TODO deal with fail
            if (emID == -1) {
                searchError.setText("Invalid input");
            }
            eID.setText("");
        }

        //add button
        if (e.getSource() == addB) {
            String eNameIn = eName.getText();
            String eSalaryString = eSalary.getText();
            int eSalaryIn=-1;
            if (eSalaryString.matches("[0-9]+") && eSalaryString.length() > 0) {
                eSalaryIn= Integer.parseInt(eSalaryString);
            }
            //TODO generate new EmployeeID, how to deal with duplicated adds
            String addMsg;
            String addMsgID;
            if (eNameIn.equals("")|eSalaryIn==-1) {
                addMsg = "Unsuccessful. Missing Fields or incorrect input. no EmployeeID generated.";
                addMsgID="";
                addConfirmation.setForeground(submitMsgColorRed);

            }else {
                addMsg = "Employee \"" + eNameIn + "\" with salary $" + eSalaryIn + " successfully added.";
                addConfirmation.setForeground(submitMsgColorGreen);
                addMsgID = "EmployeeID Assigned = ...";
            }
            addConfirmation.setText(addMsg);
            eName.setText("");
            eSalary.setText("");
            addConfirmationID.setText(addMsgID);
        }

        //delete button
        //TODO fail case
        if (e.getSource() == deleteB) {
            String eNameIn = eNameDel.getText();
            String deleteMsg;
            if(eNameIn.equals("")) {
                deleteMsg = "Invalid input";
                deleteConfirmation.setForeground(submitMsgColorRed);
            }
            else{
                deleteMsg="Employee \"" +eNameIn+ "\" successfully deleted from Database.";
                deleteConfirmation.setForeground(submitMsgColorGreen);
            }
            deleteConfirmation.setText(deleteMsg);
            eNameDel.setText("");
        }

        //directory button
        if (e.getSource() == eDirec) {
            Directory directory = new Directory("Employee Directories");
            directory.directory();
        }

    }
}




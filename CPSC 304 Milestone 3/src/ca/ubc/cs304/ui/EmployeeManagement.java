import com.sun.jdi.IntegerValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EmployeeManagement implements ActionListener {
    private int count = 0;
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


    public EmployeeManagement() {

        ePanel = new JPanel();
        eFrame = new JFrame("Employee Management");
        eFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        eFrame.add(ePanel, BorderLayout.CENTER);

        ePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        ePanel.setLayout(null);

        //Search Employee
        JLabel searchT = new JLabel("Search Employee");
        searchT.setBounds(10,50,165,25);
        ePanel.add(searchT);

        JLabel search = new JLabel("EmployeeID");
        search.setBounds(10,80,80,25);
        ePanel.add(search);

        eID = new JTextField();
        eID.setBounds(120,80,180,25);
        ePanel.add(eID);

        searchB = new JButton("Search");
        searchB.setBounds(310,80,80,25);
        //change on submit
        searchB.addActionListener(this);
        ePanel.add(searchB);
        eLabel = new JLabel();
        ePanel.add(eLabel);

        searchError = new JLabel("");
        searchError.setBounds(150,100,200,25);
        ePanel.add(searchError);

        //Horizontal Line
        JLabel line = new JLabel("_______________________________________________________________________________________");
        line.setBounds(10,120,580,25);
        ePanel.add(line);

        //Add Employee
        JLabel addT = new JLabel("Add Employee");
        addT.setBounds(10,140,165,25);
        ePanel.add(addT);

        JLabel addE = new JLabel("Employee Name");
        addE.setBounds(10,170,100,25);
        ePanel.add(addE);

        eName = new JTextField();
        eName.setBounds(120,170,180,25);
        ePanel.add(eName);

        JLabel addES = new JLabel("Salary ($)");
        addES.setBounds(315,170,70,25);
        ePanel.add(addES);

        eSalary = new JTextField();
        eSalary.setBounds(385,170,120,25);
        ePanel.add(eSalary);

        addB = new JButton("Add");
        addB.setBounds(245,200,80,25);
        addB.addActionListener(this);
        ePanel.add(addB);

        addConfirmation = new JLabel("");
        addConfirmation.setBounds(75,230,475,25);
        ePanel.add(addConfirmation);

        addConfirmationID = new JLabel("");
        addConfirmationID.setBounds(150,260,200,25);
        ePanel.add(addConfirmationID);

        //Horizontal Line
        JLabel line1 = new JLabel("_______________________________________________________________________________________");
        line1.setBounds(10,270,580,25);
        ePanel.add(line1);

        //Delete employee
        JLabel deleteE = new JLabel("Delete Employee");
        deleteE.setBounds(10,300,165,25);
        ePanel.add(deleteE);

        JLabel deleteEName = new JLabel("Employee Name");
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
        deleteConfirmation.setBounds(100,360,400,25);
        ePanel.add(deleteConfirmation);

        eDirec = new JButton("Employee Directories");
        eDirec.setBounds(150,410,300,25);
        eDirec.addActionListener(this);
        ePanel.add(eDirec);


        eFrame.pack();
        eFrame.setSize(600,500);
        eFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new EmployeeManagement();
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
            }else {
                addMsg = "Employee \"" + eNameIn + "\" with salary $" + eSalaryIn + " successfully added.";
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
            if(eNameIn.equals(""))
                deleteMsg="Invalid input";
            else
                deleteMsg="Employee \"" +eNameIn+ "\" successfully deleted from Database.";
            deleteConfirmation.setText(deleteMsg);
            eNameDel.setText("");
        }

        //directory button
        if (e.getSource() == eDirec) {
            System.out.print(1);
            EmployeeDirectory employeeDirectory = new EmployeeDirectory();
        }

    }
}




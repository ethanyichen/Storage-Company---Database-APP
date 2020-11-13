
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

    EmployeeDetails(int emID){
        panel = new JPanel();
        frame = new JFrame("Employee Details");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(null);

        eIDInt=emID;

        //TODO invalid ID ??
        if(eIDInt==-1) {
            JLabel fail = new JLabel("invalid input");
            fail.setBounds(10, 300, 300, 50);
            fail.setFont(new Font(null, Font.PLAIN,30));
            panel.add(fail);
        } else {

        //Display Employee Search Results
        JLabel header = new JLabel ("Window for employee ID: " + eIDInt);
        header.setBounds(10,10,300,25);
        panel.add(header);

        //TODO add result of search for Name
        JLabel headerName = new JLabel ("                               Name: ");
        headerName.setBounds(10,40,165,25);
        panel.add(headerName);

        //Horizontal Line
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

        frame.pack();
        frame.setSize(600,500);
        frame.setVisible(true);

    }}

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
        }
    }
}

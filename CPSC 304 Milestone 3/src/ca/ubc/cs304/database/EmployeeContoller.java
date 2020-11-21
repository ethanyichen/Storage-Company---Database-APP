package ca.ubc.cs304.database;

import ca.ubc.cs304.exceptions.CustomerSearchException;
import ca.ubc.cs304.exceptions.EmployeeSearchException;
import ca.ubc.cs304.exceptions.ServerErrorException;
import ca.ubc.cs304.model.Customer;
import ca.ubc.cs304.model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeContoller extends Controller {
    private Connection connection;

    public EmployeeContoller(DatabaseConnectionHandler db) throws ServerErrorException {
        super(db);
        connection = super.connection;
    }


    public Employee searchEmployee(int employeeID) throws EmployeeSearchException {
        int ID = -1;
        String name = "";
        int warehouseID = -1;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee WHERE employeeID = " + employeeID);
            if(rs.getRow()>1){
                throw new EmployeeSearchException(); //multiple employee with same ID found
            }
            if(rs.next()) {
                ID = rs.getInt("employeeID");
                name = rs.getString("eName");
                warehouseID = rs.getInt("warehouseID");

            } else{
                throw new EmployeeSearchException(); // employee not found
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            super.rollbackConnection();
        }
        return new Employee(ID,name,warehouseID);
    }

}

package ca.ubc.cs304.database;

import ca.ubc.cs304.exceptions.CustomerSearchException;
import ca.ubc.cs304.exceptions.EmployeeSearchException;
import ca.ubc.cs304.exceptions.EmployeeDeleteException;
import ca.ubc.cs304.exceptions.ServerErrorException;
import ca.ubc.cs304.model.Customer;
import ca.ubc.cs304.model.Employee;

import java.sql.*;

public class EmployeeContoller extends Controller {
    private Connection connection;

    public EmployeeContoller(DatabaseConnectionHandler db) throws ServerErrorException {
        super(db);
        connection = super.connection;
    }

    public void addEmployee(Employee em) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Employee(employeeID,eName,warehouseID) VALUES (?,?,?)");
            ps.setInt(1, em.getEmployeeID());
            ps.setString(2, em.getEmployeeName());
            ps.setInt(3, em.getHiringWarehouseID());
            ps.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("INSERT INTO employeeSalary(employeeID, salary) VALUES (?,?)");
            ps2.setInt(1, em.getEmployeeID());
            ps2.setInt(2, em.getSalary());
            ps2.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            super.rollbackConnection();
        }
    }

    public void delete(int eID) throws EmployeeDeleteException{
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee WHERE employeeID = " + eID);
            if(rs.getRow()==0){
                throw new EmployeeDeleteException(); //no employee with ID
            }
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Employee WHERE employeeID= " + eID);
            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            super.rollbackConnection();
        }
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
        return new Employee(ID,name,warehouseID,0);
    }

}

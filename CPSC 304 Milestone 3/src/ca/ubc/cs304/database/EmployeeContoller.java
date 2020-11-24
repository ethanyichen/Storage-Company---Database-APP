package ca.ubc.cs304.database;

import ca.ubc.cs304.exceptions.EmployeeSearchException;
import ca.ubc.cs304.exceptions.EmployeeDeleteException;
import ca.ubc.cs304.exceptions.ServerErrorException;
import ca.ubc.cs304.model.Employee;
import ca.ubc.cs304.model.Unit;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeContoller extends Controller {
    private Connection connection;
    public EmployeeContoller(DatabaseConnectionHandler db) throws ServerErrorException {
        super(db);
        connection = super.connection;
    }

    public ArrayList<String> currentWarehouse() {
        Statement stmt = null;
        ArrayList<String> listWarehouse = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT wName FROM Warehouse");
            while(rs.next()) {
                listWarehouse.add(rs.getString("wName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listWarehouse;
    }

    public int getSalary(int eIDInt) throws SQLException {
        int salary= -1;
        try{ Statement stmt = null;
            stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT salary  FROM employeeSalary");
            if(rs.next()){
                salary = rs.getInt("salary");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salary;
    }

    public ArrayList<Unit> allUnitsManaged(int EmployeeID) {
        Statement stmt = null;
        ArrayList<Unit> list = new ArrayList<>();
        try {
            stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *  FROM Unit WHERE employeeID =" + EmployeeID);
            while(rs.next()) {
                list.add(new Unit(rs.getInt("UnitID"),
                        rs.getInt("WarehouseID")));}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int currentWarehouseID(String name) {
        Statement stmt = null;
        int id =-1;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT wName, warehouseID FROM Warehouse");
            while(rs.next()) {
                String tempS = rs.getString("wname");
                int temp = rs.getInt("warehouseID");
                if (tempS.equals(name))
                    id = temp;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
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
            if(!rs.next()){
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

    public void updateSalary(int employeeID, int newSalary){
           try {
               Statement stmt = connection.createStatement();
               PreparedStatement ps = connection.prepareStatement("UPDATE EmployeeSalary SET Salary= " + newSalary + "WHERE employeeID= "+ employeeID);
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

    public ArrayList<Employee> allEmployee() {
        Statement stmt = null;
        ArrayList<Employee> list = new ArrayList<>();
        try {
            stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *  FROM Employee");
            int eID;
            while(rs.next()) {
                list.add(new Employee(rs.getInt("employeeID"), rs.getString("eName"), rs.getInt("warehouseID"), -1) );
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }
        return list;
    }

}

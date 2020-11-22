package ca.ubc.cs304.database;

import ca.ubc.cs304.exceptions.CustomerSearchException;
import ca.ubc.cs304.exceptions.ServerErrorException;
import ca.ubc.cs304.model.Customer;
import ca.ubc.cs304.ui.CustomerManagement;

import java.sql.*;
import java.util.HashMap;
import java.util.Hashtable;

public class CustomerController extends Controller {
    private Connection connection;
    private Integer customerID;

    public CustomerController(DatabaseConnectionHandler db) throws ServerErrorException {
        super(db);
        connection = super.connection;
    }

    //TODO
    public void addCustomer(Customer c) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?)");
            ps.setInt(1, c.getCustomerID());
            ps.setString(2, c.getCustomerName());
            ps.setString(3, c.getCustomerPhoneNum());
            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            super.rollbackConnection();
        }
    }

    public Customer searchCustomer(int customerID) throws CustomerSearchException {
        this.customerID = customerID;
        int ID = -1;
        String name = "";
        String phoneNum = "";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customer WHERE customerID = " + customerID);
            if(rs.getRow()>1){
                throw new CustomerSearchException(); //multiple customer with same ID found
            }
            if(rs.next()) {
                ID = rs.getInt("customerID");
                name = rs.getString("cName");
                phoneNum = rs.getString("phoneNum");
            } else{
                throw new CustomerSearchException(); // customer not found
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            super.rollbackConnection();
        }
        return new Customer(ID,name,phoneNum);
    }


    public HashMap<Integer,Integer> currentStorageCount(){
        Statement stmt = null;
        HashMap <Integer,Integer> ret = new HashMap<>();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT unitID,COUNT(*) FROM Box WHERE customerID = " + customerID + " GROUP BY unitID");
            if(rs.next()) {
                ret.put(rs.getInt("unitID"),rs.getInt(2));
                return ret;
            } else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

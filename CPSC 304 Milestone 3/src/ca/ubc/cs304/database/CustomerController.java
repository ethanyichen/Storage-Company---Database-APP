package ca.ubc.cs304.database;

import ca.ubc.cs304.exceptions.CustomerSearchException;
import ca.ubc.cs304.model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerController extends Controller {
    private Connection connection;

    public CustomerController(DatabaseConnectionHandler db) {
        super(db);
        connection = super.connection;
    }

    private void addCustomer(){

    }

    public Customer searchCustomer(int customerID) throws CustomerSearchException {
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
}

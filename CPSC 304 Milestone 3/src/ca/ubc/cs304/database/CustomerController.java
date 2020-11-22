package ca.ubc.cs304.database;

import ca.ubc.cs304.exceptions.CustomerSearchException;
import ca.ubc.cs304.exceptions.ServerErrorException;
import ca.ubc.cs304.model.Box;
import ca.ubc.cs304.model.Customer;
import ca.ubc.cs304.model.Membership;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomerController extends Controller {
    private Connection connection;
    private Integer customerID;

    public CustomerController(DatabaseConnectionHandler db) throws ServerErrorException {
        super(db);
        connection = super.connection;
    }

    public void addMember(Membership m) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Member VALUES (?,?,?)");
            ps.setString(1, m.getWarehouseID());
            ps.setString(2, m.getCustomerID());
            ps.setString(3, m.getMembershipStartDate());
            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            super.rollbackConnection();
        }
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
            ResultSet rs = stmt.executeQuery("SELECT unitID,COUNT(*) FROM Box WHERE customerID = " + customerID +
                    " GROUP BY unitID ORDER BY unitID ASC");
            while(rs.next()) {
                ret.put(rs.getInt("unitID"),rs.getInt("COUNT(*)"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ArrayList<Box> currentStorage(){
        Statement stmt = null;
        ArrayList<Box> boxList = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Box WHERE customerID = " + customerID);
            while(rs.next()) {
                boxList.add(new Box(rs.getInt("boxID"),rs.getInt("unitID"),
                        rs.getInt("customerID"), rs.getInt("boxSize")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boxList;
    }

    public int[] maxAvgSizeInAllUnit(){
        Statement stmt = null;
        int[] unitIDAndMaxAvg= {-1,-1};
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("WITH Temp(unitID, averageSize) " +
                    "AS (SELECT unitID, AVG(boxSize) FROM Box GROUP BY unitID)\n" +
                    "SELECT Temp.unitID, Temp.averageSize FROM Temp WHERE Temp.averageSize = (SELECT MAX(Temp.averageSize) FROM Temp)");
            while(rs.next()) {
                unitIDAndMaxAvg[0] = rs.getInt("unitID");
                unitIDAndMaxAvg[1] = rs.getInt("averageSize");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unitIDAndMaxAvg;
    }

    public ArrayList<Membership> currentMemberShip(){
        Statement stmt = null;
        ArrayList<Membership> listOfMemberShip = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Member " +
                    "WHERE customerID = " + customerID);
            while(rs.next()) {
                listOfMemberShip.add(new Membership(String.valueOf(rs.getInt("warehouseID")), String.valueOf(customerID),
                        rs.getString("MEMBERSHIPSTARTDATE")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfMemberShip;
    }

    public ArrayList<String> currentWarehouse() {
        Statement stmt = null;
        ArrayList<String> listWarehouse = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT warehouseID FROM Warehouse");
            while(rs.next()) {
                listWarehouse.add(rs.getString("wareHouseID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listWarehouse;
    }

    public ArrayList<Customer> allCustomer() {
        Statement stmt = null;
        ArrayList<Customer> listOfCustomer = new ArrayList<>();

        try {
            stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *  FROM Customer");

            while(rs.next()) {
                listOfCustomer.add(new Customer(rs.getInt("customerID"),
                        rs.getString("cName"), rs.getString("phoneNum")));
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return listOfCustomer;
    }

    public ArrayList<Customer> allActiveCustomer() {
        Statement stmt = null;
        ArrayList<Integer> listOfCustomerID = new ArrayList<>();
        ArrayList<Customer> listOfCustomer = new ArrayList<>();

        try {
            stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT c.customerID  FROM Customer c, Box b WHERE c.customerID = b.customerID " +
                    "GROUP BY c.customerID HAVING COUNT(*) > 3");

            while(rs.next()) {
                listOfCustomerID.add(rs.getInt("customerID"));
            }
            stmt = this.connection.createStatement();
            for (int next : listOfCustomerID) {
                ResultSet rs2 = stmt.executeQuery("SELECT *  FROM Customer WHERE customerID = " + next);
                while(rs2.next()) {
                    listOfCustomer.add(new Customer(rs2.getInt("customerID"),
                            rs2.getString("cName"), rs2.getString("phoneNum")));
                }
            }

        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return listOfCustomer;
    }

    public ArrayList<Customer> customerOfAllMember() {
        Statement stmt = null;
        ArrayList<Integer> listOfCustomerID = new ArrayList<>();
        ArrayList<Customer> listOfCustomer = new ArrayList<>();

        try {
            stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT c.customerID  FROM Customer c " +
                    "WHERE NOT EXISTS  (SELECT w.warehouseID   " +
                    "FROM Warehouse w WHERE NOT EXISTS " +
                    "(SELECT m.warehouseID FROM Member m " +
                    "WHERE m.customerID = c.customerID AND w.WAREHOUSEID = m.WAREHOUSEID))"
   );

            while(rs.next()) {
                listOfCustomerID.add(rs.getInt("customerID"));
            }
            stmt = this.connection.createStatement();
            for (int next : listOfCustomerID) {
                ResultSet rs2 = stmt.executeQuery("SELECT *  FROM Customer WHERE customerID = " + next);
                while(rs2.next()) {
                    listOfCustomer.add(new Customer(rs2.getInt("customerID"),
                            rs2.getString("cName"), rs2.getString("phoneNum")));
                }
            }

        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return listOfCustomer;
    }
}

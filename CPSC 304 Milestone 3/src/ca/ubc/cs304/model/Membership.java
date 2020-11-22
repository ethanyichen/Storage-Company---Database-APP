package ca.ubc.cs304.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Membership {

    private Connection connection;
    private String warehouseID;
    private String customerID;
    private String membershipStartDate;

    public Membership(String warehouseID, String customerID, String membershipStartDate) {
        this.warehouseID = warehouseID;
        this.customerID = customerID;
        this.membershipStartDate = membershipStartDate;
    }

    public String getWarehouseID() {
        return this.warehouseID;
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public String getMembershipStartDate() {
        return  this.membershipStartDate;
    }

}

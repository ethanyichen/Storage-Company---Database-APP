package ca.ubc.cs304.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Membership {

    private Connection connection;
    private Integer warehouseID;
    private Integer customerID;
    private String membershipStartDate;

    public Membership(Integer warehouseID, Integer customerID, String membershipStartDate) {
        this.warehouseID = warehouseID;
        this.customerID = customerID;
        this.membershipStartDate = membershipStartDate;
    }

    public Integer getWarehouseID() {
        return this.warehouseID;
    }

    public Integer getCustomerID() {
        return this.customerID;
    }

    public String getMembershipStartDate() {
        return  this.membershipStartDate;
    }

}

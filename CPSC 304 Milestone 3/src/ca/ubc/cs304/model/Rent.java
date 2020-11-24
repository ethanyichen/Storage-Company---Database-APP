package ca.ubc.cs304.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Rent {

    private String unitID;
    private String customerID;
    private String monthlyFee;
    private String startDate;
    private String endDate;

    public Rent(String unitID, String customerID, String monthlyFee, String startDate, String endDate) {
        this.unitID = unitID;
        this.customerID = customerID;
        this.monthlyFee = monthlyFee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getUnitID() {
        return this.unitID;
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public String getMonthlyFee() {
        return this.monthlyFee;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }
}

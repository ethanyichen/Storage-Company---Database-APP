package ca.ubc.cs304.model;

public class Customer {
    private Integer customerID;
    private String customerName;
    private String customerPhoneNum;

    public Customer(Integer customerID, String customerName, String customerPhoneNum) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerPhoneNum = customerPhoneNum;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhoneNum() {
        return customerPhoneNum;
    }

}

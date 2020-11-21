package ca.ubc.cs304.model;

public class Employee {
    private Integer employeeID;
    private String employeeName;
    private Integer hiringWarehouseID;


    public Employee(Integer customerID, String customerName, Integer hiringWarehouseID) {
        this.employeeID = customerID;
        this.employeeName = customerName;
        this.hiringWarehouseID = hiringWarehouseID;
    }

    public Integer getHiringWarehouseID() {
        return hiringWarehouseID;
    }

    public void setHiringWarehouseID(Integer hiringWarehouseID) {
        this.hiringWarehouseID = hiringWarehouseID;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }
}

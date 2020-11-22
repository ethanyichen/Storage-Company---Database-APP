package ca.ubc.cs304.model;

public class Employee {
    private Integer employeeID;
    private String employeeName;
    private Integer hiringWarehouseID;
    private Integer salary;
   // private Integer employeeSalary;


    public Employee(Integer customerID, String customerName, Integer hiringWarehouseID, Integer salary) {
        this.employeeID = customerID;
        this.employeeName = customerName;
        this.salary=salary;
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
   // public Integer getHiringWarehouseID() {return hiringWarehouseID;}
}

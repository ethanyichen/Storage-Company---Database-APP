package ca.ubc.cs304.model;

public class Employee {
    private Integer employeeID;
    private String employeeName;
    private Integer hiringWarehouseID;
    private Integer salary;



    public Employee(Integer employeeID, String employeeName, Integer hiringWarehouseID, Integer salary) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.salary=salary;
        this.hiringWarehouseID = hiringWarehouseID;
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
    public Integer getHiringWarehouseID() {
        return hiringWarehouseID;
    }
    public Integer getSalary() {
        return salary;
    }

}

package ca.ubc.cs304.model;

public class Unit {
    private Integer UnitID;
    private Integer WarehouseID;
    private Integer CustomerID;

    public Unit(Integer UnitID, Integer WarehouseID, Integer CustomerID) {
        this.UnitID = UnitID;
        this.WarehouseID = WarehouseID;
        this.CustomerID = CustomerID;
    }

    public Integer getUnitID() {
        return UnitID;
    }

    public Integer getWarehouseID() {
        return WarehouseID;
    }

    public Integer getCustomerID() {
        return CustomerID;
    }

}

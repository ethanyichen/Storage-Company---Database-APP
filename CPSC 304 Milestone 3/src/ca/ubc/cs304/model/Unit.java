package ca.ubc.cs304.model;

public class Unit {
    private Integer UnitID;
    private Integer WarehouseID;

    public Unit(Integer UnitID, Integer WarehouseID) {
        this.UnitID = UnitID;
        this.WarehouseID = WarehouseID;
    }

    public Integer getUnitID() {
        return UnitID;
    }

    public Integer getWarehouseID() {
        return WarehouseID;
    }


}

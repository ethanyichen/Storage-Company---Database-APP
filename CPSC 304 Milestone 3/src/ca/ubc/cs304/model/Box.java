package ca.ubc.cs304.model;

public class Box {
    private Integer boxID;
    private Integer storingUnitID;
    private Integer ownerID;
    private Integer size;

    public Box(Integer boxID, Integer storingUnitID, Integer ownerID, Integer size) {
        this.boxID = boxID;
        this.storingUnitID = storingUnitID;
        this.ownerID = ownerID;
        this.size = size;
    }

    public Integer getBoxID() {
        return boxID;
    }

    public Integer getStoringUnitID() {
        return storingUnitID;
    }

    public Integer getOwnerID() {
        return ownerID;
    }

    public Integer getSize() {
        return size;
    }
}

CREATE TABLE Cart
(
    cartID INTEGER,
    model  VARCHAR(5),
    PRIMARY KEY (cartID)
);
CREATE TABLE Warehouse
(
    warehouseID INTEGER,
    location    VARCHAR(50),
    wName       VARCHAR(50),
    PRIMARY KEY (warehouseID)
);
CREATE TABLE Employee
(
    employeeID  INTEGER,
    warehouseID INTEGER NOT NULL,
    eName       VARCHAR(80),
    PRIMARY KEY (employeeID),
    FOREIGN KEY (warehouseID) REFERENCES Warehouse (warehouseID)
);
CREATE TABLE employeeSalary
(
    employeeID INTEGER,
    salary     INTEGER,
    PRIMARY KEY (employeeID),
    FOREIGN KEY (employeeID) REFERENCES Employee (employeeID) ON DELETE CASCADE
);
CREATE TABLE use
(
    cartID     INTEGER,
    employeeID INTEGER,
    startTime  DATE,
    endTime    DATE,
    PRIMARY KEY (cartID, employeeID),
    FOREIGN KEY (employeeID) REFERENCES Employee (employeeID) ON DELETE SET NULL
);
CREATE TABLE Customer
(
    customerID INTEGER,
    cName      VARCHAR(80),
    phoneNum   CHAR(10),
    PRIMARY KEY (customerID)
);

CREATE TABLE Unit
(
    unitID      INTEGER,
    capacity    INTEGER,
    warehouseID INTEGER NOT NULL,
    employeeID  INTEGER NOT NULL,
    customerID  INTEGER,
    startDate   DATE,
    endDate     DATE,
    PRIMARY KEY (unitID),
    FOREIGN KEY (customerID) REFERENCES Customer (CustomerID) ON DELETE SET NULL,
    FOREIGN KEY (employeeID) REFERENCES Employee (employeeID),
    FOREIGN KEY (warehouseID) REFERENCES Warehouse (warehouseID)
);

CREATE TABLE ParkingSpace
(
    parkNum    INTEGER,
    customerID INTEGER,
    parkSince  Date,
    UNIQUE (customerID),
    PRIMARY KEY (parkNum),
    FOREIGN KEY (customerID) REFERENCES Customer (customerID) ON DELETE SET NULL
);
CREATE TABLE Box
(
    boxID      INTEGER,
    unitID     INTEGER NOT NULL,
    customerID INTEGER NOT NULL,
    boxSize       INTEGER,
    PRIMARY KEY (boxID),
    FOREIGN KEY (unitID) REFERENCES Unit (UnitID),
    FOREIGN KEY (customerID) REFERENCES Customer (CustomerID)
);
CREATE TABLE Own
(
    warehouseID INTEGER,
    parkNum     INTEGER,
    PRIMARY KEY (warehouseID, parkNum),
    FOREIGN KEY (warehouseID) REFERENCES Warehouse (warehouseID) ON DELETE SET NULL,
    FOREIGN KEY (parkNum) REFERENCES ParkingSpace (parkNum)
);
CREATE TABLE Member
(
    warehouseID         INTEGER NOT NULL,
    customerID          INTEGER,
    membershipStartDate DATE,
    PRIMARY KEY (warehouseID, customerID),
    FOREIGN KEY (warehouseID) REFERENCES Warehouse (warehouseID) ON DELETE CASCADE,
    FOREIGN KEY (customerID) REFERENCES Customer (customerID) ON DELETE CASCADE
);
CREATE TABLE rentFee
(
    startDate  DATE,
    capacity   INTEGER,
    monthlyFee INTEGER,
    PRIMARY KEY (startDate, capacity)
);
CREATE TABLE Room
(
    unitID  INTEGER,
    roomNum INTEGER,
    PRIMARY KEY (unitID)
);
CREATE TABLE Container
(
    unitID       INTEGER,
    containerNum INTEGER,
    PRIMARY KEY (unitID)
);
CREATE TABLE Uniform
(
    uniformID  INTEGER,
    employeeID INTEGER,
    PRIMARY KEY (uniformID, employeeID),
    FOREIGN KEY (employeeID) REFERENCES Employee (employeeID)
);
CREATE TABLE purchase
(
    warehouseID INTEGER NOT NULL,
    cartID      INTEGER,
    since       DATE,
    PRIMARY KEY (warehouseID, cartID),
    FOREIGN KEY (warehouseID) REFERENCES Warehouse (warehouseID) ON DELETE SET NULL,
    FOREIGN KEY (cartID) REFERENCES Cart (cartID)
);
INSERT INTO Customer(customerID,cName,phoneNum) VALUES('8385047','Baguette','6048059783');
INSERT INTO Customer(customerID,cName,phoneNum) VALUES('8381234','Leonardo Dicaprio','6048059783');
INSERT INTO Customer(customerID,cName,phoneNum) VALUES('8384567','Brad Pitt','6048059783');
INSERT INTO Customer(customerID,cName,phoneNum) VALUES('8383569','Robert De Niro','6048059783');

INSERT INTO Employee(employeeID,eName,warehouseID) VALUES('001','Bob','1000');
INSERT INTO Employee(employeeID,eName,warehouseID) VALUES('002','Tom','1000');
INSERT INTO Employee(employeeID,eName,warehouseID) VALUES('003','Sam','1000');
INSERT INTO Employee(employeeID,eName,warehouseID) VALUES('004','Kim','1000');

INSERT INTO Warehouse(warehouseID,location,wName) VALUES('1000','Vancouver','Vancouver-General');


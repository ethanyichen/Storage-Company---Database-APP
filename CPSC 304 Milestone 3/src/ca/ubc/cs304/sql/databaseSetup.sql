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
    startTime  VARCHAR(10),
    endTime    VARCHAR(10),
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
    startDate   VARCHAR(10),
    endDate     VARCHAR(10),
    PRIMARY KEY (unitID),
    FOREIGN KEY (customerID) REFERENCES Customer (CustomerID) ON DELETE SET NULL,
    FOREIGN KEY (employeeID) REFERENCES Employee (employeeID),
    FOREIGN KEY (warehouseID) REFERENCES Warehouse (warehouseID)
);

CREATE TABLE ParkingSpace
(
    parkNum    INTEGER,
    customerID INTEGER,
    parkSince  VARCHAR(10),
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
    membershipStartDate VARCHAR(10),
    PRIMARY KEY (warehouseID, customerID),
    FOREIGN KEY (warehouseID) REFERENCES Warehouse (warehouseID) ON DELETE CASCADE,
    FOREIGN KEY (customerID) REFERENCES Customer (customerID) ON DELETE CASCADE
);
CREATE TABLE rentFee
(
    startDate  VARCHAR(10),
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
    since       VARCHAR(10),
    PRIMARY KEY (warehouseID, cartID),
    FOREIGN KEY (warehouseID) REFERENCES Warehouse (warehouseID) ON DELETE SET NULL,
    FOREIGN KEY (cartID) REFERENCES Cart (cartID)
);

CREATE TABLE Rent (
    warehouseID INTEGER,
    customerID INTEGER,
    monthlyFee INTEGER,
    startDate  VARCHAR(10),
    endDate  VARCHAR(10),
    PRIMARY KEY (warehouseID, customerID, startDate),
    FOREIGN KEY  (warehouseID) REFERENCES Warehouse (warehouseID) ON DELETE SET NULL,
    FOREIGN KEY (customerID) REFERENCES Customer (customerID) ON DELETE CASCADE
);

INSERT INTO Customer(customerID,cName,phoneNum) VALUES('8385047','Baguette','6048059783');
INSERT INTO Customer(customerID,cName,phoneNum) VALUES('8381234','Leonardo Dicaprio','6048059783');
INSERT INTO Customer(customerID,cName,phoneNum) VALUES('8384567','Brad Pitt','6048059783');
INSERT INTO Customer(customerID,cName,phoneNum) VALUES('8383569','Robert De Niro','6048059783');

INSERT INTO Warehouse(warehouseID,location,wName) VALUES('1000','1818 Quebec Street, Vancouver','Quebec Street');
INSERT INTO Warehouse(warehouseID,location,wName) VALUES('1001','33 Commercial Drive, Vancouver','Commercial Drive');
INSERT INTO Warehouse(warehouseID,location,wName) VALUES('1002','3240 No. 4 Road, Richmond','Richmond');
INSERT INTO Warehouse(warehouseID,location,wName) VALUES('1003','3001 Wall Street, Vancouver','Wall Street');
INSERT INTO Warehouse(warehouseID,location,wName) VALUES('1004','250 Taylor Way, West Vancouver','West Vancouver');

INSERT INTO Employee(employeeID,eName,warehouseID) VALUES('121','Bob','1000');
INSERT INTO Employee(employeeID,eName,warehouseID) VALUES('454','Tom','1000');
INSERT INTO Employee(employeeID,eName,warehouseID) VALUES('132','Sam','1000');
INSERT INTO Employee(employeeID,eName,warehouseID) VALUES('865','Kim','1000');

INSERT INTO Member(warehouseID ,customerID,membershipStartDate) VALUES('1000','8385047','10-12-2020');
INSERT INTO Member(warehouseID ,customerID,membershipStartDate) VALUES('1001','8381234','03-08-2020');
INSERT INTO Member(warehouseID ,customerID,membershipStartDate) VALUES('1002','8383569','04-05-2019');

INSERT INTO Unit(unitID, capacity, warehouseID, employeeID, customerID, startDate, endDate)
VALUES ('100','100','1000','001','8385047','2020-05-20','2021-05-20');
INSERT INTO Unit(unitID, capacity, warehouseID, employeeID, customerID, startDate, endDate)
VALUES ('101','200','1001','002','8385047','2020-05-20','2021-05-20');
INSERT INTO Unit(unitID, capacity, warehouseID, employeeID, customerID, startDate, endDate)
VALUES ('102','100','1002','002','8385047','2020-05-20','2021-05-20');
INSERT INTO Unit(unitID, capacity, warehouseID, employeeID, customerID, startDate, endDate)
VALUES ('103','200','1000','003','8385047','2020-05-20','2021-05-20');

INSERT INTO Box(boxID, unitID, customerID, boxSize) VALUES ('20','100','8385047','20');
INSERT INTO Box(boxID, unitID, customerID, boxSize) VALUES ('25','101','8385047','50');
INSERT INTO Box(boxID, unitID, customerID, boxSize) VALUES ('22','102','8385047','60');
INSERT INTO Box(boxID, unitID, customerID, boxSize) VALUES ('23','102','8385047','90');

INSERT INTO Rent (warehouseID, customerID, monthlyFee, startDate, endDate) VALUES
('1001', '8385047', '3200', '20-05-2020', '20-07-2020');
INSERT INTO Rent (warehouseID, customerID, monthlyFee, startDate, endDate) VALUES
('1002', '8381234', '1200', '03-08-2020', '10-07-2021');
INSERT INTO Rent (warehouseID, customerID, monthlyFee, startDate, endDate) VALUES
('1003', '8383569', '3200', '12-05-2020', '10-08-2020');




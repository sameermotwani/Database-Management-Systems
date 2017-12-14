Using "JOINED" strategy for translation from UML into a relational schema.


CREATE TABLE `Producttype` (
  `productTypeID` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) DEFAULT NULL,
  `description` VARCHAR(100) NOT NULL,
  `size` INT(11) NOT NULL,
  PRIMARY KEY (`productTypeID`)
);


CREATE TABLE `Product` (
  `ProductID` INT(11) NOT NULL AUTO_INCREMENT,
  `started` DATE NOT NULL,
  `completed` DATE DEFAULT NULL,
  `label` VARCHAR(100) DEFAULT NULL,
  `pTypeID` INT(11) NOT NULL,
  PRIMARY KEY (`ProductID`),
 FOREIGN KEY (`pTypeID`) REFERENCES `Producttype` (`productTypeID`) ON DELETE CASCADE ON UPDATE CASCADE
)


CREATE TABLE `Employee` (
  `EmployeeID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`EmployeeID`)
)

CREATE TABLE `emailaddress` (
  `emailAddressID` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
`employeeID` INT(11),
  `domain` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`emailAddressID`),
  FOREIGN KEY (`employeeID`) REFERENCES `Employee`(`EmployeeID`) ON DELETE CASCADE ON UPDATE CASCADE
)


CREATE TABLE `Qualification` (
`canRepair` INT REFERENCES ProductType(ProductTypeID) ON UPDATE CASCADE ON DELETE CASCADE,
  canBeRepairedBy INT REFERENCES Employee(EmployeeID) ON UPDATE CASCADE ON DELETE CASCADE,
  LEVEL ENUM ('Novice', 'Intermediate', 'Expert') NOT NULL,
  PRIMARY KEY(canRepair, canBeRepairedBy)
);

CREATE TABLE `factory` (
  `factoryID` INT(11) NOT NULL,
  `address` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`factoryID`)
)

CREATE TABLE `machine` (
  `machineID` INT(11) NOT NULL AUTO_INCREMENT,
  `factoryID` INT(11) NOT NULL REFERENCES `factory` (`factoryID`) ON DELETE CASCADE on update CASCADE
  `code` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`machineID`)
);

CREATE TABLE `Labeler` (
  `labelerID` INT PRIMARY KEY REFERENCES machine(machineID) ON UPDATE CASCADE ON DELETE CASCADE,
  `maxLabel` VARCHAR(100) NOT NULL
);

CREATE TABLE `Loom` (
  loomID INT PRIMARY KEY REFERENCES machine(machineID) ON UPDATE CASCADE ON DELETE CASCADE,
  `size` INT(11) NOT NULL,
 `description` VARCHAR(100) NOT NULL
);


CREATE TABLE `Finisher` (
  `finisherID` INT PRIMARY KEY REFERENCES machine(machineID) ON UPDATE CASCADE ON DELETE CASCADE,
  `size` INT(11) NOT NULL
);

CREATE TABLE `Material` (
  `materialID` INT REFERENCES Finisher(finisherID) ON UPDATE CASCADE ON DELETE CASCADE,
  NAME VARCHAR(200) NOT NULL,
  PRIMARY KEY(materialID,name)
);



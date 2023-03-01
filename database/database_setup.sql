DROP DATABASE IF EXISTS SENG401Project;
CREATE DATABASE SENG401Project;
USE SENG401Project;

CREATE TABLE Customer_Information (
	Customer_ID int NOT NULL AUTO_INCREMENT,
    C_Name varchar(255),
    C_Address varchar(255),
    C_Card_Number int,
    CONSTRAINT PK_Customer PRIMARY KEY (Customer_ID)
);

CREATE TABLE Supplier_Information (
	Supplier_ID int AUTO_INCREMENT,
    S_Name varchar(255),
    S_Description varchar(400),
    CONSTRAINT PK_Supplier PRIMARY KEY (Supplier_ID)
);

CREATE TABLE Item_Information (
	Item_ID int NOT NULL AUTO_INCREMENT,
    I_Name varchar(255),
    I_Description varchar (400),
    I_Cost double,
    I_Category varchar (255),
    S_ID int NOT NULL,
    CONSTRAINT PK_ITEM PRIMARY KEY (Item_ID),
    CONSTRAINT FK_SID FOREIGN KEY (S_ID) REFERENCES Supplier_Information(Supplier_ID)
);

CREATE TABLE Warehouse_Information (
	Warehouse_ID int NOT NULL AUTO_INCREMENT,
    W_Address varchar(255),
    CONSTRAINT PK_Warehouse PRIMARY KEY (Warehouse_ID)
);

CREATE TABLE Order_Information (
	Order_ID int NOT NULL AUTO_INCREMENT,
    C_ID int NOT NULL,
    O_Date date,
    O_Total double,
    Ship_Address varchar(255),
    CONSTRAINT PK_Order PRIMARY KEY (Order_ID),
    CONSTRAINT FK_CID_Order FOREIGN KEY (C_ID) REFERENCES Customer_Information(Customer_ID)
);

CREATE TABLE Order_Items (
	O_ID int NOT NULL,
    I_ID int NOT NULL,
    I_Name varchar(255),
    I_Quantity int,
    I_Location int,
    Shipped boolean DEFAULT false,
    CONSTRAINT PK_OrderItem PRIMARY KEY (O_ID, I_ID),
    CONSTRAINT FK_OrderItem_OID FOREIGN KEY (O_ID) REFERENCES Order_Information(Order_ID),
    CONSTRAINT FK_OrderItem_IID FOREIGN KEY (I_ID) REFERENCES Item_Information(Item_ID),
    CONSTRAINT FK_OrderItem_ILoc FOREIGN KEY (I_Location) REFERENCES Warehouse_Information(Warehouse_ID)
);

CREATE TABLE Warehouse_Employees (
	Employee_ID int NOT NULL AUTO_INCREMENT,
    E_Name varchar(255),
    CONSTRAINT PK_Emp PRIMARY KEY (Employee_ID)
);

CREATE TABLE Warehouse_Inventory (    #not done
	Item_ID int NOT NULL,
    W_ID int NOT NULL,
    I_Name varchar(255),
    Quantity int,
    S_ID int NOT NULL,					#foreign key
    Aisle_No int,
    CONSTRAINT PK_WIN PRIMARY KEY (Item_ID, W_ID),
    CONSTRAINT PK_WIN_IID FOREIGN KEY (Item_ID) REFERENCES Item_Information(Item_ID),
    CONSTRAINT PK_WIN_WID FOREIGN KEY (W_ID) REFERENCES Warehouse_Information(Warehouse_ID),
    CONSTRAINT PK_WIN_SID FOREIGN KEY (S_ID) REFERENCES Supplier_Information(Supplier_ID)
);

CREATE TABLE Login_Information (
	username varchar(25) UNIQUE NOT NULL,
    password varchar(25)  UNIQUE NOT NULL,
    user_type enum('c', 's', 'w')
);
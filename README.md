# Tour Management System

This is a simple Java + MySQL project for managing Tours and Customers.  
It demonstrates CRUD operations (Create, Read, Update, Delete) using JDBC.

---

## Features
- Add, View, Update, Delete Tours
- Add, View, Update, Delete Customers
- MySQL database with proper foreign key relationships
- Menu-driven console interface

---

## Database Details
- *Database Name:* tour_management
- *Tables:*
  - *tours*: tour_id, tour_name, destination, price
  - *customers*: cust_id, cust_name, tour_id, num_people
- Database backup file: **tour_management.sql**

---

## Tools Used
- Java (NetBeans IDE)
- MySQL Workbench
- JDBC (MySQL Connector)

---

## How to Run

1. Import the tour_management.sql file into MySQL to set up the database.
2. Open the project in NetBeans (or any Java IDE).
3. Run **TourManagement.java** (this is the main program with the menu).

> *Note:*  
> - DBConnectTest.java is used for database connectivity (helper class).  
> - TourInsert.java was an earlier test file for inserting tours; you do *not* need to run it for the full project.

---

## Sample Operations

*Tours Menu:*
- Add Tour → View Tours → Update Price → Delete Tour

*Customers Menu:*
- Add Customer → View Customers → Update Number of People → Delete Customer

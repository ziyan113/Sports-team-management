# 🏏 Sports Management System (Java Swing + JDBC + MySQL)

## 📘 Overview
The **Sports Management System** is a GUI-based Java application built using **Swing** and **JDBC** for managing player records in a sports database.  
It allows users to easily **add**, **update**, **delete**, and **view** player information using a simple interface connected to a MySQL backend.

This project demonstrates:
- Java Swing GUI design  
- Database connectivity using JDBC  
- CRUD operations (Create, Read, Update, Delete)

---

## ⚙️ Features
- ✅ Connects Java with MySQL using JDBC  
- ✅ Create and manage a player database  
- ✅ Insert new players  
- ✅ Update player details  
- ✅ Delete players  
- ✅ View all player data in a JTable  
- ✅ GUI built completely with **Java Swing**  
- ✅ Database auto-created if not existing  

---

## 🗃️ Database Information

**Database Name:** `Assigned_Student_RA2411026010508`  
**Table Name:** `Players`

| Column Name  | Data Type    | Description           |
|---------------|--------------|-----------------------|
| player_id     | INT          | Unique Player ID      |
| name          | VARCHAR(50)  | Player Name           |
| age           | INT          | Player Age            |
| sport         | VARCHAR(50)  | Sport Name/Type       |

The database and table will be **automatically created** when you run the program for the first time.

---

## 💻 Requirements

Before running, ensure you have:

1. **Java JDK 17 or higher** (recommended: JDK 21 or JDK 23)  
2. **Eclipse IDE** (or IntelliJ IDEA)  
3. **MySQL Server** (running locally on port `3306`)  
4. **MySQL Connector/J** (JDBC Driver JAR file)

---

## 🧩 Setup Instructions

### Step 1: Create a New Java Project
- Open **Eclipse**
- Click:  
  `File → New → Java Project`
- Name the project: **JDBCDemo**

### Step 2: Add MySQL Connector
- Right-click the project → **Properties**
- Go to:  
  `Java Build Path → Libraries → Add External JARs`
- Select your downloaded `mysql-connector-j-xx.jar` (JDBC Driver)
- Click **Apply and Close**

### Step 3: Create a Package
- Right-click `src` → **New → Package**
- Name it: `jdbc`

### Step 4: Add Your Java File
- Right-click `jdbc` → **New → Class**
- Name it: `SportsGUI`
- Paste your complete Java code inside it.

### Step 5: Update Database Credentials
Inside the code, edit:
```java
String user = "root";          // Your MySQL username
String pass = "your_password"; // Your MySQL password

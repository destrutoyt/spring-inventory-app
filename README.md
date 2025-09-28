# 🚲 Bike Shop Inventory Management (Spring Boot)

This project is a **Spring Boot web application** that allows users to manage inventory for a bike shop.  
It includes functionality for managing **in-house parts, outsourced parts, and products**, along with custom **validation rules** and a responsive **UI**.  

⚠️ **Note:** Database integration is currently in progress. Some features may not work properly until DB implementation is finalized.  

---

## 🌟 Features

- 🎨 **Custom HTML UI**
  - Added shop name, product names, and part names across all pages  
  - Integrated **Bootstrap** for responsive design and improved UX  
  - Cleaned and validated HTML to follow W3C standards  

- 🏷️ **About Page**
  - Added an “About Us” landing page for the shop  
  - Linked it to the main screen with navigation buttons  
  - Added support for images and custom CSS  

- 📦 **Sample Inventory**
  - Preloaded the app with **5 products** and **5 parts** on startup  
  - Ensures users have working data without manual entry  

- 🛒 **Buy Now Feature**
  - Each product has a **Buy Now** button  
  - Decrements product inventory on purchase (does not affect part inventory)  
  - Displays success or failure messages after a transaction  

- 📊 **Inventory Constraints**
  - Added **maximum** and **minimum** inventory fields to parts  
  - UI forms updated to include input fields for `MIN_INV` and `MAX_INV`  
  - Product and part tables display these fields  

- ✅ **Validation**
  - Custom validation ensures inventory is **between min and max values**  
  - Error messages display when:  
    - Inventory is below minimum  
    - Inventory exceeds maximum  
    - Product creation would lower part inventory below minimum  
  - Created reusable annotations and validators for cleaner code  

- 🧪 **Unit Testing**
  - Implemented JUnit tests for `MIN_INV` and `MAX_INV` getters/setters  
  - Validates business logic and enforces data integrity  

- 🧹 **Code Cleanup**
  - Removed unused imports and validators  
  - Refactored controllers for consistency  
  - Promoted clean code practices throughout the project  

---

## 🛠️ Tech Stack
- ☕ **Java 21**  
- 🌱 **Spring Boot 3 / Spring Framework 6**  
- 🛢️ **MySQL** (in progress)  

## 📦 Dependencies
- 🌐 **Spring Web** – REST & MVC  
- 🗄️ **Spring Data JPA** – persistence layer  
- 🔗 **MySQL Connector/J** – DB connection  
- 📦 **Jackson** – JSON serialization  
- 🧪 **Spring Boot Starter Test** – unit testing  

## 👨‍💻 Development Tools
- 🧑‍💻 **VS Code** – IDE  
- 🛠️ **Postman** – API testing & documentation  
- 🔗 **Git & GitHub** – version control  
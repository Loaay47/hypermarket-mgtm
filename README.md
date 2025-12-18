# Hypermarket Management System

A Hypermarket Management System built with Java Swing, designed to handle different user roles like administrators, inventory managers, sellers, and marketing staff. It helps manage products, track stock, process orders, handle employees, and even supports basic demand tracking—all in one lightweight application.

---

## Getting Start

Clone the repository and build the project from source on Linux:

```sh
git clone https://github.com/Loaay47/hypermarket-mgtm.git
cd hypermarket-mgtm
```

###  Build using Makefile (Linux)

This project includes a `Makefile` to automate compilation:

* To compile and run the application:

```sh
make
make run
```

> Make sure you have JDK installed (Java 8+ recommended).

---

## Overview

This system is implemented in **Java** using **Swing** for the graphical interface and plain text files for persistence (no external database).

It follows a **modular manager design** where core subsystems interact cleanly:

* **InventoryManager** — handles product storage and stock.
* **SalesManager** — manages orders and stock deduction.
* **AdminManager** — manages users and employee lifecycle.
* **MarketingManager** — supports promotional workflows.
* **IdGenerator** — auto‑increments and tracks unique IDs for products, orders, offers, and users.

Persistence is implemented by each manager reading/writing to flat files in `data/`, enabling quick bootstrapping and light deployment without external dependencies.

---

## Features Implemented

### **Product Management**

* Add, update, delete products.
* Minimum stock warning and expiry date tracking.
* Auto‑generated product IDs.

### **Order Processing**

* Create orders with stock validation.
* Track total price and remaining stock.
* Save orders persistently.

### **User Roles & Authentication**

* Login system supporting multiple roles.
* Different menus and permissions per role.
* Admin controls for adding employees.

### **Simple Reporting**

* View product list in a sortable table.
* View logged‑in user’s orders in a table.
* Search products by ID (via dialogs).

---

##  Code Structure

```
src/
├─ models/          — domain classes (Product, Order, User)
├─ managers/        — business logic (InventoryManager, SalesManager, etc.)
└─ view/            — GUI screens and Swing components

```

Managers encapsulate state and persist data into text files (`data/*.txt`).
UI logic dynamically switches panels without external GUI frameworks.

---

## Persistence Format

All data is stored under the `data/` directory

* `products.txt` — product inventory
* `orders.txt` — recorded orders
* `offers.txt` — current promotions
* `users.txt` — registered users

---

## Contribution

This project aims to be a clean, maintainable hypermarket management solution in Java. Contributions are welcome via pull requests, issue reports, or enhancements.

---

## License

Distributed under the **GPL‑3.0 License** — see `LICENSE` for details.


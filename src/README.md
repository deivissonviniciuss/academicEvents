# Academic Event Management System

This is a Java-based academic event management system developed as a university assignment.  
It allows the registration of events and participants, the association between them, certificate generation, and event reporting — all through a command-line interface.

---

## 📚 Features

- Register different types of **events**:
  - `Lecture`
  - `Course` (only accepts `Student`)
  - `Workshop`
  - `AcademicFair`

- Register different types of **participants**:
  - `Student`
  - `Professor`
  - `Guest`

- Events can be either `IN_PERSON` or `ONLINE`, using an `EventMode` enum
- Registration control with **event capacity limit**
- Prevention of **duplicate participant IDs** (based on enrollment ID, employee ID, or guest ID)
- **Certificate generation**:
  - Printed in console
  - PDF (using Apache PDFBox)
- Event reports by:
  - **Type**
  - **Date**
- Colored and organized console interface (using Jansi)

---

## 🧰 Technologies and Tools

- Java 11+
- Maven
- Jansi (for console color formatting)
- Apache PDFBox (for PDF certificate generation)
- Object-Oriented Programming:
  - Abstraction
  - Inheritance
  - Polymorphism
  - Encapsulation
  - Method Overloading
  - Interfaces

---

## ▶️ How to Compile and Run

This project uses **Maven**. You do **not need any IDE** to run the system — just Java and Maven installed.

### 1. Compile the project

```bash
mvn clean compile

### 2. Run the system

```bash
mvn exec:java



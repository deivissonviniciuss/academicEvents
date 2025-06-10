# Academic Event Management System

This is a Java-based academic event management system developed as a university assignment.  
It models different types of events and participants, and allows registration, certificate generation, and event reporting.

---

## 📚 Features

- Supports different **event types**:
  - `Lecture`
  - `Course` (only accepts `Student`)
  - `Workshop`
  - `AcademicFair`

- Supports different **participant roles**:
  - `Student`
  - `Professor`
  - `Guest`

- Event **registration with capacity control**
- Event **mode**: `IN_PERSON` or `ONLINE`, using an `EventMode` enum
- **Certificate generation** (console and PDF format)
- Prevents **duplicate participant IDs**
- Generates **event reports**:
  - By **type**
  - By **date**
- Clean and colored console interface using Jansi

---

## ⚙️ Technologies Used

- Java 11+
- Maven
- VS Code + GitHub Codespaces
- **Jansi** – Console output formatting with colors
- **Apache PDFBox** – PDF certificate generation
- OOP principles:
  - Inheritance
  - Abstraction
  - Encapsulation
  - Polymorphism
  - Method Overloading
  - Interfaces

---

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/your-username/academicEvents.git
cd academicEvents

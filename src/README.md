# Academic Event Management System

This is a Java-based academic event management system developed as a university assignment.  
It models different types of events and participants, and allows registration, certificate generation, and event reporting.

---

## Features

- Supports different **event types**:
  - `Lecture`
  - `Course` (only accepts `Student`)
  - `Workshop`
  - `AcademicFair`

- Supports different **event modes**:

The system uses an `EventMode` enum to define whether an event is `IN_PERSON` or `ONLINE`. This prevents typos, enforces valid values at compile time, and simplifies control logic in registration. It also makes the code more maintainable and expandable, allowing new modes like `HYBRID` to be added easily in the future.

---

- Supports different **participant roles**:
  - `Student`
  - `Professor`
  - `Guest`

- **Certificate generation** per participant
- **Specific event information** depending on event type (e.g. speaker, number of lessons)
- **Event modes**: `IN_PERSON` and `ONLINE`, with different registration rules
- **Event report**:
  - By **type**
  - By **date**

## Technologies

- Java 17+
- VS Code
- Git & GitHub

---

## How to Run

1. Compile:
   ```bash
   mvn compile

2. Run:
   ```bash
    mvn exec:java

##

- Developed by Deivisson Vinicius França de Jesus
- Third semester – IFBA – Programming Oriented to Objects

package main;

import events.*;
import participants.*;

public class Main {
    public static void main(String[] args) {
        EventManager manager = new EventManager();

        // === Criação de Eventos ===
        Lecture lecture1 = new Lecture("AI Lecture", "2025-06-10", "Auditorium A", 2,
                "Basics of AI", EventMode.IN_PERSON, "Dr. Jane Smith");

        Lecture lecture2 = new Lecture("Cybersecurity", "2025-06-11", "Auditorium B", 3,
                "Network security and data protection", EventMode.ONLINE, "Dr. Alice Johnson");

        Course course1 = new Course("Java Fundamentals", "2025-06-12", "Lab A", 2,
                "Basic Java programming", EventMode.IN_PERSON, 6);

        Course course2 = new Course("Data Structures", "2025-06-13", "Lab B", 1,
                "Lists, stacks, and queues", EventMode.IN_PERSON, 8);

        Workshop workshop1 = new Workshop("Arduino Basics", "2025-06-14", "Room C", 2,
                "Hands-on with Arduino", EventMode.ONLINE, 4);

        Workshop workshop2 = new Workshop("3D Printing", "2025-06-15", "Room D", 2,
                "Printing your first prototype", EventMode.IN_PERSON, 3);

        AcademicFair fair1 = new AcademicFair("Tech Fair 2025", "2025-06-16", "Pavilion A", 100,
                "Exhibition of student projects", EventMode.IN_PERSON, "Innovation");

        AcademicFair fair2 = new AcademicFair("Science Fair", "2025-06-17", "Pavilion B", 50,
                "Experiments and demos", EventMode.IN_PERSON, "Science and Discovery");

        // === Adiciona eventos ao gerenciador ===
        manager.addEvent(lecture1);
        manager.addEvent(lecture2);
        manager.addEvent(course1);
        manager.addEvent(course2);
        manager.addEvent(workshop1);
        manager.addEvent(workshop2);
        manager.addEvent(fair1);
        manager.addEvent(fair2);

        // === Criação de Participantes ===
        Student student1 = new Student("Alice", "alice@gmail.com", "2023101");
        Student student2 = new Student("Bob", "bob@gmail.com", "2023102");

        Professor professor1 = new Professor("Dr. John", "john@ifba.edu.br", "889900", "Computer Science");
        Professor professor2 = new Professor("Dr. Maria", "maria@ifba.edu.br", "991122", "Physics");

        Guest guest1 = new Guest("Marco Silva", "marco@gmail.com", "GUEST001");
        Guest guest2 = new Guest("Luana Torres", "luana@gmail.com", "GUEST002");

        // === Registro de participantes em eventos ===

        // Cursos (só alunos)
        course1.registerParticipant(student1); // ✅
        course1.registerParticipant(professor1); // ❌
        course2.registerParticipant(student2); // ✅

        // Palestras (todos)
        lecture1.registerParticipant(student1); // ✅
        lecture1.registerParticipant(professor1); // ✅
        lecture2.registerParticipant(guest1); // ✅
        lecture2.registerParticipant(professor2); // ✅

        // Workshops
        workshop1.registerParticipant(guest2); // ✅
        workshop1.registerParticipant(student2); // ✅
        workshop2.registerParticipant(professor1); // ✅

        // Feiras
        fair1.registerParticipant(student1); // ✅
        fair1.registerParticipant(guest1); // ✅
        fair2.registerParticipant(professor2); // ✅
        fair2.registerParticipant(student2); // ✅

        // === Geração de Certificados ===
        System.out.println("\n=== Certificates ===");
        course1.generateCertificate(student1);      // ✅
        course2.generateCertificate(student2);      // ✅
        lecture1.generateCertificate(professor1);   // ✅
        lecture2.generateCertificate(guest1);       // ✅
        workshop1.generateCertificate(guest2);      // ✅
        fair1.generateCertificate(guest1);          // ✅
        fair2.generateCertificate(student2);        // ✅

        // === Relatórios ===
        manager.listEventsByType("Course");

        manager.listEventsByType("Lecture");

        manager.listEventsByDate("2025-06-12");

        manager.listEventsByDate("2025-06-17");
    }
}

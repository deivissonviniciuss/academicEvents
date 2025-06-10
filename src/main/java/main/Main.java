package main;

import java.util.*;
import util.ConsolePrinter;

import participants.*;
import events.*;

public class Main {
    private static final List<Participant> participants = new ArrayList<>();
    private static final EventManager eventManager = new EventManager();

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ConsolePrinter.init();
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = readUserChoice();
            if (choice < 0 || choice > 5) {
                ConsolePrinter.printError("Invalid option!");
                break;
            }

            switch (choice) {
                case 0: {
                    running = false;
                    break;
                }
                case 1:
                    displayEventMenu();
                    int choiceEvent = readUserChoice();

                    if (choiceEvent < 0 || choiceEvent > 4) {
                        ConsolePrinter.printError("Invalid option!");
                        break;
                    }

                    System.out.print("Enter the event name: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter the event date (yyyy/mm/dd): ");
                    String date = scanner.nextLine();

                    System.out.print("Enter the event location: ");
                    String location = scanner.nextLine();

                    System.out.print("Enter the event mode (IN_PERSON/ONLINE): ");
                    String modeInput = scanner.nextLine();

                    System.out.print("Enter event capacity: ");
                    int capacity = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter event description: ");
                    String description = scanner.nextLine();

                    EventMode mode = EventMode.valueOf(modeInput.toUpperCase());
                    Event event = null;

                    switch (choiceEvent) {
                        case 1: {
                            System.out.print("Enter the speaker's name: ");
                            String speaker = scanner.nextLine();
                            event = new Lecture(title, date, location, capacity, description, mode, speaker);
                            break;
                        } case 2: {
                            System.out.print("Enter the number of lessons: ");
                            int numberOfLessons = Integer.parseInt(scanner.nextLine());
                            event = new Course(title, date, location, capacity, description, mode, numberOfLessons);
                            break;
                        } case 3: {
                            System.out.print("Enter the main topic: ");
                            String mainTopic = scanner.nextLine();
                            event = new AcademicFair(title, date, location, capacity, description, mode, mainTopic);
                            break;
                        } case 4: {
                            System.out.print("Enter the course duration (in hours): ");
                            int durationInHours = Integer.parseInt(scanner.nextLine());
                            event = new Workshop(title, date, location, capacity, description, mode, durationInHours);
                            break;
                        }
                    }

                    if (event != null) {
                        eventManager.addEvent(event);
                    }
                    break;

                case 2:
                    displayParticipantMenu();
                    int choiceParticipant = readUserChoice();

                    if (choiceParticipant < 0 || choiceParticipant > 3) {
                        ConsolePrinter.printError("Invalid option!");
                        break;
                    }

                    System.out.print("Enter the participant's name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter the participant's email: ");
                    String email = scanner.nextLine();

                    Participant participant = null;

                    switch (choiceParticipant) {
                        case 1:
                            System.out.print("Enter the employee ID: ");
                            String employeeId = scanner.nextLine();

                            System.out.print("Enter the department: ");
                            String department = scanner.nextLine();

                            participant = new Professor(name, email, employeeId, department);
                            break;
                        case 2:
                            System.out.print("Enter the enrollment ID: ");
                            String enrollmentId = scanner.nextLine();

                            participant = new Student(name, email, enrollmentId);
                            break;
                        case 3:
                            System.out.print("Enter the guest ID: ");
                            String guestId = scanner.nextLine();

                            participant = new Guest(name, email, guestId);
                            break;
                    }

                    if (participant != null) {
                        if (isDuplicateId(participant)) {
                            ConsolePrinter.printError("A participant with the same ID already exists.");
                            break;
                        }
                        participants.add(participant);
                    }
                    break;

                case 3:
                    ConsolePrinter.printSectionHeader("Register Participant in Event");
                    if (eventManager.getAllEvents().isEmpty()) {
                        ConsolePrinter.printError("No events available.");
                        break;
                    }
                    if (participants.isEmpty()) {
                        ConsolePrinter.printError("No participants available.");
                        break;
                    }
                    
                    System.out.println("Available Events:");
                    List<Event> allEvents = eventManager.getAllEvents();
                    for (int i = 0; i < allEvents.size(); i++) {
                        System.out.println((i + 1) + ". " + allEvents.get(i).getTitle()+ "(" + allEvents.get(i).getEventType() + ")");
                    }
                    System.out.print("Select event by number: ");
                    int eventIndex = readUserChoice() - 1;

                    if (eventIndex < 0 || eventIndex >= allEvents.size()) {
                        ConsolePrinter.printError("Invalid event selection.");
                        break;
                    }

                    Event selectedEvent = allEvents.get(eventIndex);

                    System.out.println("Available Participants:");
                    List<Participant> allParticipants = participants;
                    for (int i = 0; i < allParticipants.size(); i++) {
                        System.out.println((i + 1) + ". " + allParticipants.get(i).getName() + "(" + allParticipants.get(i).getParticipantType() + ")");
                    }
                    System.out.print("Select participant by number: ");
                    int participantIndex = readUserChoice() - 1;

                    if (participantIndex < 0 || participantIndex >= allParticipants.size()) {
                        ConsolePrinter.printError("Invalid participant selection.");
                        break;
                    }

                    Participant selectedParticipant = allParticipants.get(participantIndex);
                    selectedEvent.registerParticipant(selectedParticipant);
                    break;

                case 4: {
                    ConsolePrinter.printSectionHeader("List Events (Reports)");
                    System.out.println("1 - List by type");
                    System.out.println("2 - List by date");
                    System.out.println("3 - List all events");
                    System.out.println("4 - Main menu");
                    System.out.print("Choose an option: ");
                    int reportOption = readUserChoice();

                    if (reportOption < 0 || reportOption > 3) {
                        ConsolePrinter.printError("Invalid option!");
                        break;
                    }

                    switch (reportOption) {
                        case 1: {
                            System.out.print("Enter type (Lecture, Course, Workshop, AcademicFair): ");
                            String reportType = scanner.nextLine();
                            eventManager.listEventsByType(reportType);
                            break;
                        }
                        case 2: {
                            System.out.print("Enter date (dd/mm/yyyy): ");
                            String reportDate = scanner.nextLine();
                            eventManager.listEventsByDate(reportDate);
                            break;
                        }
                        case 3: {
                            List<Event> events = eventManager.getAllEvents();
                            if (events.isEmpty()) {
                                ConsolePrinter.printError("No events available.");
                            } else {
                                ConsolePrinter.printSectionHeader("List of Events");
                                for (Event e : events) {
                                    System.out.println("- " + e.getTitle() + " (" + e.getEventType() + ") on " + e.getDate());
                                }
                            }
                            break;
                        }
                        case 4: {
                            break;
                        }
                        default:  {
                            ConsolePrinter.printError("Invalid report option.");
                        }
                    }
                    break;
                }
                case 5: {
                    ConsolePrinter.printSectionHeader("Generate Certificate");
                    if (eventManager.getAllEvents().isEmpty()) {
                        ConsolePrinter.printError("No events available.");
                        break;
                    }
                    if (participants.isEmpty()) {
                        ConsolePrinter.printError("No participants available.");
                        break;
                    }

                    System.out.println("Available Events:");
                    List<Event> certEvents = eventManager.getAllEvents();
                    for (int i = 0; i < certEvents.size(); i++) {
                        System.out.println((i + 1) + ". " + certEvents.get(i).getTitle() + "(" + certEvents.get(i).getEventType() + ")");
                    }
                    System.out.print("Select event by number: ");
                    int certEventIndex = readUserChoice() - 1;
                    if (certEventIndex < 0 || certEventIndex >= certEvents.size()) {
                        ConsolePrinter.printError("Invalid event selection.");
                        break;
                    }

                    Event certEvent = certEvents.get(certEventIndex);

                    System.out.println("Participants registered in this event:");
                    List<Participant> eventParticipants = certEvent.getParticipants();
                    if (eventParticipants.isEmpty()) {
                        ConsolePrinter.printError("No participants registered in this event.");
                        break;
                    }
                    for (int i = 0; i < eventParticipants.size(); i++) {
                        System.out.println((i + 1) + ". " + eventParticipants.get(i).getName() + "(" + eventParticipants.get(i).getParticipantType() + ")");
                    }

                    System.out.print("Select participant by number: ");
                    int certPartIndex = readUserChoice() - 1;
                    if (certPartIndex < 0 || certPartIndex >= eventParticipants.size()) {
                        ConsolePrinter.printError("Invalid participant selection.");
                        break;
                    }

                    System.out.println("1: Certificate in console");
                    System.out.println("2: Certificate in PDF");
                    System.out.print("Choose an option: ");
                    int chooseCertificateType = readUserChoice();

                    if (chooseCertificateType < 0 || chooseCertificateType > 2) {
                        ConsolePrinter.printError("Invalid option!");
                        break;
                    }

                    Participant certParticipant = eventParticipants.get(certPartIndex);

                    switch(chooseCertificateType){
                        case 1:{
                            certEvent.generateCertificate(certParticipant);
                            break;
                        }
                        case 2:{
                            certEvent.generateCertificate(certParticipant, "PDF");
                            break;
                        }
                    }
                    break;
                }
                default:{
                    ConsolePrinter.printError("Invalid option. Please try again.");
                    break;
                }
            }

            if (running) {
                System.out.print("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        ConsolePrinter.printTitle("Exiting system. Goodbye!");
        ConsolePrinter.shutdown();
        scanner.close();
    }

    private static void displayMenu() {
        ConsolePrinter.printTitle("ACADEMIC EVENTS MANAGEMENT SYSTEM");
        ConsolePrinter.printText("1: Add New Event");
        ConsolePrinter.printText("2: Add New Participant");
        ConsolePrinter.printText("3: Register Participant in Event");
        ConsolePrinter.printText("4: List Events (Reports)");
        ConsolePrinter.printText("5: Generate Certificate");
        ConsolePrinter.printText("0: Exit");
        System.out.print("\nChoose an option: ");
    }

    private static void displayEventMenu() {
        ConsolePrinter.printSectionHeader("Add New Event");
        System.out.println("Choose the type of event:");
        System.out.println("1 - Lecture");
        System.out.println("2 - Course");
        System.out.println("3 - Academic Fair");
        System.out.println("4 - Workshop");
        System.out.println("0 - Back");
        System.out.print("\nChoose an option: ");
    }

    private static void displayParticipantMenu() {
        ConsolePrinter.printSectionHeader("Add New Participant");
        System.out.println("Choose the type of participant:");
        System.out.println("1 - Professor");
        System.out.println("2 - Student");
        System.out.println("3 - Guest");
        System.out.println("0 - Back");
        System.out.print("\nChoose an option: ");
    }

    /**
     * Reads and validates the user's integer input for the menu.
     * @return The user's choice, or -1 if the input is invalid.
     */
    private static int readUserChoice() {
        try {
            String input = scanner.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1; // Indicates invalid (non-numeric) input
        }
    }

    public static boolean isDuplicateId(Participant newParticipant) {
        for (Participant p : participants) {
            if (p instanceof Professor && newParticipant instanceof Professor) {
                Professor existing = (Professor) p;
                Professor incoming = (Professor) newParticipant;
                if (existing.getEmployeeId().equalsIgnoreCase(incoming.getEmployeeId())) {
                    return true;
                }
            } else if (p instanceof Student && newParticipant instanceof Student) {
                Student existing = (Student) p;
                Student incoming = (Student) newParticipant;
                if (existing.getEnrollmentNumber().equalsIgnoreCase(incoming.getEnrollmentNumber())) {
                    return true;
                }
            } else if (p instanceof Guest && newParticipant instanceof Guest) {
                Guest existing = (Guest) p;
                Guest incoming = (Guest) newParticipant;
                if (existing.getGuestId().equalsIgnoreCase(incoming.getGuestId())) {
                    return true;
                }
            }
        }
        return false;
    }
} 

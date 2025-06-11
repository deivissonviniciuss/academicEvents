package br.edu.ifba.inf008.main;

import java.util.*;
import br.edu.ifba.inf008.util.ConsolePrinter;

import br.edu.ifba.inf008.participants.*;
import br.edu.ifba.inf008.events.*;

public class App {
	private static final List<Participant> participants = new ArrayList<>();
	private static final EventManager eventManager = new EventManager();
	private static final Scanner scanner = new Scanner(System.in);

	public void run() {
		ConsolePrinter.init();
		boolean running = true;

		while (running) {
			displayMenu();
			int choice = readValidInt();
			if (choice < 0 || choice > 5) {
				ConsolePrinter.printError("Invalid option!");
				continue;
			}

			switch (choice) {
			case 0: {
				running = false;
				break;
			}
			case 1:
				displayEventMenu();
				int choiceEvent = readValidInt();

				if (choiceEvent <= 0 || choiceEvent > 4) {
					ConsolePrinter.printError("Invalid option!");
					continue;
				}

				String title = readNonEmptyString("Enter the event name: ");
				String date = readNonEmptyString("Enter the event date (yyyy/mm/dd): ");
				String location = readNonEmptyString("Enter the event location: ");
				int capacity = readValidInt("Enter event capacity: ");
				String description = readNonEmptyString("Enter the event description: ");
				EventMode mode = readEventMode("Enter the event mode");
				Event event = null;

				switch (choiceEvent) {
				case 1: {
					String speaker = readNonEmptyString("Enter the speaker's name: ");
					event = new Lecture(title, date, location, capacity, description, mode, speaker);
					break;
				}
				case 2: {
					int numberOfLessons = readValidInt("Enter the number of lessons: ");
					event = new Course(title, date, location, capacity, description, mode, numberOfLessons);
					break;
				}
				case 3: {
					String mainTopic = readNonEmptyString("Enter the main topic: ");
					event = new AcademicFair(title, date, location, capacity, description, mode, mainTopic);
					break;
				}
				case 4: {
					int durationInHours = readValidInt("Enter the course duration (in hours): ");
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
				int choiceParticipant = readValidInt();

				if (choiceParticipant <= 0 || choiceParticipant > 3) {
					ConsolePrinter.printError("Invalid option!");
					continue;
				}

				String name = readNonEmptyString("Enter the participant's name: ");
				String email = readNonEmptyString("Enter the participant's email: ");

				Participant participant = null;

				switch (choiceParticipant) {
				case 1:
					String employeeId = readNonEmptyString("Enter the employee ID: ");
					String department = readNonEmptyString("Enter the department: ");

					participant = new Professor(name, email, employeeId, department);
					break;
				case 2:
					String enrollmentId = readNonEmptyString("Enter the enrollment ID: ");

					participant = new Student(name, email, enrollmentId);
					break;
				case 3:
					String guestId = readNonEmptyString("Enter the guest ID: ");

					participant = new Guest(name, email, guestId);
					break;
				}

				if (participant != null) {
					if (isDuplicateId(participant)) {
						ConsolePrinter.printError("A participant with the same ID already exists.");
						continue;
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
				int eventIndex = readValidInt() - 1;

				if (eventIndex < 0 || eventIndex >= allEvents.size()) {
					ConsolePrinter.printError("Invalid event selection.");
					continue;
				}

				Event selectedEvent = allEvents.get(eventIndex);

				System.out.println("Available Participants:");
				List<Participant> allParticipants = participants;
				for (int i = 0; i < allParticipants.size(); i++) {
					System.out.println((i + 1) + ". " + allParticipants.get(i).getName() + "(" + allParticipants.get(i).getParticipantType() + ")");
				}
				System.out.print("Select participant by number: ");
				int participantIndex = readValidInt() - 1;

				if (participantIndex < 0 || participantIndex >= allParticipants.size()) {
					ConsolePrinter.printError("Invalid participant selection.");
					continue;
				}

				Participant selectedParticipant = allParticipants.get(participantIndex);
				selectedEvent.registerParticipant(selectedParticipant);
				break;

			case 4: {
				displayListEvents();
				int reportOption = readValidInt();

				if (reportOption <= 0 || reportOption > 3) {
					ConsolePrinter.printError("Invalid option!");
					continue;
				}

				switch (reportOption) {
				case 1: {
					String reportType = readNonEmptyString("Enter type (Lecture, Course, Workshop, AcademicFair): ");
					eventManager.listEventsByType(reportType);
					break;
				}
				case 2: {
					String reportDate = readNonEmptyString("Enter date (dd/mm/yyyy): ");
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
				int certEventIndex = readValidInt() - 1;
				if (certEventIndex < 0 || certEventIndex >= certEvents.size()) {
					ConsolePrinter.printError("Invalid event selection.");
					continue;
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
				int certPartIndex = readValidInt() - 1;
				if (certPartIndex < 0 || certPartIndex >= eventParticipants.size()) {
					ConsolePrinter.printError("Invalid participant selection.");
					continue;
				}

				displayCertificateMenu();
				int chooseCertificateType = readValidInt();

				if (chooseCertificateType <= 0 || chooseCertificateType > 2) {
					ConsolePrinter.printError("Invalid option!");
					continue;
				}

				Participant certParticipant = eventParticipants.get(certPartIndex);

				switch(chooseCertificateType) {
				case 1: {
					certEvent.generateCertificate(certParticipant);
					break;
				}
				case 2: {
					certEvent.generateCertificate(certParticipant, "PDF");
					break;
				}
				}
				break;
			}
			default: {
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
		System.out.print("\nChoose an option: ");
	}

	private static void displayParticipantMenu() {
		ConsolePrinter.printSectionHeader("Add New Participant");
		System.out.println("Choose the type of participant:");
		System.out.println("1 - Professor");
		System.out.println("2 - Student");
		System.out.println("3 - Guest");
		System.out.print("\nChoose an option: ");
	}

	private static void displayListEvents(){
		ConsolePrinter.printSectionHeader("List Events (Reports)");
		System.out.println("1 - List by type");
		System.out.println("2 - List by date");
		System.out.println("3 - List all events");
		System.out.println("4 - Main menu");
		System.out.print("Choose an option: ");
	}
	private static void displayCertificateMenu() {
		ConsolePrinter.printSectionHeader("Generate certificate");
		System.out.println("1: Certificate in console");
		System.out.println("2: Certificate in PDF");
		System.out.print("Choose an option: ");
	}
	private String readNonEmptyString(String prompt) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();
			if (!input.isEmpty()) return input;
			ConsolePrinter.printError("Input cannot be empty.");
		}
	}
	private int readValidInt() {
		while (true) {
			String input = scanner.nextLine().trim();
			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				ConsolePrinter.printError("Invalid number. Please enter a valid integer.");
			}
		}
	}

	private int readValidInt(String prompt) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();
			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				ConsolePrinter.printError("Invalid number. Please enter a valid integer.");
			}
		}
	}

	private EventMode readEventMode(String prompt) {
		while (true) {
			System.out.print(prompt + " (IN_PERSON / ONLINE): ");
			String input = scanner.nextLine().trim().toUpperCase();
			try {
				return EventMode.valueOf(input);
			} catch (IllegalArgumentException e) {
				ConsolePrinter.printError("Invalid mode. Use IN_PERSON or ONLINE.");
			}
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



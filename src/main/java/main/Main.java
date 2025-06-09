package main;

import java.util.*;
import util.ConsolePrinter;

import participants.*;
import events.*;


public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ConsolePrinter.init();
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = readUserChoice();

            switch (choice) {
                case 1:
                    displayEventMenu();
                    int choiceEvent = readUserChoice();

                    System.out.print("Digite o nome do evento: ");
                    String title = scanner.nextLine();
                        
                    System.out.print("Digite a data do evento (dd/mm/yyyy): ");
                    String date = scanner.nextLine();

                    System.out.print("Digite o local do evento: ");
                    String location = scanner.nextLine();

                    System.out.print("Digite o modo do evento (IN_PERSON/ONLINE): ");
                    String modeImput = scanner.nextLine();

                    switch (choiceEvent) {
                        case 1: 
                            System.out.print("Digite o nome do palestrante: ");
                            String speaker = scanner.nextLine();
                            event = new Lecture(title, date, location, capacity, description, mode, speaker);
                            break;
                        case 2:
                            System.out.print("Digite o número de aulas: ");
                            int numberOfLessons = scanner.nextInt();
                            event = new Course(title, date, location, capacity, description, mode, numberOfLessons);
                            break;
                        case 3:
                            System.out.print("Digite o tema principal: ");
                            int mainTopic = scanner.nextInt();
                            event = new AcademicFair(title, date, location, capacity, description, mode, mainTopic);
                            break;
                        case 4:
                            System.out.print("Digite a duração do curso (em horas): ");
                            String durationInHours = scanner.nextLine();
                            event = new Workshop(title, date, location, capacity, description, mode, durationInHours);
                            break;
                        default:
                            System.out.println("Opção inválida!");
                            return;
                    }
                    if(event != NULL){
                        EventManager.addEvent(event);
                    }
                    break;
                case 2:
                
                    int choiceParticipant = readUserChoice();

                    System.out.print("Digite o nome do participante: ");
                    String name = scanner.nextLine();
                        
                    System.out.print("Digite o email do participante: ");
                    String email = scanner.nextLine();

                    switch (choiceParticipant) {
                        case 1: 
                            System.out.print("Digite o employee Id: ");
                            String employeeId = scanner.nextLine();

                            System.out.print("Digite o departamento: ");
                            String department = scanner.nextLine();

                            participant = new Professor(name, email, employeeId, department);
                            break;
                        case 2:
                            System.out.print("Digite o enrollment Id: ");
                            String enrollmentId = scanner.nextLine();

                            participant = new Student(name, email, enrollmentId);
                            break;
                        case 3:
                            System.out.print("Digite o guest Id: ");
                            String guestId = scanner.nextLine();

                            participant = new Guest(name, email, guestId);
                            break;
                        default:
                            System.out.println("Opção inválida!");
                            return;
                    }
                    if(participant != NULL){
                        Participant.addParticipant(participant);
                    }
                    break;
                case 3:
                    ConsolePrinter.printSectionHeader("Register Participant in Event");
                    // TODO: Implement the logic to register a participant here.
                    break;
                case 4:
                    ConsolePrinter.printSectionHeader("List Events (Reports)");
                    // TODO: Implement the logic to show event reports here.
                    break;
                case 5:
                    ConsolePrinter.printSectionHeader("Generate Certificate");
                    // TODO: Implement the logic to generate a certificate here.
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    ConsolePrinter.printError("Invalid option. Please try again.");
                    break;
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
        ConsolePrinter.printText("1: Add New Event ");
        ConsolePrinter.printText("2: Add New Participant ");
        ConsolePrinter.printText("3: Register Participant in Event ");
        ConsolePrinter.printText("4: List Events (Reports) ");
        ConsolePrinter.printText("5: Generate Certificate ");
        ConsolePrinter.printText("0: Exit");
        System.out.print("\nChoose an option: ");
    }

    private static void displayEventMenu() {
        ConsolePrinter.printSectionHeader("Add New Participant");
        System.out.println("Escolha o tipo de participante:");
        System.out.println("1 - Professor (Lecture)");
        System.out.println("2 - Aluno");
        System.out.println("3 - Convidado");
        System.out.println("0 - Voltar");
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
}
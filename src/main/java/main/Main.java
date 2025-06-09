package main;

import java.util.*;
import util.ConsolePrinter;


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
                    ConsolePrinter.printSectionHeader("Add New Event");
                    // TODO: Implement the logic to add a new event here.
                    break;
                case 2:
                    ConsolePrinter.printSectionHeader("Add New Participant");
                    // TODO: Implement the logic to add a new participant here.
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
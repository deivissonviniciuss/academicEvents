package events;


import java.util.ArrayList;
import java.util.List;
import util.ConsolePrinter;

public class EventManager {
    private List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        events.add(event);
    }

    private void printEventInfo(Event event) {
        System.out.println();
        System.out.println("Title: " + event.getTitle());
        System.out.println("Type: " + event.getEventType());
        System.out.println("Date: " + event.getDate());
        System.out.println("Location: " + event.getLocation());
        System.out.println("Capacity: " + event.getCapacity());
        System.out.println("Description: " + event.getDescription());
        System.out.println("Mode: " + event.getMode());
        System.out.println(event.getSpecificInfoEvent()); // informação específica da subclasse
        System.out.println();
    }

    private void printEventInfo(Event event, String text) {
        System.out.println("Title: " + event.getTitle());
        System.out.println(event.getSpecificInfoEvent());
        System.out.println(text);
    }

    public void listEventsByType(String type) {
        ConsolePrinter.printTitle("Event Report by Type: " + type);
        int count = 0;
        
        for (Event event : this.events) {
            if (event.getEventType().equalsIgnoreCase(type)) {
                printEventInfo(event);
                count++;
            }
        }
        
        ConsolePrinter.printSeparator();
        if (count > 0) {
            ConsolePrinter.printSuccess("Found " + count + " event(s) matching the criteria (List events by type "+ type + ").");
        } else {
            ConsolePrinter.printError("No events found for the specified type.");
        }
        ConsolePrinter.printSeparator();
    }

    public void listEventsByDate(String date) {
        ConsolePrinter.printTitle("Event Report by Date: " + date);
        int count = 0;
        
        for (Event event : this.events) {
            if (event.getDate().equalsIgnoreCase(date)) {
                printEventInfo(event);
                count++;
            }
        }
        
        ConsolePrinter.printSeparator();

        if (count > 0) {
            ConsolePrinter.printSuccess("Found " + count + " event(s) matching the criteria (List events by date " + date + ").");
        } else {
            ConsolePrinter.printError("No events found for the specified date.");
        }

        ConsolePrinter.printSeparator();
    }

    public List<Event> getAllEvents() {
        return events;
    }
}

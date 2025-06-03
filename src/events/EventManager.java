package events;

import java.util.ArrayList;
import java.util.List;

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

    public void listEventsByType(String type) {
        System.out.println("=== Events of type: " + type + " ===");
        for (Event event : events) {
            if (event.getEventType().equalsIgnoreCase(type)) {
                printEventInfo(event);
            }
        }
    }

    public void listEventsByDate(String date) {
        System.out.println("=== Events on date: " + date + " ===");
        for (Event event : events) {
            if (event.getDate().equalsIgnoreCase(date)) {
                printEventInfo(event);
            }
        }
    }

    public List<Event> getAllEvents() {
        return events;
    }
}

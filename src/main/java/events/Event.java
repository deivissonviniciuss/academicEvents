package events;

import participants.Participant;
import util.ConsolePrinter;
import java.util.List;
import java.util.ArrayList;

public abstract class Event implements Certifiable
{
    private static int eventCount = 0;

    protected String title;
    protected String date;
    protected String location;
    protected int capacity;
    protected String description;
    protected EventMode mode;

    protected List<Participant> participants = new ArrayList<>();

    public Event(String title, String date, String location, int capacity, String description, EventMode mode){
        this.title = title;
        this.date = date;
        this.location = location;
        this.capacity = capacity;
        this.description = description;
        this.mode = mode;
        eventCount++;
    }

    public static int getEventCount() {
        return eventCount;
    }

    public List<Participant> getParticipants(){
        return participants;
    }
    public String getTitle(){
        return title;
    }

    public String getDate(){
        return date;
    }

    public String getLocation(){
        return location;
    }

    public int getCapacity(){
        return capacity;
    }

    public String getDescription(){
        return description;
    }

    public String getEventType(){
        return this.getClass().getSimpleName();
    }

    public EventMode getMode() {
        return mode;
    }

    protected abstract String getSpecificInfoEvent();

    public void registerParticipant(Participant participant){
        if(participants.size() >= capacity){
            System.out.println(getEventType() + " is full. Cannot register " + participant.getName() + " ("+ participant.getParticipantType()+").");
            return;
        }

        if (mode == EventMode.ONLINE) {
            System.out.println("Participant " + participant.getName() + "("+ participant.getParticipantType()+")" + " registered successfully for the " + getEventType() + "("+ getMode() +").");
            System.out.println("A link will be sent to the participant's email.");
            participants.add(participant);
            return;
        } else if (mode == EventMode.IN_PERSON) {
            participants.add(participant);
            System.out.println("Participant " + participant.getName()+ "("+ participant.getParticipantType()+")" + " registered successfully for the " + getEventType() + "("+ getMode() +").");
        } else {
            System.out.println("Error: Unknown event mode.");
        }
    }
    
    @Override
    public void generateCertificate(Participant participant) {
        if (!participants.contains(participant)) {
            ConsolePrinter.printError("This participant is not registered for this event.");
            return;
        }

        ConsolePrinter.printSeparator();
        ConsolePrinter.printSectionHeader("\n----- CERTIFICATE OF PARTICIPATION -----");
        
        ConsolePrinter.printText("This is to certify that the participant below:");
        
        ConsolePrinter.printInfo("Name", participant.getName());
        ConsolePrinter.printInfo("Contact (Email)", participant.getEmail());
        ConsolePrinter.printInfo("Participant Details", participant.getSpecificInfoParticipant());
        
        ConsolePrinter.printText("\nhas successfully participated in the event:");
        
        ConsolePrinter.printInfo("Event", this.title);
        ConsolePrinter.printInfo("Type", this.getEventType());
        ConsolePrinter.printInfo("Date", this.date);
        ConsolePrinter.printInfo("Location", this.location);
        ConsolePrinter.printInfo("Event Details", this.getSpecificInfoEvent());
        
        ConsolePrinter.printSeparator();
    }
    
}
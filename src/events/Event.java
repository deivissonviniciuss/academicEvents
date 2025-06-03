package events;

import participants.Participant;
import java.util.List;
import java.util.ArrayList;

public abstract class Event
{
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
   
    public void generateCertificate(Participant participant){
        if (!(participants.contains(participant))) {
            System.out.println("This participant is not registered in this event.");
            return;
        }

        System.out.println("\n----- CERTIFICATE -----");
        System.out.println("This certifies that " + participant.getName() + " has successfully participated in the " + getEventType() + ":");
        System.out.println("Title: " + title);
        System.out.println("Mode of event: " + getMode());
        System.out.println("Date: " + date);
        System.out.println("Location: " + location);
        System.out.println(getSpecificInfoEvent());
        System.out.println(participant.getSpecificInfoParticipant());
        System.out.println(participant.getEmail());
        System.out.println("------------------------\n");
    }
    
}
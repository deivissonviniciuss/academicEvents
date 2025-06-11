package br.edu.ifba.inf008.events;

import br.edu.ifba.inf008.participants.Participant;
import br.edu.ifba.inf008.participants.Student;

public class Course extends Event{
    private int numberOfLessons;

    public Course(String title, String date, String location, int capacity, String description, EventMode mode, int numberOfLessons){
        super(title, date, location, capacity, description, mode);
        this.numberOfLessons = numberOfLessons;
    }

    @Override
    public void registerParticipant(Participant participant){
        if (participants.contains(participant)) {
            System.out.println("Participant " + participant.getName() + " is already registered in this event.");
            return;
        }

        if(!(participant instanceof Student)){
            System.out.println("Only students can register for a " + getEventType() + ". Cannot register " + participant.getName() + " ("+ participant.getParticipantType()+").");
            return;
        }

        if(participants.size() >= capacity){
            System.out.println("Course is full. Cannot register "+ participant.getName() + ".");
            return;
        }

        if (mode == EventMode.ONLINE) {
            participants.add((Student) participant);
            System.out.println("Student " + participant.getName() + " registred successfully for the Course" + "("+ getMode() +").");
            System.out.println("Participant " + participant.getName() + "("+ participant.getParticipantType()+")" + " registered successfully for the " + getEventType() + "("+ getMode() +").");
            System.out.println("A link will be sent to the participant's email.");

            int remaining = capacity - participants.size();
            System.out.println("Remaining slots: " + remaining);
            return;
        } else if (mode == EventMode.IN_PERSON) {
            participants.add((Student) participant);
            System.out.println("Student " + participant.getName() + " registred successfully for the Course" + "("+ getMode() +").");
            System.out.println("Participant " + participant.getName()+ "("+ participant.getParticipantType()+")" + " registered successfully for the " + getEventType() + "("+ getMode() +").");
            
            int remaining = capacity - participants.size();
            System.out.println("Remaining slots: " + remaining);
        } else {
            System.out.println("Error: Unknown event mode.");
        }
    }

    @Override
    public String getSpecificInfoEvent(){
        return "Number of lessons: " + numberOfLessons;
    }

    
}


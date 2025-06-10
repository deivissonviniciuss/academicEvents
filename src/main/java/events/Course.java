package events;

import java.util.*;
import participants.Participant;
import participants.Student;

public class Course extends Event{
    private int numberOfLessons;

    public Course(String title, String date, String location, int capacity, String description, EventMode mode, int numberOfLessons){
        super(title, date, location, capacity, description, mode);
        this.numberOfLessons = numberOfLessons;
    }

    @Override
    public void registerParticipant(Participant participant){
        if(!(participant instanceof Student)){
            System.out.println("Only students can register for a " + getEventType() + ". Cannot register " + participant.getName() + " ("+ participant.getParticipantType()+").");
            return;
        }

        if(participants.size() >= capacity){
            System.out.println("Course is full. Cannot register "+ participant.getName() + ".");
            return;
        }

        participants.add((Student) participant);
        System.out.println("Student " + participant.getName() + " registred successfully for the Course" + "("+ getMode() +").");
    }

    @Override
    public String getSpecificInfoEvent(){
        return "Number of lessons: " + numberOfLessons;
    }

    
}

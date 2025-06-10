package participants;

import java.util.*;

public abstract class Participant {
    protected List<Participant> participants = new ArrayList<>();
    protected String name;
    protected String email;

    public Participant(String name, String email){
        this.name = name;
        this.email = email;
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getParticipantType(){
        return getClass().getSimpleName();
    }

    public abstract String getSpecificInfoParticipant();

}

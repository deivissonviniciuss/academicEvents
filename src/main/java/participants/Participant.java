package participants;

public abstract class Participant {
    protected String name;
    protected String email;

    public Participant(String name, String email){
        this.name = name;
        this.email = email;
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

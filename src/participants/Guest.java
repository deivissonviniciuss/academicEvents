package participants;

public class Guest extends Participant{
    private String guestId;

    public Guest(String name, String email, String guestId){
        super(name, email);
        this.guestId = guestId;
    }

    public String getGuestId(){
        return guestId;
    }

    @Override
    public String getSpecificInfoParticipant(){
        return "Guest ID: " + guestId;
    }
}

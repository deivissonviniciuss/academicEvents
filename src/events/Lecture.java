package events;

public class Lecture extends Event{
    private String speaker;

    public Lecture(String title, String date, String location, int capacity, String description, EventMode mode, String speaker){
        super(title, date, location, capacity, description, mode);
        this.speaker = speaker;
    }

    @Override
    protected String getSpecificInfoEvent() {
        return "Speaker: " + speaker;
    }


}

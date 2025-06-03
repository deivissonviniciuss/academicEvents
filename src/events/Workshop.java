package events;

public class Workshop extends Event
{
    private int durationInHours;

    public Workshop(String title, String date, String location, int capacity, String description, EventMode mode, int durationInHours){
        super(title, date, location, capacity, description, mode);
        this.durationInHours = durationInHours;
    }

    @Override
    public String getSpecificInfoEvent(){
        return "Duration in hours: " + durationInHours + " hours";
    }

}

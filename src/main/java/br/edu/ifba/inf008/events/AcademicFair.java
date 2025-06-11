package br.edu.ifba.inf008.events;

public class AcademicFair extends Event
{
    private String mainTopic;

    public AcademicFair(String title, String date, String location, int capacity, String description, EventMode mode, String mainTopic){
        super(title, date, location, capacity, description, mode);
        this.mainTopic = mainTopic;
    }

    @Override
    public String getSpecificInfoEvent(){
        return "Main topic: " + mainTopic;
    }
       
            
}

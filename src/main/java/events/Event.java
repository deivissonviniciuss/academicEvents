package events;

import participants.Participant;
import util.ConsolePrinter;
import java.util.List;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;

public abstract class Event implements ICertifiable
{
    protected List<Participant> participants = new ArrayList<>();

    protected String title;
    protected String date;
    protected String location;
    protected int capacity;
    protected String description;
    protected EventMode mode;

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
        if (participants.contains(participant)) {
            System.out.println("Participant " + participant.getName() + " is already registered in this event.");
            return;
        }

        if(participants.size() >= capacity){
            System.out.println(getEventType() + " is full. Cannot register " + participant.getName() + " ("+ participant.getParticipantType()+").");
            return;
        }

        if (mode == EventMode.ONLINE) {
            participants.add(participant);
            System.out.println("Participant " + participant.getName() + "("+ participant.getParticipantType()+")" + " registered successfully for the " + getEventType() + "("+ getMode() +").");
            System.out.println("A link will be sent to the participant's email.");

            int remaining = capacity - participants.size();
            System.out.println("Remaining slots: " + remaining);
            return;
        } else if (mode == EventMode.IN_PERSON) {
            participants.add(participant);
            System.out.println("Participant " + participant.getName()+ "("+ participant.getParticipantType()+")" + " registered successfully for the " + getEventType() + "("+ getMode() +").");
            
            int remaining = capacity - participants.size();
            System.out.println("Remaining slots: " + remaining);
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

    public void generateCertificate(Participant participant, String text) {
        if (!this.participants.contains(participant)) {
            ConsolePrinter.printError("This participant is not registered for this event.");
            return;
        }

        String fileName = "Certificate-" + participant.getName().replace(" ", "_") + ".pdf";

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            float y = 700;
            float margin = 50;

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
            contentStream.newLineAtOffset(margin, y);
            contentStream.showText("CERTIFICATE OF PARTICIPATION");
            contentStream.endText();

            y -= 50;

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(margin, y);
            contentStream.showText("This is to certify that " + participant.getName() + " has successfully participated in the event:");
            contentStream.endText();

            y -= 30;

            // Informações do evento
            String[] eventDetails = {
                "Title: " + this.title,
                "Date: " + this.date,
                "Location: " + this.location,
                "Mode: " + this.mode,
                "Capacity: " + this.capacity,
                "Description: " + this.description,
                "Event Type: " + this.getEventType(),
                "Specific Info: " + this.getSpecificInfoEvent()
            };

            for (String line : eventDetails) {
                y -= 20;
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(margin, y);
                contentStream.showText(line);
                contentStream.endText();
            }

            y -= 30;

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(margin, y);
            contentStream.showText("Participant Info: ");
            contentStream.endText();

            y -= 20;

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(margin, y);
            contentStream.showText(participant.getSpecificInfoParticipant());
            contentStream.endText();

            contentStream.close();
            document.save(fileName);

            ConsolePrinter.printSuccess("PDF Certificate generated successfully: " + fileName);

        } catch (IOException e) {
            ConsolePrinter.printError("Failed to create PDF certificate: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
}
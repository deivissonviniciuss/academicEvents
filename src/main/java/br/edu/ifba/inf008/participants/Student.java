package br.edu.ifba.inf008.participants;

public class Student extends Participant
{
    private String enrollmentNumber;

    public Student(String name, String email, String enrollmentNumber){
        super(name, email);
        this.enrollmentNumber = enrollmentNumber;
    }

    public String getEnrollmentNumber(){
        return enrollmentNumber;
    }

    @Override
    public String getSpecificInfoParticipant(){
        return "Enrollment Number: " + enrollmentNumber;
    }
}

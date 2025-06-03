package participants;

public class Professor extends Participant
{
    private String employeeId;
    private String department;

    public Professor(String name, String email, String employeeId, String department){
        super(name, email);
        this.employeeId = employeeId;
        this.department = department;
    }

    public String getEmployeeId(){
        return employeeId;
    }

    public String getDepartament(){
        return department;
    }

    @Override
    public String getSpecificInfoParticipant(){
        return "Employee ID: " + employeeId + ". Department: " + department;
    }
}

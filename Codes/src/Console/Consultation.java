package Console;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation extends Patient {
    private String doctor;

    private LocalDate Date;
    private LocalTime startTime;
    private LocalTime endTime;
    private double cost;
    private String note;

    public Consultation(String firstname, String surname, LocalDate date_of_birth, String mobileNo,String patient_ID,String doctor,  LocalDate Date,LocalTime startTime,LocalTime endTime, double cost, String note) {
        super(firstname,surname,date_of_birth,mobileNo,patient_ID);
        this.doctor=doctor;
        this.Date = Date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
        this.note = note;
    }
    public String getDoctor(){
        return doctor;
    }

    public void setDoctor(String doctor){
        this.doctor = doctor;
    }

    public LocalDate getDate(){
        return Date ;
    }
    public void setDate(LocalDate Date){
        this.Date = Date;
    }
    public LocalTime getStartTime(){
        return startTime;
    }
    public void setStartTime(LocalTime startTime){
        this.startTime = startTime;
    }
    public  LocalTime getEndTime(){
        return endTime;
    }
    public void setEndTime(LocalTime endTime){
        this.endTime = endTime;
    }
    public String getNote(){
        return note;
    }
    public void setNote(String note){
        this.note = note;
    }
    public double getCost(){
        return cost;
    }
    public void setCost(double cost){
        this.cost = cost;
    }

    public String toString() {
        return ("Full Name               : " + getFirstname()+ " "  + getSurname() + "\n" +
                "patient ID number        : " + getPatient_ID() + "\n" +
                "Doctor assigned         : " + getDoctor() + "\n" +
                "Mobile number           : " + getMobileNo() + "\n" +
                "start time              : " + getStartTime() + "\n" +
                "end time                : " + getEndTime() + "\n" +
                "Date of Birth           : " + getDate_of_birth() + "\n"+
                "consultation date       : " + getDate() + "\n"+
                "Cost                    : " + getCost() + "\n"+
                "Additional note         : " + getNote() + "\n");
    }
}

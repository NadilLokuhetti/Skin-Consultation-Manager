package Console;

import java.io.Serializable;
import java.time.LocalDate;

public class Patient extends Person implements Serializable {

    private String patient_ID;

    public Patient(String firstname, String surname, LocalDate date_of_birth, String mobileNo,String patient_ID) {
        super(firstname, surname, date_of_birth, mobileNo);
        this.patient_ID = patient_ID;
    }

    public String getPatient_ID(){
        return patient_ID;
    }
    public void  setPatient_ID(String patient_ID){
        this.patient_ID = patient_ID;
    }
}

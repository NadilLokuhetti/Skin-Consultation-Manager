package Console;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {
    Doctor doctor = new Doctor("Nadil","lokuhetti","1232A2","Dentist",LocalDate.parse("2004-05-23"),"0783788167");

    @Test
    void getMedicalLicenseNumber() {
        assertEquals("1232A2",doctor.getMedicalLicenseNumber());
    }

    @Test
    void getSpecialization() {
        assertEquals("Dentist",doctor.getSpecialization());
    }

    @Test
    void setMedicalLicenseNumber() {
        doctor.setMedicalLicenseNumber("5656B");
        assertEquals("5656B",doctor.getMedicalLicenseNumber());
    }

    @Test
    void setSpecialization() {
        doctor.setSpecialization("Psychologist");
        assertEquals("Psychologist",doctor.getSpecialization());
    }


}
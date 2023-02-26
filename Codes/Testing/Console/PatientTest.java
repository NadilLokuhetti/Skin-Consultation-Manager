package Console;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    private Patient patient = new Patient("Nadil","lokuhetti", LocalDate.parse("2004-05-23"),"0783788167","123456789");

    @Test
    void getPatient_ID() {
        assertEquals("123456789",patient.getPatient_ID());
    }

    @Test
    void setPatient_ID() {
        patient.setPatient_ID("112244558");
        assertEquals("112244558",patient.getPatient_ID());
    }
}
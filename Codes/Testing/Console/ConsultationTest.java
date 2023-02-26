package Console;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ConsultationTest {
    private final Consultation consultation = new Consultation("Nadil","Lokuhetti", LocalDate.parse("2004-05-23"),"0783788167","123456789","NadilLOkuhetti",LocalDate.parse("2023-03-03"), LocalTime.parse("08:00"),LocalTime.parse("09:00"),Double.parseDouble("30.00"),"Hello");

    @Test
    void getDoctor() {
        assertEquals("NadilLOkuhetti",consultation.getDoctor());
    }

    @Test
    void setDoctor() {
        consultation.setDoctor("Harry patrick");
        assertEquals("Harry patrick",consultation.getDoctor());
    }

    @Test
    void getDate() {
        assertEquals(LocalDate.parse("2023-03-03"),consultation.getDate());
    }

    @Test
    void setDate() {
        consultation.setDate(LocalDate.parse("2023-05-10"));
        assertEquals(LocalDate.parse("2023-05-10"),consultation.getDate());
    }

    @Test
    void getStartTime() {
        assertEquals(LocalTime.parse("08:00"),consultation.getStartTime());
    }

    @Test
    void setStartTime() {
        consultation.setStartTime(LocalTime.parse("10:30"));
        assertEquals(LocalTime.parse("10:30"),consultation.getStartTime());
    }

    @Test
    void getEndTime() {
        assertEquals(LocalTime.parse("09:00"),consultation.getEndTime());
    }

    @Test
    void setEndTime() {
        consultation.setEndTime(LocalTime.parse("12:00"));
        assertEquals(LocalTime.parse("12:00"),consultation.getEndTime());
    }

    @Test
    void getNote() {
        assertEquals("Hello",consultation.getNote());
    }

    @Test
    void setNote() {
        consultation.setNote("Hello world");
        assertEquals("Hello world",consultation.getNote());
    }

    @Test
    void getCost() {
        assertEquals(Double.parseDouble("30.00"),consultation.getCost());
    }

    @Test
    void setCost() {
        consultation.setCost(Double.parseDouble("100.00"));
        assertEquals(Double.parseDouble("100.00"),consultation.getCost());
    }
}
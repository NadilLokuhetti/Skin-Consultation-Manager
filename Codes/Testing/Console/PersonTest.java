package Console;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private Person person = new Person("Nadil","lokuhetti", LocalDate.parse("2004-05-23"),"0783788167");

    @Test
    void getFirstname() {
        assertEquals("Nadil",person.getFirstname());
    }

    @Test
    void setFirstname() {
        person.setFirstname("Harry");
        assertEquals("Harry",person.getFirstname());
    }

    @Test
    void getSurname() {
        assertEquals("lokuhetti",person.getSurname());
    }

    @Test
    void setSurname() {
        person.setSurname("patrick");
        assertEquals("patrick",person.getSurname());
    }

    @Test
    void getDate_of_birth() {
        assertEquals(LocalDate.parse("2004-05-23"),person.getDate_of_birth());
    }

    @Test
    void setDate_of_birth() {
        person.setDate_of_birth(LocalDate.parse("2003-03-03"));
        assertEquals(LocalDate.parse("2003-03-03"),person.getDate_of_birth());
    }

    @Test
    void getMobileNo() {
        assertEquals("0783788167",person.getMobileNo());
    }

    @Test
    void setMobileNo() {
        person.setMobileNo("0729227359");
        assertEquals("0729227359",person.getMobileNo());
    }
}
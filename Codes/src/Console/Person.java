package Console;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
    private String firstname;
    private String surname;
    private LocalDate date_of_birth;
    private String mobileNo;

    public Person(String firstname, String surname, LocalDate date_of_birth, String mobileNo){
        this.firstname = firstname;
        this.surname=surname;
        this.date_of_birth = date_of_birth;
        this.mobileNo = mobileNo;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}

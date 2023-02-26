package Console;


import java.time.LocalDate;

public class Doctor extends Person {
   private String medicalLicenseNumber;
   private String specialization;

   public Doctor(String firstname, String surname, String medicalLicenseNumber, String specialization, LocalDate dateOfBirth, String mobileNo)
   {
      super(firstname, surname, dateOfBirth,mobileNo);
      this.medicalLicenseNumber = medicalLicenseNumber;
      this.specialization = specialization;
   }


   public String getMedicalLicenseNumber() {
      return medicalLicenseNumber;
   }

   public String getSpecialization(){
      return specialization;
   }

   public void setMedicalLicenseNumber(String medicalLicenseNumber) {
      this.medicalLicenseNumber = medicalLicenseNumber;
   }

   public void setSpecialization(String specialization) {
      this.specialization = specialization;
   }

   public void setDetails(String firstname, String surname,String medicalLicenseNumber,String specialization, LocalDate dateOfBirth, String mobileNo)
   {
      super.setFirstname(firstname);
      super.setSurname(surname);
      super.setDate_of_birth(dateOfBirth);
      super.setMobileNo(mobileNo);
      this.specialization = specialization;
      this.medicalLicenseNumber = medicalLicenseNumber;
   }

   public String toString() {
      return ("Full Name               : " + getFirstname()+ " "  + getSurname() + "\n" +
              "Medical license number  : " + getMedicalLicenseNumber() + "\n" +
              "Specialization          : " + getSpecialization() + "\n" +
              "Mobile number           : " + getMobileNo() + "\n" +
              "Date of Birth           : " + getDate_of_birth() + "\n");
   }

}

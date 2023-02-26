package Console;

import GUI.Main;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    /*Creating Linked list*/
    public final static LinkedList<Doctor> doctorList = new LinkedList<>();
    public  static ArrayList<Consultation> patientList = new ArrayList<>();
    public static WestminsterSkinConsultationManager skinConsultationCentre = new WestminsterSkinConsultationManager();


    public static void main(String[] args) {
        skinConsultationCentre.loadProgram(doctorList);
        boolean menu = true;
        while (menu) {
            /*loading the text file*/
            skinConsultationCentre.stars();
            /*displaying the menu to the user*/
            System.out.println("\nWestminsterSkinConsultationManager Menu\n");
            System.out.println("\t\tEnter 1 or Add to add a doctor.");
            System.out.println("\t\tEnter 2 or del to delete a doctor.");
            System.out.println("\t\tEnter 3 or pri to print the doctors list.");
            System.out.println("\t\tEnter 4 or sav to save the information to the text file.");
            System.out.println("\t\tEnter 5 or gui to open the GUI");
            System.out.println("\t\tEnter 0 or ext to exit the program");

            /*Taking the user input for the options above*/
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your option! : ");
            String option = scanner.next();

            switch (option) {
                case "1":
                case "Add":
                    System.out.println("\tAdd the new doctor to the list");
                    skinConsultationCentre.AddDoctor(doctorList);
                    break;
                case "2":
                case "del":
                    System.out.println("Delete the doctor from the list");
                    skinConsultationCentre.DeleteDoctor(doctorList);
                    break;
                case "3":
                case "pri":
                    System.out.println("The list of doctors ");
                    skinConsultationCentre.printList(doctorList);
                    break;
                case "4":
                case "sav":
                    System.out.println("The list of doctors has successfully printed in the text file");
                    skinConsultationCentre.saveProgram(doctorList);
                    break;
                case "5":
                case "gui":
                    System.out.println("GUI");
                    new Main();
                    break;
                case "0":
                case "ext":
                    System.out.println("You have exited the program");
                    menu = false;
                    skinConsultationCentre.saveProgram(doctorList);
            }
        }
    }
    /* Adding doctors to the array with the validation statements*/
    @Override
    public  void AddDoctor(LinkedList<Doctor> doctorList) {
        if (doctorList.size() < 10) {
            /*Checking the validation in user inputs*/
            String firstName = Validation("Enter Doctors name:", "\nincorrect! First name only should contain letters and cannot be empty, ", "[a-zA-Z]+");
            String surname = Validation("Enter Doctors surname:", "\nincorrect! surname only should contain letters and cannot be empty, ", "[a-zA-Z]+");
            String medicalLicenseNumber = Validation("Enter the medical license:", "\nmedical license cannot be empty, ", "[a-zA-Z0-9]+");
            /* Checking whether the medical license is only used once*/
            for(int i = 0; i < doctorList.size(); i++) {
                if(MedicalLicenseNumber(doctorList, medicalLicenseNumber)) {
                    break;
                } else {
                    System.out.println("Medical license number already exists in the system. \n");
                    medicalLicenseNumber = Validation("doctor's medical license number: ",
                            "Medical license number", "[a-zA-Z0-9]+");

                }
            }
            String specialization = Validation("Enter the Doctors specialization:", "\nSpecialization only should contain letters and cannot be empty, ", "[a-zA-Z]+");
            LocalDate date_of_brith = DOB_validation("Enter Doctors date of birth in (YYYY-MM-DD):", "invalid input!");
            String mobileNO = Validation("Enter the mobile Number:", "The mobile no can only have 10 digits and cannot be empty", "[0-9]{10}");

            doctorList.add(new Doctor(firstName, surname, medicalLicenseNumber, specialization, date_of_brith, mobileNO));
            System.out.println("\nDr." + firstName + " " + surname + " added to skin consultation centre" +
                    " successfully!\n");
        } else {
            System.out.println("\n\tDoctor list is full!\n\n");
        }



    }
    @Override
    /*Deleting a doctor for the linked list*/
    public  void DeleteDoctor(LinkedList<Doctor> doctorList) {
        Scanner input = new Scanner(System.in);
        String choice;
        if (doctorList.size() != 0) {
            String medicalLicenseNumber = Validation("Enter medical license number of the doctor you want to remove: ",
                    "Check the Medical license number", "[a-zA-Z0-9]+");
            //checking the medical id number whether it is equal to the user input
            for (int i = 0; i < doctorList.size(); i++) {
                if (doctorList.get(i).getMedicalLicenseNumber().equals(medicalLicenseNumber)) {
                    System.out.println("\nDetails of deleted doctor \n" + doctorList.get(i));
                    doctorList.remove(i);
                    System.out.println("Number of doctors in the skin consultation centre: " + doctorList.size());
                    System.out.println();
                    break;

                } else if (i == doctorList.size() - 1) {
                    System.out.println("Doctor not found! Please re-check existing medical license numbers\n");
                    break;
                }
            }
            //Asks user to delete another doctor or exit the program
            System.out.print("Press 'del' to remove a doctor from the list once more, or any key to leave :");
            choice = input.nextLine();
            System.out.println();
            if (Objects.equals(choice, "del")){
                DeleteDoctor(doctorList);

            }
        }else{
            System.out.println("\nThere are no doctors present in the skin consultation center.!\n");
        }

    }
    /*Displaying the doctors available  in the linked list, by sorting there second name in alphabetical order*/
    @Override
    public  void printList(LinkedList<Doctor> doctorList){
        Doctor[] sort_list = new Doctor[doctorList.size()];
        doctorList.toArray(sort_list);
        Arrays.sort(sort_list, Comparator.comparing(Doctor::getSurname));
        for (Doctor doctor : sort_list) System.out.println(doctor);

//        Consultation[] temp = new Consultation[patientList.size()];
//        patientList.toArray(temp);
//        Arrays.sort(temp, Comparator.comparing(Consultation::getSurname));
//        for (Consultation consultation : temp) System.out.println(consultation);
    }
    /*method to save the doctors and patients into the text file.*/
    @Override
    public void saveProgram(LinkedList<Doctor> doctorList) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Doctor_list.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (Doctor doctor : doctorList) {
                objectOutputStream.writeObject(doctor);
            }

            objectOutputStream.close();

        } catch (IOException e) {
            System.out.println("Error saving progress!!!\n" + e);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("consultation.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (Consultation consultation : patientList) {
                objectOutputStream.writeObject(consultation);
            }

            objectOutputStream.close();

            System.out.println("Program saved successfully!\n");

        } catch (IOException e) {
            System.out.println("Error saving progress!!!\n" + e);
        }
    }
    /*this method is used to load data from the text file*/
    @Override
    public  void loadProgram(LinkedList<Doctor> doctorList){
        try {
            FileInputStream fileInputStream = new FileInputStream("Doctor_list.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            while(fileInputStream.available()>0){
                doctorList.add((Doctor) objectInputStream.readObject());
            }

            objectInputStream.close();
            System.out.println("\nProgram successfully loaded!!!\n");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading progress!!!\n" + e);
        }
        try {
            FileInputStream fileInputStream = new FileInputStream("consultation.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            while(fileInputStream.available()>0){
                patientList.add((Consultation) objectInputStream.readObject());
            }

            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading progress!!!\n" + e);
        }
    }
    public LinkedList<Doctor> getDoctorList() {
        return doctorList;
    }


    /*------------------------------------Validation methods-----------------------------------------*/
    /*validating the Strings*/
    /*https://stackoverflow.com/questions/3732809/how-can-a-string-be-validated-in-java*/
    /*this method is used for validation of strings*/
    public static String Validation(String msg, String error, String format) {
        Scanner input = new Scanner(System.in);
        String Name = "";
        boolean StringValidation = true;

        while (StringValidation) {
            System.out.print(msg);
            Name = input.nextLine();

            if (Name.matches(format) && Name.length() > 0) {
                StringValidation = false;
            } else {
                System.out.println(error + " Please enter a again!.");
            }
        }
        return Name;
    }
    /*method for validating the medical license number for doctors*/
    public static boolean MedicalLicenseNumber(LinkedList<Doctor> doctorList, String medicalLicenseNumber) {
        boolean Medical_number = true;

        for (Doctor doctor : doctorList) {
            if (doctor.getMedicalLicenseNumber().equals(medicalLicenseNumber)) {
                Medical_number = false;
                break;
            }
        }
        return Medical_number;
    }
    /*Method for validating the date of brith*/
        public static LocalDate DOB_validation(String msg, String error) {
        Scanner input = new Scanner(System.in);
        LocalDate date_Of_birth = null;
        boolean DOB_validation = true;

        while (DOB_validation) {
            try {
                System.out.print("" + msg);
                date_Of_birth = LocalDate.parse(input.nextLine());

                if (date_Of_birth.isBefore(LocalDate.now())) {
                    DOB_validation = false;
                } else {
                    System.out.println(error + " has to be before today's date!");
                }
            } catch (DateTimeParseException e1) {
                System.out.print("Incorrect format! ");
            }
        }
        return date_Of_birth;
    }
    /*Method to print <> for separating the menu*/
    public void stars(){
        for(int i=0;i<50;i++){
            System.out.print(">");
        }
    }
}


package Console;


import java.util.LinkedList;

public interface SkinConsultationManager {
    void AddDoctor(LinkedList<Doctor> doctorList);
    void DeleteDoctor(LinkedList<Doctor> doctorList);
    void printList(LinkedList<Doctor> doctorList);
    void saveProgram(LinkedList<Console.Doctor> doctorList);
    void loadProgram(LinkedList<Doctor> doctorList);
}

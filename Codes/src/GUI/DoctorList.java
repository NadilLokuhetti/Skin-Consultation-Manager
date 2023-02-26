package GUI;

import Console.WestminsterSkinConsultationManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;



public class DoctorList extends JFrame implements ActionListener {
    WestminsterSkinConsultationManager GUI = new WestminsterSkinConsultationManager();

    JLabel topicLabel;
    JButton Back_btn;
    JButton sortName;
    JTable doctorTable;

    public DoctorList() {
        this.setLayout(null);
        /*Creating the table of doctors*/
        String[] column = {"Name", "Surname", "Medical license No", "Specialization", "Mobile Number", "Date of Birth"};                        //Creating the table.
        DefaultTableModel doctor_table = new DefaultTableModel(column, 0);
        doctorTable = new JTable(doctor_table);
        doctorTable.setAutoCreateRowSorter(true);


        for (int i = 0; i < GUI.getDoctorList().size(); i++) {
            String firstname = GUI.getDoctorList().get(i).getFirstname();
            String surname = GUI.getDoctorList().get(i).getSurname();
            String medicalLicenseNumber = GUI.getDoctorList().get(i).getMedicalLicenseNumber();
            String specialization = GUI.getDoctorList().get(i).getSpecialization();
            String mobileNo = GUI.getDoctorList().get(i).getMobileNo();
            LocalDate date_of_birth = GUI.getDoctorList().get(i).getDate_of_birth();


            Object[] row = {firstname, surname, medicalLicenseNumber, specialization, mobileNo, date_of_birth};
            doctor_table.addRow(row);
        }
        JScrollPane pane = new JScrollPane(doctorTable);
        pane.setBounds(20, 100, 850,300);
        this.add(pane);

        /*Topic of the page.*/
        topicLabel = new JLabel();
        topicLabel.setText("Name of available doctors,");
        topicLabel.setBounds(20, 20, 550, 30);
        topicLabel.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
        topicLabel.setForeground(new Color(5, 35, 234));
        this.add(topicLabel);
        /*Back Button.*/
        Back_btn = new JButton();
        Back_btn.setText("Back");
        Back_btn.setBounds(610, 420, 75, 30);
        Back_btn.setFocusable(false);
        Back_btn.addActionListener(this);
        this.add(Back_btn);
        /*Sort button*/
        sortName = new JButton();
        sortName.setText("Sort Doctors");
        sortName.setBounds(710, 420, 150, 30);
        sortName.setFocusable(false);
        sortName.addActionListener(this);
        this.add(sortName);

        this.setTitle("List of Doctors");
        this.setSize(900, 500);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        JLabel background = new JLabel(new ImageIcon("background.jpeg"));
        background.setBounds(0, 0, 900, 500);
        this.add(background);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Back_btn) {
            this.dispose();
            new Main();
        } else if (e.getSource() == sortName) {
            doctorTable.getRowSorter().toggleSortOrder(1);
        }

    }
}




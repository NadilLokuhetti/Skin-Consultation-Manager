package GUI;

import Console.Consultation;
import Console.WestminsterSkinConsultationManager;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import  static Console.WestminsterSkinConsultationManager.patientList;


public class Add_Consultation extends JFrame implements ActionListener {
    WestminsterSkinConsultationManager GUI = new WestminsterSkinConsultationManager();

    private final JButton button1;
    private final JButton button2;
    private final  JButton button3;
    private final JTextField Name;
    private final JTextField surname;
    private final JTextField DOB;
    private final JTextField patientId;
    private final JTextField date;
    private final JTextField phoneNO;
    private final  JLabel photo_path;
    private final JLabel photo;
    private final JTextArea  Note;
    JComboBox<String> doctor_Name, duration, hour_time, Min_time;
    private String name, Surname, mobileNo, patient_ID, doctorID,note, EndTime;
    private LocalDate dateOfBirth, conDate;
    private LocalTime startTime, endTime;
    int again = 0;


    String[] hours = {"HH", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"};
    String[] min = {"MM", "00", "10", "15", "20", "30", "45"};
    String[] due = {"1", "2", "3", "4", "5"};
    String[] doctorName = new String[GUI.getDoctorList().size()];

    public Add_Consultation() {
        this.setLayout(null);
        //background image of consultation page
        JLabel background = new JLabel(new ImageIcon("background.jpeg"));
        background.setBounds(0, 0, 1100, 600);
        //Topic of the GUI
        JLabel topicLabel = new JLabel();
        label(topicLabel, "<html>Add your consultation<br><br>List of Doctors</html>", 50, 20, 500, 70);
        topicLabel.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
        topicLabel.setForeground(new Color(4, 38, 155));
        //Creating the table to show the doctors who are available
        String[] column = {"Name", "Surname", "Specialization"};
        DefaultTableModel doctor_table = new DefaultTableModel(column, 0);
        JTable doctorTable = new JTable(doctor_table);
        doctorTable.setBounds(20, 150, 350, 200);

        for (int i = 0; i < GUI.getDoctorList().size(); i++) {
            String firstname = GUI.getDoctorList().get(i).getFirstname();
            String surname = GUI.getDoctorList().get(i).getSurname();
            String specialization = GUI.getDoctorList().get(i).getSpecialization();


            Object[] row = {firstname, surname, specialization};
            doctor_table.addRow(row);
        }
        JScrollPane pane = new JScrollPane(doctorTable);
        pane.setBounds(20, 100, 350, 200);
        this.add(pane);

        //labels used to doctorName the text boxes(Text Fields)
        JLabel name_surname = new JLabel();
        label(name_surname, "NAME                  :                                     Surname :", 400, 100, 550, 20);
        name_surname.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 12));

        Name = new JTextField();
        textField(Name, 590, 100);

        surname = new JTextField();
        textField(surname, 900, 100);

        JLabel DOB_ID = new JLabel();
        label(DOB_ID, "Date of birth(YYYY-MM-DD) :                            Patient NIC NO :", 400, 160, 550, 20);
        DOB_ID.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 12));

        DOB = new JTextField();
        textField(DOB, 590, 160);

        patientId = new JTextField();
        textField(patientId, 900, 160);

        JLabel time_date = new JLabel();
        label(time_date, "Time for consultation:                            Date of consultation:", 400, 220, 550, 20);
        time_date.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 12));

        date = new JTextField();
        textField(date, 905, 220);
        //J combo Box used to display the consultation time
        hour_time = new JComboBox<>(hours);
        comboBox(hour_time, 590, 220, 70, 20);

        Min_time = new JComboBox<>(min);
        comboBox(Min_time, 675, 220, 70, 20);

        JLabel phone_no = new JLabel();
        label(phone_no, "Mobile Number :                                      Consultation Duration :", 400, 260, 550, 20);
        phone_no.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 12));

        phoneNO = new JTextField();
        textField(phoneNO, 590, 260);

        duration = new JComboBox<>(due);
        comboBox(duration, 950, 260, 70, 20);


        //J combo Box to display the available doctors.
        for (int i = 0; i < GUI.getDoctorList().size(); i++) {
            this.doctorName[i] = GUI.getDoctorList().get(i).getFirstname() + " " + GUI.getDoctorList().get(i).getSurname();
        }
        doctor_Name = new JComboBox<>(this.doctorName);
        comboBox(doctor_Name, 700, 300, 170, 20);
        /*back button*/
        button1 = new JButton();
        button1.setText("Back");
        button1.setBounds(860, 520, 65, 30);
        button1.setFocusable(false);
        button1.addActionListener(this);
        /*Submit button*/
        button2 = new JButton();
        button2.setText("Submit");
        button2.setBounds(950,520, 75, 30);
        button2.setFocusable(false);
        button2.addActionListener(this);

        JLabel image_upload = new JLabel();
        label(image_upload, "Upload your images :", 500, 350, 150, 20);
        image_upload.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 12));
        /*image upload button*/
        button3 = new JButton();
        button3.setText("Upload image");
        button3.setBounds(780,350, 100, 30);
        button3.setFocusable(false);
        button3.addActionListener(this);
        this.add(button3);

        JLabel doctor_name = new JLabel();
        label(doctor_name, "Choose a doctor based on your preferences.", 380, 300, 300, 20);
        doctor_name.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 12));

        JLabel add_Note = new JLabel();
        label(add_Note, "Additional Add_Note:", 500, 400, 150, 20);
        add_Note.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 12));

        Note = new JTextArea();
        Note.setBounds(650, 410, 200, 100);
        Note.setLineWrap(true);
        this.add(Note);

        photo = new JLabel();
        label(photo,"",900, 385, 100, 100);
        photo.setOpaque(false);
        this.add(photo);

        photo_path = new JLabel();
        photo_path.setBounds(650, 385, 400, 20);
        photo_path.setBackground(Color.white);
        photo_path.setOpaque(false);
        this.add(photo_path);

        this.add(button1);
        this.add(button2);
        this.add(background);
        this.setTitle("Consultation");
        this.setSize(1100, 600);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void validation() {
        int duration = Integer.parseInt(EndTime);
        boolean temp_count = true;
        /*Loop for checking whether the consultation times and date overlap to assign a random doctor*/
        for (Consultation consultation : patientList) {
            if (doctorID.equals(consultation.getDoctor())) {
                if (consultation.getDate().isEqual(conDate)) {
                    if ((consultation.getStartTime().isBefore(startTime)) && (consultation.getEndTime().isAfter(startTime)) ||
                            (consultation.getStartTime().isBefore(endTime)) && ((consultation.getEndTime()).isAfter(endTime)) ||
                            ((consultation.getStartTime()).equals(startTime)) || ((consultation.getEndTime()).equals(endTime))) {
                        temp_count = false;
                        break;
                    }
                }
            }
        }

        for (Consultation consultation : patientList) {
            if (Objects.equals(consultation.getPatient_ID(), patient_ID)) {
                again++;
            }
        }
        double cost;
        if (again == 0) {
            cost = (15 + ((duration - 1) * 15));
        } else {
            cost = 25 * duration;
        }
        if (temp_count) {
            patientList.add(new Consultation(name, Surname, dateOfBirth, mobileNo,patient_ID, doctorID,conDate, startTime, endTime, cost, note));
        } else {
            int Temp = GUI.getDoctorList().size();
            String[] random = new String[Temp];
            for (int i = 0; Temp > i; i++) {
                random[i] = GUI.getDoctorList().get(i).getFirstname() +" "+ GUI.getDoctorList().get(i).getSurname();
            }
            Random rand = new Random();
            int randomIndex = rand.nextInt(random.length);
            doctorID = random[randomIndex];
            validation();
        }
    }

    /*create a labels with the given text, position, and size*/
    public void label(JLabel label, String text, int x, int y, int width, int height) {
        label.setBounds(x, y, width, height);
        label.setText(text);
        this.add(label);
    }
    /*Method to creating text fields*/
    public void textField(JTextField textField, int x, int y) {
        textField.setBounds(x, y, 150, 20);
        textField.setFont(new Font("console", Font.PLAIN, 15));
        this.add(textField);
    }
    /*Method to creating comboBox*/
    public void comboBox(JComboBox<String> comboBox, int x, int y, int width, int height) {
        comboBox.setBounds(x, y, width, height);
        comboBox.setFont(new Font("console", Font.PLAIN, 12));
        this.add(comboBox);
    }
    /*method for resize the image according to the label*/
    public ImageIcon ResizeImage(String image){
        ImageIcon MyImage=new ImageIcon(image);
        Image pic=MyImage.getImage();
        Image newImg=pic.getScaledInstance(photo.getWidth(),photo.getHeight(),Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
    /*Method to validate the inputs taken by the user*/
    public void Input_validationPart(){
        try {
            String regex = "[A-Za-z]+";
            Pattern format = Pattern.compile(regex);
            if (10 > patientList.size()) {
                name = Name.getText().trim();
                Surname = surname.getText().trim();
                Matcher firstName_format = format.matcher(name);
                Matcher surname_format = format.matcher(Surname);
                /*validating the first name and the surname*/
                if (firstName_format.matches() && surname_format.matches()) {
                    dateOfBirth = LocalDate.parse(DOB.getText().trim());
                    mobileNo = phoneNO.getText().trim();
                    /*validation for mobile number*/
                    if (10 == mobileNo.length()) {
                        Integer.parseInt(mobileNo);
                        patient_ID = patientId.getText().trim();
                        /*validating the patient NIC number*/
                        if(patient_ID.length()==12 || patient_ID.length()==9) {
                            doctorID = (String) doctor_Name.getSelectedItem();
                            String hour = (String) hour_time.getSelectedItem();
                            String minutes = (String) Min_time.getSelectedItem();
                            String time = hour + ":" + minutes + ":00";
                            startTime = LocalTime.parse(time);
                            EndTime = (String) duration.getSelectedItem();
                            assert EndTime != null;
                            endTime = startTime.plusHours(Long.parseLong(EndTime));


                            conDate = LocalDate.parse(date.getText().trim());

                            note = Note.getText();
//                            validation();
                            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
                            SecretKey myDesKey = keygenerator.generateKey();

                            // Creating object of Cipher
                            Cipher desCipher;
                            desCipher = Cipher.getInstance("DES");


                            // Creating byte array to store string
                            byte[] text = note.getBytes(StandardCharsets.UTF_8);

                            // Encrypting text
                            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
                            byte[] textEncrypted = desCipher.doFinal(text);

                            // Converting encrypted byte array to string
                            note =  Base64.getEncoder().encodeToString(textEncrypted);
                            /// covert security key


                            /// Decrypting text
                            byte[] output =Base64.getDecoder().decode(note);
                            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
                            byte[] textDecrypted1 = desCipher.doFinal(output);
                            // Converting decrypted byte array to string
                            note = new String(textDecrypted1);
                            validation();


                            JOptionPane.showMessageDialog(null,patientList.get(patientList.size()-1),"Alert",JOptionPane.INFORMATION_MESSAGE);
                                /*https://www.geeksforgeeks.org/create-password-protected-zip-file-in-java/
                                https://stackoverflow.com/questions/14135527/encrypting-zip-entries-but-not-the-entire-zip-file-in-java
                                https://www.geeksforgeeks.org/zip-api-in-java/*/
                            try{
                                // Create an input stream to read the image
                                FileInputStream fis = new FileInputStream(photo_path.getText());
                                // Create an output stream to write the encrypted image
                                FileOutputStream fos = new FileOutputStream(patient_ID+".jpeg");
                                // Create a zip output stream to compress the image data
                                ZipOutputStream zos = new ZipOutputStream(fos);
                                // Define the compression level
                                zos.setLevel(9);
                                // Create a zip entry for the image
                                ZipEntry ze = new ZipEntry(patient_ID+".jpeg");
                                // Put the zip entry into the zip output stream
                                zos.putNextEntry(ze);
                                // Create an array to hold the bytes from the image
                                byte[] buffer = new byte[1024];
                                // Read the image data into the array
                                int len;
                                while ((len = fis.read(buffer)) > 0) {
                                    // Write the encrypted data to the zip output stream
                                    zos.write(buffer, 0, len);
                                }
                                // Close the input and output streams
                                zos.closeEntry();
                                fis.close();
                                zos.close();

                            }catch (Exception ignored){

                            }
                            this.dispose();
                            new Main();
                        }else {
                            JOptionPane.showMessageDialog(null,"please recheck the NIC number","Alert",JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"Error! Please renter the Mobile number","Alert",JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Error! Please check your name and surname","Alert",JOptionPane.ERROR_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(null,"The consultation is full","Alert",JOptionPane.ERROR_MESSAGE);

            }
        } catch (Exception ignored) {
//                System.out.println("Invalid Input");
            JOptionPane.showMessageDialog(null,"Error! Please check your details and enter them in the correct format","Alert",JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*action to back button*/
        if (e.getSource() == button1) {
            this.dispose();
            new Main();
        }
        /*action to submit button*/
        if (e.getSource() == button2) {
            Input_validationPart();
        }
        /*Action to upload image button*/
        if (e.getSource() == button3){
            try {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                photo.setIcon(ResizeImage(String.valueOf(new ImageIcon(f.toString()))));
                String filename = f.getAbsolutePath();
                photo_path.setText(filename);
            }catch(Exception ignored){

            }
        }
    }
}

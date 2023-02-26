package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static Console.WestminsterSkinConsultationManager.patientList;

public class Consultation_Details extends JFrame implements ActionListener {
    private final JLabel photo;

    private final JTextField id;
    private final JButton button1;
    private final JButton button2;
    public Consultation_Details(){
        this.setLayout(null);

        JLabel title = new JLabel();
        labels(title,"Consultation Details",20,20,250,30);
        title.setFont(new Font("id", Font.PLAIN, 20));

        JLabel input = new JLabel();
        labels(input,"Enter your NIC number to view the details :",20,100,400,20);

        id = new JTextField();
        id.setBounds(310, 100, 200, 20);
        id.setFont(new Font("id", Font.PLAIN, 15));
        this.add(id);

        photo = new JLabel();
        labels(photo,"",350, 250, 250, 150);
        photo.setOpaque(false);
        this.add(photo);

        button1 = new JButton();
        button1.setText("Back");
        button1.setBounds(700, 500, 65, 30);
        button1.setFocusable(false);
        button1.addActionListener(this);
        this.add(button1);

        button2 = new JButton();
        button2.setText("OK");
        button2.setBounds(800,500, 65, 30);
        button2.setFocusable(false);
        button2.addActionListener(this);
        this.add(button2);

        //background image of consultation page
        JLabel background = new JLabel(new ImageIcon("background.jpeg"));
        background.setBounds(0, 0, 1000, 600);
        this.add(background);

        this.setTitle("Consultation details");
        this.setSize(1000, 600);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
    public void labels(JLabel label, String text, int x, int y, int width, int height) {
        label.setBounds(x, y, width, height);
        label.setText(text);
        this.add(label);
    }
    public void Image_Decryption() throws IOException {

        String fileName = (id.getText());
        // Create an input stream to read the encrypted image
        FileInputStream fis = new FileInputStream(fileName+".jpeg");
        // Create a zip input stream to read the compressed image data
        ZipInputStream zis = new ZipInputStream(fis);
        // Get the zip entry for the image
        ZipEntry ze = zis.getNextEntry();
        // Create an output stream to write the image
        FileOutputStream fos = new FileOutputStream(fileName+"0.jpeg");
        // Create an array to hold the bytes from the image
        byte[] buffer = new byte[1024];
        // Read the compressed image data from the zip input stream
        int len;
        while ((len = zis.read(buffer)) > 0) {
            // Write the decrypted data to the output stream
            fos.write(buffer, 0, len);
        }
        // Close the input and output streams
        zis.closeEntry();
        fis.close();
        fos.close();

        // Adding the decrypted photo to the frame
        photo.setIcon(ResizeImage(fileName+"0.jpeg"));

    }
    public ImageIcon ResizeImage(String image){
        ImageIcon MyImage=new ImageIcon(image);
        Image pic=MyImage.getImage();
        Image newImg=pic.getScaledInstance(photo.getWidth(),photo.getHeight(),Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
    public void information(){
        String[] column = {"Name", "Surname","Patient ID","Doctors Name","Date Of Birth","MobileNO","startTime","endTime","Date","cost","note"};
        DefaultTableModel patient_table = new DefaultTableModel(column, 0);
        JTable doctorTable = new JTable(patient_table);
        for (int i = 0; i < patientList.size(); i++) {
            String temp = patientList.get(i).getPatient_ID();
            if(Objects.equals(temp, id.getText())) {
                String firstname = patientList.get(i).getFirstname();
                String surname = patientList.get(i).getSurname();
                String patient_id = patientList.get(i).getPatient_ID();
                String doctor_name = patientList.get(i).getDoctor();
                LocalDate dateOfBirth = patientList.get(i).getDate_of_birth();
                String mobileNO = patientList.get(i).getMobileNo();
                LocalTime startTime = patientList.get(i).getStartTime();
                LocalTime endTime = patientList.get(i).getEndTime();
                LocalDate con_Date = patientList.get(i).getDate();
                double cost = patientList.get(i).getCost();
                String note = patientList.get(i).getNote();

                Object[] row = {firstname, surname,patient_id, doctor_name, dateOfBirth, mobileNO, startTime, endTime, con_Date, cost, note};
                patient_table.addRow(row);

            }
        }
        this.add(doctorTable);
        JScrollPane pane = new JScrollPane(doctorTable);
//        pane.setSize(800,300);
        pane.setBounds(30, 150, 900, 80);
        this.add(pane);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            this.dispose();
            new Main();
        }
        if (e.getSource() == button2){
            if (!(id.getText().length() == 9 || id.getText().length() == 12)) {
                JOptionPane.showMessageDialog(null, "Error! Please enter your NIC number to view the details", "Alert", JOptionPane.ERROR_MESSAGE);
            }else{
                try {
                    Image_Decryption();
                    information();

                } catch (IOException ex) {
                    for (int i = 0; i < patientList.size(); i++) {
                        String temp = patientList.get(i).getPatient_ID();
                        if (!Objects.equals(temp, id.getText())) {
                            if(i == patientList.size()-1) {
                                JOptionPane.showMessageDialog(null, "you have not registered before", "Alert", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else{
                            JOptionPane.showMessageDialog(null, "NO images have been uploaded", "Alert", JOptionPane.INFORMATION_MESSAGE);
                            information();
                            break;
                        }
                    }

                }

            }
            File file= new File(id.getText()+"0.jpeg");
            file.delete();
        }
    }
}

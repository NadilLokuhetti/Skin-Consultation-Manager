package GUI;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Main extends JFrame implements ActionListener {

    private final JButton button_1 = new JButton();
    private final JButton button_2 = new JButton();
    private final JButton button_3 = new JButton();

    public Main() {
        this.setLayout(null);
        ImageIcon image = new ImageIcon("image1.png");  //Adding the Image.

        JLabel label1 = new JLabel();
        label1.setBounds(340,0,450,600);
        label1.setIcon(image);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setVerticalTextPosition(JLabel.TOP);


        JLabel label2 = new JLabel();                                                      //Adding the Topic.
        label2.setText("<html>Welcome !!<br>To Westminster skin consultation manager<html>");
        label2.setBounds(5,100,400,50);
        label2.setFont(new Font("SansSerif", Font.BOLD, 12));


        button_1.setBounds(50,200,200,30);
        button_1.setText("View Doctors");
        button_1.setFocusable(false);
        button_1.addActionListener(this);

        button_2.setBounds(50,250,200,30);
        button_2.setText("Add Consultation");
        button_2.setFocusable(false);
        button_2.addActionListener(this);

        button_3.setBounds(50,300,200,30);
        button_3.setText("patient information");
        button_3.setFocusable(false);
        button_3.addActionListener(this);

        this.add(label1);
        this.add(label2);
        this.add(button_1);
        this.add(button_2);
        this.add(button_3);
        this.setTitle("Westminster skin consultation manager");                                    //Adding the elements to the Main Frame.
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button_1) {
            this.dispose();
            new DoctorList();
        } else if (e.getSource()==button_2) {
            this.dispose();
            new Add_Consultation();
        } else if (e.getSource()==button_3) {
            this.dispose();
            new Consultation_Details();
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


// implementing the ActionListener interface in the class
public class PatientLogin implements ActionListener {
    JFrame frame; // declaring frame


    // using JLabel to display text
    JLabel nameLabel=new JLabel("Name");
    JLabel passwordLabel=new JLabel("Password");


    // using JTextField to create text field
    static JTextField nameTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();


    // making buttons through JButton
    JButton LoginButton=new JButton("LOGIN");



    //constructor for calling methods
    public PatientLogin()
    {
        createform();
        Location();
        addingElements();
        buttonAction();
    }


    public void createform()
    {
        // creating a frame
        frame=new JFrame();
        frame.setTitle("Patient Login"); // setting title
        frame.setBounds(50,50,300,300); // setting location and bounds
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // hides the frame when user closes panel

        // getContentPane() method
        frame.getContentPane().setBackground(Color.white); // setting "white" color to background
        frame.getContentPane().setLayout(null);

        // defining appearance
        frame.setVisible(true);
        frame.setResizable(true);
    }


    public void Location()
    {
        // setting location and bounds for labels and text fields
        nameLabel.setBounds(20,20,40,70);
        passwordLabel.setBounds(20,70,100,70);



        nameTextField.setBounds(100,43,160,23);
        passwordField.setBounds(100,93,160,23);


        // setting location and bounds for buttons (register, reset, covid)
        LoginButton.setBounds(90,150,100,35);


        // setting fonts to labels and buttons
        nameLabel.setFont(new Font("Montserrat", Font.PLAIN, 12));
        passwordLabel.setFont(new Font("Montserrat", Font.PLAIN, 12));
        LoginButton.setFont(new Font("Montserrat", Font.PLAIN, 11));

    }



    public void addingElements()
    {
        // adding elements(labels) to the Frame
        frame.add(nameLabel);
        frame.add(passwordLabel);


        // adding elements(fields, combobox) to the Frame
        frame.add(nameTextField);
        frame.add(passwordField);


        //adding buttons to the Frame
        frame.add(LoginButton);
    }

    // method for adding implementing ActionListeners
    public void buttonAction()
    {
        LoginButton.addActionListener(this);
    }


    // actionPerformed(ActionEvent e) method is called automatically when user clicks on registered components
    @Override
    public void actionPerformed(ActionEvent e) {

        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        ConnectDB obj_ConnectDB = new ConnectDB();
        connection = obj_ConnectDB.get_connection();

        // in case button "Login" was clicked
        if(e.getSource()==LoginButton)
        {
            LoginList.displayLogData();
        }

    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


// implementing the ActionListener interface in the class
public class PatientRegistration implements ActionListener {
    JFrame frame; // declaring frame

    // dropdown lists: Gender, Doctor
    String[] gender={"Male","Female"};
    String[] doctor={"Doctor Strange - Surgeon","Hippocrates - God of Medicine", "Alexei Tsoy - Doctor QR"};

    // using JLabel to display text
    JLabel nameLabel=new JLabel("Name");
    JLabel surNameLabel=new JLabel("Surname");
    JLabel passwordLabel=new JLabel("Password");
    JLabel confirmLabel=new JLabel("Confirm Password");
    JLabel genderLabel=new JLabel("Gender");
    JLabel cityLabel=new JLabel("City");
    JLabel emailLabel=new JLabel("eMail");
    JLabel doctorLabel=new JLabel("Doctor");
    JLabel diseaseLabel=new JLabel("Disease");


    // using JTextField to create text field and JComboBox for dropdown menu
    JTextField nameTextField=new JTextField();
    JComboBox genderComboBox=new JComboBox(gender);
    JTextField surNameTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JPasswordField confirmField=new JPasswordField();
    JTextField cityTextField=new JTextField();
    JTextField emailTextField=new JTextField();
    JComboBox doctorComboBox=new JComboBox(doctor);
    JTextField diseaseTextField=new JTextField();

    // making buttons through JButton
    JButton registerButton=new JButton("REGISTER");
    JButton resetButton=new JButton("RESET");
    JButton covidButton=new JButton("COVID-19 Statistics");
    JButton listButton=new JButton("List of Patients");
    JButton chAppButton=new JButton("Check Appointment");
    JButton loginButton=new JButton("LOGIN");


    //constructor for calling methods
    public PatientRegistration()
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
        frame.setTitle("Hospital Registration System"); // setting title
        frame.setBounds(50,50,1000,450); // setting location and bounds
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // hides the frame when user closes panel

        // getContentPane() method
        frame.getContentPane().setBackground(Color.white); // setting "white" color to background
        frame.getContentPane().setLayout(null);

        // defining appearance
        frame.setVisible(true);
        frame.setResizable(true);
    }


    public void Location()
    {
        // setting location and bounds for 1st column (for labels)
        nameLabel.setBounds(20,20,40,70);
        surNameLabel.setBounds(20,70,100,70);
        genderLabel.setBounds(20,120,80,70);

        // setting location and bounds for 2nd column (for labels)
        diseaseLabel.setBounds(350,15,100,70);
        doctorLabel.setBounds(350,65,100,70);
        cityLabel.setBounds(350,115,100,70);

        // setting location and bounds for 3rd column (for labels)
        emailLabel.setBounds(700,16,100,70);
        passwordLabel.setBounds(680,66,100,70);
        confirmLabel.setBounds(630,116,140,70);



        // setting location and bounds for 1st column (for rectangle fields)
        nameTextField.setBounds(100,43,160,23);
        surNameTextField.setBounds(100,93,160,23);
        genderComboBox.setBounds(100,143,162,23);

        // setting location and bounds for 2nd column (for rectangle fields)
        diseaseTextField.setBounds(428,43,160,23);
        doctorComboBox.setBounds(427,90,164,23);
        cityTextField.setBounds(428,141,160,23);

        // setting location and bounds for 3rd column (for rectangle fields)
        emailTextField.setBounds(750,43,160,23);
        passwordField.setBounds(750,93,160,23);
        confirmField.setBounds(750,143,160,23);


        // setting location and bounds for buttons (register, reset, covid)
        registerButton.setBounds(440,225,100,35);
        resetButton.setBounds(550,225,100,35);
        loginButton.setBounds(330,225,100,35);
        covidButton.setBounds(490,275,150,35);
        listButton.setBounds(340,275,150,35);
        chAppButton.setBounds(415,325,150,35);





        // setting fonts to labels and buttons
        nameLabel.setFont(new Font("Montserrat", Font.PLAIN, 12));
        surNameLabel.setFont(new Font("Montserrat", Font.PLAIN, 12));
        diseaseLabel.setFont(new Font("Montserrat", Font.PLAIN, 12));
        doctorLabel.setFont(new Font("Montserrat", Font.PLAIN, 12));
        cityLabel.setFont(new Font("Montserrat", Font.PLAIN, 12));
        emailLabel.setFont(new Font("Montserrat", Font.PLAIN, 12));
        passwordLabel.setFont(new Font("Montserrat", Font.PLAIN, 12));
        confirmLabel.setFont(new Font("Montserrat", Font.PLAIN, 12));

        registerButton.setFont(new Font("Montserrat", Font.PLAIN, 11));
        resetButton.setFont(new Font("Montserrat", Font.PLAIN, 11));
        covidButton.setFont(new Font("Montserrat", Font.PLAIN, 11));
        listButton.setFont(new Font("Montserrat", Font.PLAIN, 11));
        chAppButton.setFont(new Font("Montserrat", Font.PLAIN, 11));
        loginButton.setFont(new Font("Montserrat", Font.PLAIN, 11));

    }



    public void addingElements()
    {
        // adding elements(labels) to the Frame
        frame.add(nameLabel);
        frame.add(genderLabel);
        frame.add(surNameLabel);
        frame.add(passwordLabel);
        frame.add(confirmLabel);
        frame.add(cityLabel);
        frame.add(emailLabel);
        frame.add(doctorLabel);
        frame.add(diseaseLabel);

        // adding elements(fields, combobox) to the Frame
        frame.add(nameTextField);
        frame.add(genderComboBox);
        frame.add(surNameTextField);
        frame.add(passwordField);
        frame.add(confirmField);
        frame.add(cityTextField);
        frame.add(emailTextField);
        frame.add(doctorComboBox);
        frame.add(diseaseTextField);

        //adding buttons to the Frame
        frame.add(registerButton);
        frame.add(resetButton);
        frame.add(covidButton);
        frame.add(listButton);
        frame.add(chAppButton);
        frame.add(loginButton);

    }

    // method for adding implementing ActionListeners
    public void buttonAction()
    {
        registerButton.addActionListener(this);
        resetButton.addActionListener(this);
        covidButton.addActionListener(this);
        listButton.addActionListener(this);
        chAppButton.addActionListener(this);
        loginButton.addActionListener(this);

    }


    // actionPerformed(ActionEvent e) method is called automatically when user clicks on registered components
    @Override
    public void actionPerformed(ActionEvent e) {

        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        ConnectDB obj_ConnectDB = new ConnectDB();
        connection = obj_ConnectDB.get_connection();

        // in case button "REGISTER" was clicked
        if(e.getSource()==registerButton)
        {

            try {
                // inserting values which is sent by user into "Patient"

                PreparedStatement Pstatement = connection.prepareStatement("insert into patient (name, surname ,gender, disease, doctor, city, email, password, confirmpassword) values(?,?,?,?,?,?,?,?,?)");
                Pstatement.setString(1,nameTextField.getText());
                Pstatement.setString(2,surNameTextField.getText());
                Pstatement.setString(3,genderComboBox.getSelectedItem().toString());
                Pstatement.setString(4,diseaseTextField.getText());
                Pstatement.setString(5,doctorComboBox.getSelectedItem().toString());
                Pstatement.setString(6,cityTextField.getText());
                Pstatement.setString(7,emailTextField.getText());
                Pstatement.setString(8,passwordField.getText());
                Pstatement.setString(9,confirmField.getText());



                if(passwordField.getText().equalsIgnoreCase(confirmField.getText()))
                {
                    Pstatement.executeUpdate();
                    // Dialog Window for outputting message
                    JOptionPane.showMessageDialog(null,"Data Registered Successfully");
                }
                else
                {
                    // Dialog Window for outputting message
                    JOptionPane.showMessageDialog(null,"password did not match");
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }


        }

        // in case button "RESET" was clicked
        if(e.getSource()==resetButton)
        {
            nameTextField.setText("");
            genderComboBox.setSelectedItem("Male");
            doctorComboBox.setSelectedItem("Doctor Strange - Surgeon");
            surNameTextField.setText("");
            passwordField.setText("");
            diseaseTextField.setText("");
            confirmField.setText("");
            cityTextField.setText("");
            emailTextField.setText("");
        }

        // in case button "COVID" was clicked
        if(e.getSource()==covidButton)
        {
            CovidStatistics.displayCOVIData();
        }

        if (e.getSource() == listButton){
            PatientList.displayPatientData();
        }

        if (e.getSource() == chAppButton){
            Appointment.displayAppointmentData();
        }

        if (e.getSource() == loginButton){
            new PatientLogin();
        }


    }
}

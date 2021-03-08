# Hospital Management System (HMS)
This project aims to simplify registering and managing patients. HMS is created using Java, PostgreSQL and Swing (GUI).  


## Classes
Hospital Management System(HMS) consists of 8 classes:

* *PatientRegistration* - to register new patients
* *PatientLogin* - to log in to the database
* *CovidStatistics* - to show the amount of patients with COVID-19
* *PatientList* - to illustrate the data about patients 
* *LoginList* - to show the data of exact patient
* *Appointment* - to get info about order, date
* *ConnectDB* - connecting to the database
* *Main* - to run the project


>The most valuable ones are: *PatientRegistration, PatientLogin, CovidStatistics, PatientList.* 

## Usage

### *PatientRegistration.java*
This class records patient data depending on what was written in text fields. Main GUI is stored there.
```
public PatientRegistration()
    {
        createform();
        Location();
        addingElements();
        buttonAction();
    }
```
PatientRegistration class has 5 main methods: `createform()`, `Location()`, `addingElements()`, `buttonAction()`, `actionPerformed(ActionEvent e)`
- `createform()`
This method creates frame for the main panel using JFrame. Along with setting the title, location and background. Briefly, *createform()* focusses on the appearance of a panel.
- `location()`
This method sets location bounds, fonts of labels, buttons also it's text fields.
- `addingElements()`
Adding components such as *textfield, labels* and *buttons* into the Frame
- `buttonAction()`
This method stands for adding *Action Listeners*
- `actionPerformed(ActionEvent e)` method is called automatically when user clicks on registered components. Depending on what buttons(*registerButton, resetButton, covidButton, listButton, chAppButton, loginButton*) were clicked executes specific commands.

**registerButton** - *stands for inserting user-defined values from textfield into database*.

**resetButton** - *stands for resetting textfield back to empty fields.*

**covidButton** - *stands for calling `CovidStatistics.displayCOVIData()` which display the patients with COVID-19.*

**listButton** - *stands for calling 
`PatientList.displayPatientData()` which display all patients.*

**chAppButton** - *stands for calling `Appointment.displayAppointmentData()` which display patients' order ID and date of appointment.*

**loginButton** - *stands for executing `new PatientLogin();` which logins patient and display data about this exact person.*

>Note: Last 4 buttons will be explained thoroughly in its own classes


### *PatientLogin.java*
This class stands for logging into system and getting common information about the patient.

Will be executed when in *PatientRegistration.java* `loginButton` is clicked

```
/* 
it is the main method that executes LoginList.displayLogData()
which has same logic as CovidStatistics.java
*/
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
```


## Displaying the common data of patients

### *CovidStatistics.java* and *PatientList.java*
*CovidStatistics* class itself provides information about patients that has COVID-19 and outputs data of these individuals.

*PatientList* class displays all data of patients that are in hospital management system.

Will be executed when in *PatientRegistration.java*  `covidButton` or `listButton` is clicked.

>Note: in following pseudocode were mentioned only main points
```
public static void displayCOVIData() {
  JFrame frame1; // declaring frame for table
  JTable table; // declaring table

  // creating a frame
  frame1 = new JFrame("Patients affected by COVID-19");
  frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

  table = new JTable();
  table.setModel(model); 
  // setting autoresize
  table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 

  try{
     // displaying patients with COVID-19 (SQL query is in Report)

    PreparedStatement covidstatistics = connection.prepareStatement(sql);
    ResultSet rs = covidstatistics.executeQuery();
  }

   while (rs.next()) {
      String firstname = rs.getString(1);
      //...

   }

} catch(SQLException e1){
   JOptionPane.showMessageDialog(...)
}

```









## Summary
The main purpose of my project is to make easy managing patients and getting information from database without any problem.

Hope this project will help to enhance the quality of hospital management system.

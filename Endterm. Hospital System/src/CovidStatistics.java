import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class CovidStatistics {
    public static void displayCOVIData() {
        JFrame frame1; // declaring frame for table
        JTable table; // declaring table

        // creating a frame
        frame1 = new JFrame("Patients affected by COVID-19");
        frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // hides the frame when user closes panel

        //constructing a DefaultTableModel and initializing the table by passing data and columnNames
        DefaultTableModel model = new DefaultTableModel();

        // add columnNames of the table
        String[] columnNames = {"name", "surname", "gender", "disease", "doctor", "city", "email", "password", "confirmpassword"};
        // add columnNames in table model
        model.setColumnIdentifiers(columnNames);

        // creating a table
        table = new JTable();
        table.setModel(model); // set model to
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // setting autoresize


        JScrollPane scroll = new JScrollPane(table);// providing a scrollable view of the table

        // Determines when the horizontal scrollbar appears in the scroll pane
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // adding compoments to the frame
        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(600, 400);


        try {
            // providing connection
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SimpleDB", "postgres", "postgres");

            // displaying patients with COVID-19
            PreparedStatement covidstatistics = connection.prepareStatement("select * from patient where disease = 'COVID-19'");
            ResultSet rs = covidstatistics.executeQuery();

            while (rs.next()) {
                String firstname = rs.getString(1);
                String secondname = rs.getString(2);
                String gender = rs.getString(3);
                String disease = rs.getString(4);
                String doctor = rs.getString(5);
                String city = rs.getString(6);
                String email = rs.getString(7);
                String password = rs.getString(8);
                String confirm = rs.getString(9);

                // adding row of columns of table "patient" into JTable
                model.addRow(new Object[]{firstname, secondname, gender, disease, doctor, city, email, password, confirm});

                System.out.println();
            }
            rs.close();


        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage(), "Error Boss", JOptionPane.ERROR_MESSAGE);
        }
    }
}

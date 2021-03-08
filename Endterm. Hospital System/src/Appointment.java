import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Appointment {
    public static void displayAppointmentData() {
        JFrame frame1; // declaring frame for table
        JTable table; // declaring table

        // creating a frame
        frame1 = new JFrame("Appointments");
        frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // hides the frame when user closes panel


        //constructing a DefaultTableModel and initializing the table by passing data and columnNames
        DefaultTableModel model = new DefaultTableModel();

        // add columnNames of the table
        String[] columnNames = {"order_id", "date_appoint"};
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
        frame1.setSize(300, 250);


        try {
            // providing connection
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SimpleDB", "postgres", "postgres");

            // displaying appointments
            PreparedStatement list = connection.prepareStatement("select * from appointment");
            ResultSet rs = list.executeQuery();

            while (rs.next()) {
                String id = rs.getString(1);
                String date = rs.getString(2);


                // adding row of columns of table "patient" into JTable
                model.addRow(new Object[]{id, date});

                System.out.println();
            }
            rs.close();


        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage(), "Error Boss", JOptionPane.ERROR_MESSAGE);
        }
    }

}

package sample;

import java.sql.*;
import java.util.ArrayList;

//Methods for retrieving (and adding?) stuff from the database
public class DBTools {
    public static Connection getConnection() throws Exception {
        Connection conn = null;
        // db parameters
        String url = "jdbc:sqlite:schedule.db";
        // create a connection to the database
        conn = DriverManager.getConnection(url);

        System.out.println("Connection to SQLite has been established.");

        return conn;
    }

    //Returns an arrayList of Volunteer objects representing all the information in the Volunteers table
    public static ArrayList<Volunteer> getVolunteers() throws Exception {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT VolunteerID, Name FROM Volunteers");
        ResultSet rs = ps.executeQuery();
        ArrayList<Volunteer> result = new ArrayList<Volunteer>();
        while (rs.next()) {
            result.add(new Volunteer(rs.getInt("VolunteerID"), rs.getString("Name")));
        }
        rs.close();
        return result;
    }

    //Returns an arrayList of Shift objects representing all the information in the Shifts table
    public static ArrayList<Shift> getShifts() throws Exception {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT ShiftID, Start_time, End_time FROM Volunteers");
        ResultSet rs = ps.executeQuery();
        ArrayList<Shift> result = new ArrayList<Shift>();
        while (rs.next()) {
            result.add(new Shift(rs.getInt("ShiftID"), rs.getString("Start_time"),
                    rs.getString("End_time")));
        }
        rs.close();
        return result;
    }

    //Joins Volunteers and Shifts through the Assignments tables and returns arrayList of Assignment objects
    public static ArrayList<Assignment> getAssignments() throws Exception {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT Name, Start_time, End_time" +
                " FROM Volunteers v" +
                " JOIN Assignments a ON v.VolunteerID = a.VolunteerID" +
                " JOIN Shifts s ON a.ShiftID = s.ShiftID" +
                " ORDER BY s.End_time ASC");
        ResultSet rs = ps.executeQuery();
        ArrayList<Assignment> result = new ArrayList<Assignment>();
        while (rs.next()) {
            result.add(new Assignment(rs.getString("Name"), rs.getString("Start_time"),
                    rs.getString("End_time")));
        }
        rs.close();
        return result;
    }


}

package sample;

import java.sql.*;
import java.util.ArrayList;

//Methods for retrieving (and adding?) stuff from the database
public class DBTools {
    public static Connection getConnection() throws Exception {
        Connection conn = null;
        // db parameters
        String url = "jdbc:sqlite:C:\\Users\\kec20\\IdeaProjects\\Museum-Schedule\\schedule.db";
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
        PreparedStatement ps = con.prepareStatement("SELECT ShiftID, Start_time, End_time FROM Shifts");
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

    //adds Volunteer object in the Volunteers table in database
    public void add(Volunteer v) throws Exception {
        String sql = "INSERT INTO Volunteers(VolunteerID, Name) VALUES(?,?)";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, v.getId());
            pstmt.setString(2, v.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //adds Assignments object in the Assignments table in database
    public void addAssignments(int volunteerId, int shiftId) throws Exception {
        String sql = "INSERT INTO Assignments(VolunteerID, ShiftID) VALUES(?,?)";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, volunteerId);
            pstmt.setInt(2, shiftId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //find the volunteer ID of the volunteer name
    public int getVolunteerID(String vol) throws Exception {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Volunteers");
        ResultSet rs = ps.executeQuery();
        ArrayList<Volunteer> result = new ArrayList<>();
        while (rs.next()) {
            //adds volunteers into an arraylist of volunteers
            result.add(new Volunteer(rs.getInt("VolunteerID"), rs.getString("Name")));
        }
        rs.close();
        //searches through the volunteers to find the name of volunteer and returns the id
        for (int i = 0; i < result.size(); i++) {
            if(result.get(i).getName().equals(vol)) {
                return result.get(i).getId();
            }
        }
        return result.size()+1;
    }

    //find the volunteer ID of the volunteer name
    public int getShiftID(String start, String end) throws Exception {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Shifts");
        ResultSet rs = ps.executeQuery();
        ArrayList<Shift> result = new ArrayList<>();
        while (rs.next()) {
            //adds shifts into an arraylist
            result.add(new Shift(rs.getInt("ShiftID"), rs.getString("Start_time"),
                    rs.getString("End_time")));
        }
        rs.close();
        //searches through the shifts to find the shiftID  of the specific start and end time
        for (int i = 0; i < result.size(); i++) {
            if(result.get(i).getStartTime().equals(start) && result.get(i).getEndTime().equals(end)) {
                return result.get(i).getShiftID();
            }
        }
        return -1;
    }

}

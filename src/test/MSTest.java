package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import sample.Assignment;
import sample.DBTools;
import sample.Volunteer;

import java.sql.Connection;
import java.util.ArrayList;

public class MSTest {
    DBTools db = new DBTools();

    @Test
    void connectTest() throws Exception {
        Connection connect = db.getConnection();
        assertNotNull(connect);
    }

    @Test
    void isAddingVolunteers() throws Exception {
        ArrayList<Volunteer> list = new ArrayList<>();
        db.add(new Volunteer(2, "Kass"));
        list = db.getVolunteers();
        assertEquals(list.get(1).getName(), "Kass");
        assertEquals(list.get(1).getId(), 2);
    }

    @Test
    void isAddingAssignments() throws Exception {
        ArrayList<Assignment> list = new ArrayList<>();
        db.add(new Volunteer(2, "Kass"));
        db.addAssignments(db.getVolunteerID("Kass"), 1);
        list = db.getAssignments();
        assertEquals(list.get(1).getName(), "Kass");
        assertEquals(list.get(1).getStartTime(), "0700");
        assertEquals(list.get(1).getEndTime(), "0900");
    }

    @Test
    void gettingVolunteerID() throws Exception {

    }

    @Test
    void gettingVolunteers() throws Exception {
        ArrayList<Volunteer> list = new ArrayList<>();
        list = db.getVolunteers();
        assertEquals(list.get(0).getName(), "Ben");
        assertEquals(list.get(0).getId(), 1);
    }

    @Test
    void gettingAssignments() throws Exception {
        ArrayList<Assignment> list = new ArrayList<>();
        list = db.getAssignments();
        assertEquals(list.get(0).getName(), "Ben");
        assertEquals(list.get(0).getStartTime(), "0700");
        assertEquals(list.get(0).getEndTime(), "0900");
    }


}

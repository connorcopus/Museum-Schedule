package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DisplayController implements Initializable {
    ObservableList<String> shiftOption = FXCollections.observableArrayList(
            "Select a shift", "0700-0900", "0900-1100", "1100-1300", "1300-1500", "1500-1700");
    DBTools db = new DBTools();
    @FXML
    private TextArea addName;
    @FXML
    private ChoiceBox<String> shiftOp;
    @FXML
    private TableView table;
    @FXML
    private TableColumn<String, Assignment> nameCol;
    @FXML
    private TableColumn<String, Assignment> startCol;
    @FXML
    private TableColumn<String, Assignment> endCol;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        shiftOp.setValue("Select a shift");

        //adding the options to the choice box from the ObservableList
        shiftOp.setItems(shiftOption);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        startCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        endCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        ArrayList<Assignment> assignments = new ArrayList<>();
        try {
            assignments = DBTools.getAssignments();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        Assignment cur = assignments.get(0);
        for (int i = 0; i < assignments.size(); i++) {
            cur = assignments.get(i);
            table.getItems().add(cur);
        }

    }

    //adds a new assignment on the table in the display and a volunteer in the volunteers table in database
    @FXML
    private void addVolunteer() throws Exception {
        Assignment volShift;
        Volunteer v = new Volunteer(db.getVolunteerID(addName.getText()), addName.getText());
        if (shiftOp.getValue().toString() == "Select a shift") {
            System.out.print("Please select a shift");
        } else if(shiftOp.getValue().toString() == "0700-0900"){
            db.add(v);
            db.addAssignments(db.getVolunteerID(addName.getText()), db.getShiftID("0700","0900"));
            volShift = new Assignment(addName.getText(), "0700", "0900");
            table.getItems().add(volShift);
        } else if(shiftOp.getValue().toString() == "0900-1100"){
            db.add(v);
            db.addAssignments(db.getVolunteerID(addName.getText()), db.getShiftID("0900","1100"));
            volShift = new Assignment(addName.getText(), "0900", "1100");
            table.getItems().add(volShift);
        } else if(shiftOp.getValue().toString() == "1100-1300"){
            db.add(v);
            db.addAssignments(db.getVolunteerID(addName.getText()), db.getShiftID("1100","1300"));
            volShift = new Assignment(addName.getText(), "1100", "1300");
            table.getItems().add(volShift);
        } else if(shiftOp.getValue().toString() == "1300-1500"){
            db.add(v);
            db.addAssignments(db.getVolunteerID(addName.getText()), db.getShiftID("1300","1500"));
            volShift = new Assignment(addName.getText(), "1300", "1500");
            table.getItems().add(volShift);
        } else {
            db.add(v);
            db.addAssignments(db.getVolunteerID(addName.getText()), db.getShiftID("1500","1700"));
            volShift = new Assignment(addName.getText(), "1500", "1700");
            table.getItems().add(volShift);
        }

    }

    @FXML
    private void clear() {
        addName.clear();
        shiftOp.setValue("Select a shift");
    }

}

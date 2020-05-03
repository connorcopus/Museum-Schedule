package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DisplayController implements Initializable {
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


}

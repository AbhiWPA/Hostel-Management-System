package lk.ijse.d24hostel.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.d24hostel.bo.custom.RoomsBO;
import lk.ijse.d24hostel.bo.custom.impl.RoomBOImpl;
import lk.ijse.d24hostel.entity.Rooms;
import lk.ijse.d24hostel.entity.Student;
import net.bytebuddy.dynamic.DynamicType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RoomsAvailabilityController implements Initializable {
    public AnchorPane context;
    public TableView tblRooms;
    public TableColumn colRId;
    public TableColumn colRType;
    public TableColumn colKeyMoney;
    public TableColumn colQty;
    RoomsBO roomsBO = new RoomBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableDetails();

        colRId.setCellValueFactory(new PropertyValueFactory<>("rID"));
        colRType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void setTableDetails() {
        tblRooms.getItems().clear();
        try {
            ArrayList<Rooms> allRooms = roomsBO.getAllRooms();

            for (Rooms rooms : allRooms) {
                tblRooms.getItems().add(new Rooms(rooms.getRID(), rooms.getRoomType(), rooms.getKeyMoney(), rooms.getQty()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

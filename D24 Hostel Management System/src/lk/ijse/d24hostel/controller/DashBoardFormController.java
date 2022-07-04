package lk.ijse.d24hostel.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class DashBoardFormController {
    public ImageView imgLogout;
    public Label lblNote;
    public Label lblDescription;
    public AnchorPane context;
    public ImageView imgStudent;
    public ImageView imgRoom;
    public ImageView imgReservation;
    public ImageView imgReport;
    public ImageView imgSettings;

    public void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            switch (icon.getId()) {
                case "imgStudent":
                    loadUi("StudentForm", imgStudent);
                    break;
                case "imgRoom":
                    loadUi("ManageRoomDetailsForm", imgRoom);
                    break;
                case "imgReservation":
                    loadUi("RoomReservationForm", imgReservation);
                    break;
                case "imgReport":
                    loadUi("ReportForm", imgReport);
                    break;
                case "imgLogout":
                    loadUi("LoginPageForm", imgLogout);
                    break;
                case "imgSettings":
                    loadUi("SecurityForm", imgSettings);
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.context.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    public void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            switch (icon.getId()) {
                case "imgStudent":
                    lblNote.setText("Student Details");
                    lblDescription.setText("Edit student details(Add/Remove/Update).");
                    break;
                case "imgRoom":
                    lblNote.setText("Room Details");
                    lblDescription.setText("Edit room details(Add/Remove/Update).");
                    break;
                case "imgReservation":
                    lblNote.setText("Room Reservation");
                    lblDescription.setText("Reserve a room for students and make payments.");
                    break;
                case "imgReport":
                    lblNote.setText("System Reports");
                    lblDescription.setText("Income Report, Student reports etc.");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.DARKSALMON);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblNote.setText("Welcome...!");
            lblDescription.setText("Please select one of above options to continue.");
        }
    }

    public void loadUi(String location, ImageView img) throws IOException {
        Stage stage = (Stage) img.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/d24hostel/view/"+location+".fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.setResizable(false);
        stage2.show();
    }
}

package lk.ijse.d24hostel.controller;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.management.Notification;
import java.awt.*;

public class NotificationController {
    Notifications notifications;
    Button ok;
    Button cancel;

    public void confirmMassage(String title, String text){
        notifications = Notifications.create();
        notifications.title(title);
        notifications.text(text);
        notifications.graphic(null);
        notifications.showConfirm();
        notifications.darkStyle();
        notifications.hideAfter(Duration.seconds(3));
        notifications.position(Pos.BASELINE_RIGHT);
        notifications.show();
    }

    public void errorMassage(String title, String text){
        notifications = Notifications.create();
        notifications.title(title);
        notifications.text(text);
        notifications.graphic(null);
        notifications.showWarning();
        notifications.darkStyle();
        notifications.hideAfter(Duration.seconds(3));
        notifications.position(Pos.BASELINE_RIGHT);
        notifications.show();
    }

    public void upperConfirmMessage(String title, String text){
        notifications = Notifications.create();
        notifications.title(title);
        notifications.text(text);
        notifications.graphic(null);
        notifications.showConfirm();
        notifications.darkStyle();
        notifications.hideAfter(Duration.seconds(3));
        notifications.position(Pos.TOP_RIGHT);
        notifications.show();
    }

    public void upperErrorMessage(String title, String text){
        notifications = Notifications.create();
        notifications.title(title);
        notifications.text(text);
        notifications.graphic(null);
        notifications.showConfirm();
        notifications.darkStyle();
        notifications.hideAfter(Duration.seconds(3));
        notifications.position(Pos.TOP_RIGHT);
        notifications.show();
    }

}

package lk.ijse.d24hostel.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Reservation {
    @Id
    private String ReservationId;
    private String date;
    private String StudentId;
    private String RoomId;
    private String Payments;
}

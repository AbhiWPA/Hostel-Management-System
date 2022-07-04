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
public class Rooms {
    @Id
    private String rID;
    private String roomType;
    private BigDecimal keyMoney;
    private int qty;
}

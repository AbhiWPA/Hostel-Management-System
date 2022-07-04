package lk.ijse.d24hostel.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Student {
    @Id
    private String studentId;
    private String studentName;
    private String studentAddress;
    private int contact;
    private String dob;
    private String gender;
}

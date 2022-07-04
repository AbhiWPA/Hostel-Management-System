package lk.ijse.d24hostel.dto;

import java.math.BigDecimal;

public class ReservationDTO {
    private String ReservationId;
    private String date;
    private String StudentId;
    private String RoomId;
    private BigDecimal Payments;

    public ReservationDTO() {
    }

    public ReservationDTO(String reservationId, String date, String studentId, String roomId, BigDecimal payments) {
        ReservationId = reservationId;
        this.date = date;
        StudentId = studentId;
        RoomId = roomId;
        Payments = payments;
    }

    public String getReservationId() {
        return ReservationId;
    }

    public void setReservationId(String reservationId) {
        ReservationId = reservationId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getRoomId() {
        return RoomId;
    }

    public void setRoomId(String roomId) {
        RoomId = roomId;
    }

    public BigDecimal getPayments() {
        return Payments;
    }

    public void setPayments(BigDecimal payments) {
        Payments = payments;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "ReservationId='" + ReservationId + '\'' +
                ", date='" + date + '\'' +
                ", StudentId='" + StudentId + '\'' +
                ", RoomId='" + RoomId + '\'' +
                ", Payments=" + Payments +
                '}';
    }
}

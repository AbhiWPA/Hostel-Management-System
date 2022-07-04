package lk.ijse.d24hostel.dto;

import java.math.BigDecimal;

public class RoomsDTO {
    private String rID;
    private String roomType;
    private BigDecimal keyMoney;
    private int Qty;

    public RoomsDTO() {
    }

    public RoomsDTO(String rID, String roomType, BigDecimal keyMoney, int qty) {
        this.rID = rID;
        this.roomType = roomType;
        this.keyMoney = keyMoney;
        Qty = qty;
    }

    public String getrID() {
        return rID;
    }

    public void setrID(String rID) {
        this.rID = rID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public BigDecimal getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(BigDecimal keyMoney) {
        this.keyMoney = keyMoney;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    @Override
    public String toString() {
        return "RoomsDTO{" +
                "rID='" + rID + '\'' +
                ", roomType='" + roomType + '\'' +
                ", keyMoney=" + keyMoney +
                ", Qty=" + Qty +
                '}';
    }
}

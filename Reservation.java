
public class Reservation {

	private String checkInDate;
    private String checkOutDate;
    private Room room;

    public Reservation(Room room) {
        this.room = room;
    }

    public void setCheckInDate(String date) {
        this.checkInDate = date;
    }

    public void setCheckOutDate(String date) {
        this.checkOutDate = date;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public Room getRoom() {
        return room;
    }

    public String toString() {
        return "Check-in: " + checkInDate +
               ", Check-out: " + checkOutDate +
               ", Room: " + room.getRoomNumber();
    }
}

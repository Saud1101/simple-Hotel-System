
public class Reservation {

	private String checkInDate;
    private String checkOutDate;
    private Room room;

    public Reservation(Room room) {
        this.room = room;
        this.checkInDate = "";
        this.checkOutDate = "";
        room.setAvailable(false); //room is unavailable by default
    }
    //setters and getters
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
        return "Room: " + room.getRoomNumber() +
               " | Check-in: " + checkInDate +
               " | Check-out: " + checkOutDate;
    }
}


public class Guest {

	private String name;
    private String id;
    private Reservation reservation;

    public Guest(String name, String id) {
        this.name = name;
        this.id = id;
        reservation = null; 
    }

    public boolean addReservation(Reservation r) {
        if (reservation == null && r.getRoom().isAvailable()) {
            reservation = r;
            r.getRoom().setAvailable(false); //room is booked
            return true;
        }
        return false; 
    }

    public boolean cancelReservation() {
        if (reservation != null) {
        	reservation.getRoom().setAvailable(true); //room becomes available again
        	reservation = null;
            return true;
        }
        return false;
    }

    public void checkOutReservation() {
        if (reservation != null) {
            reservation.getRoom().setAvailable(true);
            reservation = null;
        }
    }

    public Reservation getReservation() {
        return reservation;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

    public String toString() {
        return "Guest: " + name + "\nID: " + id +
               (reservation != null ? "\n" + reservation.toString() : "\nNo reservation");
    }
}

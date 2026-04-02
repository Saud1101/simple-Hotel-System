// Abstract class representing a general Room in the system
public abstract class Room {
    protected int roomNumber;
    protected double pricePerNight;
    protected boolean available;

    // Constructor to initialize room details
    public Room(int roomNumber, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.available = true; 
    }

    // Abstract method to be implemented by subclasses 
    public abstract String getRoomType();

    // Getters and Setters
    public int getRoomNumber() {
        return roomNumber;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    //string representation of the room details
    public String toString() {
        return "Room Number: " + roomNumber + ", Price: " + pricePerNight + ", Available: " + available;
    }
}
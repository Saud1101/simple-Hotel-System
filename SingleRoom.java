// Subclass representing a Single Room, inheriting from Room and implementing Status
public class SingleRoom extends Room implements Status {

    // Constructor sets a fixed price 
    public SingleRoom(int num) {
        super(num, 150.0);
    }

    // Overriding the abstract method to specify the room type
    public String getRoomType() {
        return "Single Room";
    }

    // Implementation of ShowStatus method from the Status interface
    public String ShowStatus() {
        return available ? "The single room is available." : "The single room isn't available.";
    }

    
    public String toString() {
        return super.toString() + ", Type: " + getRoomType();
    }
}

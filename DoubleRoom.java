// Subclass representing a Double Room, inheriting from Room and implementing Status
public class DoubleRoom extends Room implements Status {

	public DoubleRoom(int num) {
		super(num, 250.0);
	}

	// Overriding the abstract method to specify the room type
	public String getRoomType() {
		return "Double Room";
	}

	// Implementation of ShowStatus method from the Status interface
	public String ShowStatus() {
		return available ? "The double room is available." : "The double room isn't available.";
	}

	
	public String toString() {
		return super.toString() + ", Type: " + getRoomType();
	}
}
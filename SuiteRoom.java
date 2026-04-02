// Specialized subclass representing a Suite, inheriting from DoubleRoom
public class SuiteRoom extends DoubleRoom {
	private int numRooms; // Additional attribute for the number of rooms in the suite

	// Constructor updates inherited price and initializes suite-specific rooms
	public SuiteRoom(int num) {
		super(num);
		this.pricePerNight = 450.0;
		this.numRooms = 3;
	}

	// Overriding the abstract method to specify the room type
	public String getRoomType() {
		return "Suite Room";
	}

	public int getNumRooms() {
		return numRooms;
	}

	// Appends the suite's room count to the existing room details
	public String toString() {
		return super.toString() + ", Number of Rooms in Suite: " + numRooms;
	}
}

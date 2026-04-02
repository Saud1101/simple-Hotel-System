package JAVAPROJECT;

public class Hotel {

	private Room[] rooms;
	private int numRooms;
	private Guest[] guests;
	private int numGuests;
	private int totalGuestsEver;

	// Constructor 
	public Hotel(int RoomSize, int GuestSize) {
		rooms = new Room[RoomSize];
		guests = new Guest[GuestSize];
	}

	// Adds a room to the hotel based on its type
	public boolean addRoom(Room r) {

		if (numRooms >= rooms.length) 
			return false;
			else {
				if (r instanceof suitRoom)
				{
				rooms[numRooms++] = new suitRoom(r.getRoomNumber());
			return true;
			}
			
			else 
				if (r instanceof singleRoom) 
				{
					rooms[numRooms++] = new singleRoom(r.getRoomNumber());
					return true;
					}
			else 
				if (r instanceof doubleRoom) 
				{
				rooms[numRooms++] = new doubleRoom(r.getRoomNumber());
				return true;
				}
			}//end else 

	return false;
	}//end addRoom method

	// Searches for a room by its number
	public Room searchRoom(int roomNumber) {

		for (int i = 0; i < numRooms; i++) {
			if (rooms[i].getRoomNumber() == roomNumber)
				return rooms[i];
		}

		// Room not found
		return null;
	}//end searchRoom method

	// Checks if there is at least one available room
	public boolean hasAvailableRooms() {

		for (int i = 0; i < numRooms; i++)
			if (rooms[i].isAvailable())
				return true;

		return false;
	}// end hasAvailableRooms method

	// Displays all available rooms
	public void showAvailableRooms() {

		String AvailableRooms = "";

		// If no rooms exist in the hotel
		if (numRooms == 0)
			System.out.println("There are no rooms in the hotel ");
		else {
		for (int i = 0; i < numRooms; i++)
			if (rooms[i].isAvailable())
				AvailableRooms += rooms[i].toString() + "\n";// Collect all available rooms 

		if (AvailableRooms.equals("")) // If no available rooms found
			System.out.println("There are no available rooms");  
		else
		System.out.println(AvailableRooms);}
	}// end showAvailableRooms method

	public boolean addGuest(Guest g) {

		if (numGuests < guests.length) {

			guests[numGuests++] = g;
			totalGuestsEver++;// Total number of guests ever added (for tracking)

			return true;
		} else
			return false;
	}//end addGuest method

	// Recursive method to search for a guest by ID starting from a given index
	public Guest searchGuestRecursive(String guestId, int index) {

	    // Base case: if index reaches the end, guest not found
	    if (index >= numGuests) {
	        return null;
	    }

	    // If the guest ID matches, return the guest object
	    if (guests[index].getId().equals(guestId)) {
	        return guests[index];
	    }

	    // Recursive call: check the next index
	    return searchGuestRecursive(guestId, index + 1);
	}// end searchGuestRecursive method 

	// Public method to start searching from index 0 using the recursive method
	public Guest searchGuest(String guestId) {

	    // Start the recursive search from the beginning of the array
	    return searchGuestRecursive(guestId, 0);
	    }// end searchGuest method 
	
	// Deleting a guest by ID
	public boolean removeGuest(String guestId) {

		for (int i = 0; i < numGuests; i++)
			if (guests[i].getId().equals(guestId))
			{
				for ( int j = i ; j< numGuests-1 ; j++ ) 
					guests[j] = guests[j+1];
				numGuests--;
				guests[numGuests] = null;
				return true; // if deleting completed 
				
			}
		
		return false; // if can't delete the guest
		
		}// end removeGuest method
	
}// end class

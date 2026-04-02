package JAVAPROJECT;
import java.util.*;

public class HotelTester {

	public static Hotel hotel = new Hotel(100,50);
	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Room room1 = new doubleRoom(1);
		Room room2 = new doubleRoom(2);
		Room room3 = new singleRoom(3);
		Room room4 = new singleRoom(4);
		Room room5 = new suitRoom(5);
		Room room6 = new suitRoom(6);
		
		hotel.addRoom(room1);
		hotel.addRoom(room2);
		hotel.addRoom(room3);
		hotel.addRoom(room4);
		hotel.addRoom(room5);
		hotel.addRoom(room6);
		
		int choice;
		do {
			MainMenu();
			choice = scanner.nextInt();
			
			switch(choice) {
			case 1: 		
				addReservation();
				break;
			case 2:
				cancelReservation();
				break;
			case 3:
				checkIn();
				break;
			case 4:
				checkOut();
				break;
			case 5:
				ShowAvailableRooms();
				break;
			case 6:
				FindGuestRoom();
				break;
			case 0:
				System.out.println("Thank you for visiting our system!");
				break;
			}
		}while (choice!=0);

	}//end main
	
	public static void addReservation() {
		
		//Check if there are available rooms in the hotel
		if ( hotel.hasAvailableRooms() == true ) {
			
	        // Ask for guest name
			System.out.println("Enter your Name: ");
			scanner.nextLine();
			String GuestName = scanner.nextLine();
			
	        // Ask for guest ID
			System.out.println("Enter your ID : ");
			
			String GuestId = scanner.nextLine();
			
			
			//// Check the validity of the ID ////
			
			while(GuestId.equals("") || GuestId.charAt(0) != '1') {
				
				if ( GuestId.equals(""))
				System.out.println("Sorry, ID cannot be empty! ");
				else
					System.out.println("Invalid ID! The ID must start with number 1. ");
				
				System.out.println("Enter your ID : ");
				scanner.nextLine();
				GuestId = scanner.nextLine();
 
			}
		    //// End ID validation ////
		
			//Search for the guest in the hotel system
			Guest guest = hotel.searchGuest(GuestId);
			
			// Check if the guest already has a reservation
		if (guest != null && guest.getReservation() != null)
		{ 
				System.out.println("This guest already has a reservation. ");
				}// Stooping he is already have!
		
			else {
				
				System.out.println("Starting reservation process...");
				
				// Show available rooms to the user
				hotel.showAvailableRooms();
				
			System.out.println("Enter the room number you want to reserve:");
			int RoomNumber = scanner.nextInt();
			
			// Search for the selected room
			Room room = hotel.searchRoom(RoomNumber);
			
			//Validate the selected room
			while(room == null || room.isAvailable() == false) 
			{
				
				if ( room == null)
				System.out.println("Invalid Room Number, We have not this room");
				else
					System.out.println("Sorry, room is not availble ");
				
			System.out.println("Enter The number of the rooms: ");
			RoomNumber = scanner.nextInt();
			
			room = hotel.searchRoom(RoomNumber);
			}//end while
			
			//If guest does not exist, create a new guest
			if ( guest == null ) 
			{
		guest = new Guest( GuestName , GuestId);
			hotel.addGuest(guest);
			}//end if 
			
			Reservation reservation = new Reservation(room);
			
			guest.addReservation(reservation);
			System.out.println("Reservation added successfully!");
			}//end else 
		
			}// end if available rooms 
		
			
		
		else //if there is no available rooms
			System.out.println("Sorry all rooms are currently booked! no rooms available");
		
		}//end method addReservation

	
	public static void cancelReservation() {
		
		//Ask the user to enter the guest ID
		System.out.println("Enter your ID :");
		
		scanner.nextLine();
		String ID = scanner.nextLine();
		
		//Search for the guest in the system
		Guest guest = hotel.searchGuest(ID);
		
		//If the guest does not exist
		if (guest == null) {
	System.out.println("Guest not found!");
		}//end if
		else //Guest exists
		{
			//Get the reservation of this guest
			Reservation reservation = guest.getReservation();
			
			//If the guest does not have a reservation
		if (reservation == null )	
			System.out.println("This guest does not have a reservation.");
		else//guest has a reservation 
		{
			// When the reservation is cancelled, the room becomes available for other guests
			reservation.getRoom().setAvailable(true);
			
			// Cancel the reservation from the guest
		guest.cancelReservation();
		System.out.println("Reservation cancelled successfully");
		}//end else
		
		}
			
	}//end method cancelReservation
	
	
	public static void ShowAvailableRooms() {
		
		hotel.showAvailableRooms();
		
	}//end method showAvailableRooms
	
	public static void FindGuestRoom() {
		System.out.println("Enter your ID : ");
		scanner.nextLine();
	String id = scanner.nextLine();
	
	// Search for the guest in the hotel system
	Guest guest = hotel.searchGuest(id);
	
	// If the guest does not exist
	if ( guest == null )
		System.out.println("Invalid ID. No guest found with this ID. ");
	else {
		
		// Get the reservation of this guest
		Reservation reservation = guest.getReservation();
		
		// If the guest does not have a reservation
	if (reservation == null )
		
		System.out.println("Guest does not have reservation.  ");
	else// Guest has a reservation
	{
	System.out.println("| Guest name: "+ guest.getName() );
	System.out.print(" | Guest Room number: " + reservation.getRoom().getRoomNumber() ); 
	System.out.println(" | Guest room type: |" + reservation.getRoom().getRoomType());
	}//end else 
	
	}//end elseeee
	
	}//end method FindGuestRoom

	// main menu
			public static void MainMenu() {
			   System.out.println("===============================================================");
			   System.out.println("**  Grand Hayat Hotel  **");
			   System.out.println("===============================================================");
			   System.out.println("1- Book a room / Add reservation");
	           System.out.println("2- Cancel reservation");
	           System.out.println("3- Check-in");
		       System.out.println("4- Check-out");
	           System.out.println("5-Show available rooms");
		      System.out.println("6- Find the guest's room number");
		       System.out.println("0- Exit");
		       System.out.println("===============================================================");
		       System.out.print("Enter your choice: ");
			}// end method MainMenu
		
		
		
			// Make sure that the date format is correct
			public static boolean isValidDate(String date) {
		        if (date.length() != 10)   return false;
		        if (date.charAt(4) != '-') return false;
		        if (date.charAt(7) != '-') return false;
		        for (int i = 0; i < 10; i++) {
		        if (i == 4 || i == 7) continue;
		         if (date.charAt(i) < '0' || date.charAt(i) > '9') return false;
		        }
		        int year  = Integer.parseInt(date.substring(0, 4));
		        int month = Integer.parseInt(date.substring(5, 7));
		        int day   = Integer.parseInt(date.substring(8, 10));
		        if (year  < 2025 || year  > 2027) return false;
		        if (month < 1    || month > 12)   return false;
		        if (day   < 1    || day   > 31)   return false;
		        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) return false;
		        if (month == 2) {
		            boolean leap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
		            if (day > (leap ? 29 : 28)) return false;
		        }
		        return true;
		    }// end method isValidDate
		
			// Make sure that the departure date is after the arrival date
			public static boolean isAfter(String date1, String date2) {   
		        int d1 = Integer.parseInt(date1.substring(0, 4)) * 10000
		               + Integer.parseInt(date1.substring(5, 7)) * 100
		               + Integer.parseInt(date1.substring(8, 10));
		        int d2 = Integer.parseInt(date2.substring(0, 4)) * 10000
		               + Integer.parseInt(date2.substring(5, 7)) * 100
		               + Integer.parseInt(date2.substring(8, 10));
		        return d1 > d2;
		    }//end method isAfter
	

		public static void checkIn() {

		    // ask user to enter guest ID
		    System.out.print("Enter guest ID: ");
		    String id = scanner.nextLine();

		    // search for the guest in the system
		    Guest g = hotel.searchGuest(id);

		    // if guest not found, stop
		    if (g == null) {
		        System.out.println("Invalid ID / does not exist.");
		        return;
		    }

		    // get the reservation of the guest
		    Reservation r = g.getReservation();

		    // if no reservation, cannot check-in
		    if (r == null) {
		        System.out.println("Guest has no reservation.");
		        return;
		    }

		    // get the room of the reservation
		    Room room = r.getRoom();

		    // if room is already occupied, guest is already checked in
		    if (!room.isAvailable()) {
		        System.out.println("Guest is already checked in.");
		        return;
		    }

		    String date;

		    // repeat until user enters a valid date
		    do {
		        System.out.print("Enter check-in date (YYYY-MM-DD): ");
		        date = scanner.nextLine();

		        if (!isValidDate(date)) {
		            System.out.println("Invalid date. Try again.");
		        }

		    } while (!isValidDate(date));

		    // save check-in date in reservation
		    r.setCheckInDate(date);

		    // mark room as occupied
		    room.setAvailable(false);

		    System.out.println("Check-in successful.");
		    
		}//end method checkIn
		
		
		public static void checkOut() {

		    // ask user for guest ID
		    System.out.print("Enter guest ID: ");
		    String id = scanner.nextLine();

		    // search for the guest in the system
		    Guest g = hotel.searchGuest(id);

		    // if guest not found or not checked in
		    if (g == null) {
		        System.out.println("Invalid ID / does not exist / not checked in.");
		        return;
		    }

		    // get guest reservation
		    Reservation r = g.getReservation();

		    // if no reservation, cannot check-out
		    if (r == null) {
		        System.out.println("Guest has no reservation.");
		        return;
		    }

		    // get check-in date to compare with check-out date
		    String checkInDate = r.getCheckInDate();
		    String checkOutDate;

		    // repeat until valid date and after check-in date
		    do {
		        System.out.print("Enter check-out date (YYYY-MM-DD): ");
		        checkOutDate = scanner.nextLine();

		        // check if date format is correct
		        if (!isValidDate(checkOutDate)) {
		            System.out.println("Invalid date format.");
		        } 
		        // check if check-out date is after check-in date
		        else if (!isAfter(checkOutDate, checkInDate)) {
		            System.out.println("Check-out must be after check-in.");
		        }

		    } while (!isValidDate(checkOutDate) || !isAfter(checkOutDate, checkInDate));

		    // save check-out date in reservation
		    r.setCheckOutDate(checkOutDate);

		    // print guest information before removing
		    System.out.println("Guest Info:");
		    System.out.println("Name: " + g.getName());
		    System.out.println("ID: " + g.getId());
		    System.out.println("Check-in: " + checkInDate);
		    System.out.println("Check-out: " + checkOutDate);

		    // free the room (make it available again)
		    Room room = r.getRoom();
		    room.setAvailable(true);

		    // remove reservation from guest
		    g.checkOutReservation(r);

		    // try to remove guest from array using boolean method
		    if (hotel.removeGuest(id)) {
		        // if removal successful
		        System.out.println("Guest removed successfully. Goodbye!");
		    } 
		    else {
		        // if removal failed
		        System.out.println("Error: could not remove guest.");
		    }

		    System.out.println("Check-out completed.");
		}//end method checkOut


}//end class

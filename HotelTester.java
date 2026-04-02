import java.util.Scanner;

public class HotelTester {

	  public static void main(String[] args) {

	        Scanner input = new Scanner(System.in);
	        Hotel hotel = new Hotel(10, 10);

	        int choice;

	        do {
	            System.out.println("\n====== HOTEL MENU ======");
	            System.out.println("1. Add Room");
	            System.out.println("2. Show Available Rooms");
	            System.out.println("3. Add Guest");
	            System.out.println("4. Make Reservation");
	            System.out.println("5. Cancel Reservation");
	            System.out.println("6. Check-Out");
	            System.out.println("7. Search Guest");
	            System.out.println("8. Remove Guest");
	            System.out.println("9. Exit");
	            System.out.print("Enter choice: ");

	            choice = input.nextInt();
	            input.nextLine(); // clear buffer

	            switch (choice) {

	                //adding a room
	                case 1:
	                    System.out.println("Select Room Type:");
	                    System.out.println("1. Single Room");
	                    System.out.println("2. Double Room");
	                    System.out.println("3. Suite Room");
	                    int type = input.nextInt();

	                    System.out.print("Enter room number: ");
	                    int roomNum = input.nextInt();
	                    input.nextLine();

	                    Room newRoom = null;

	                    if (type == 1)
	                        newRoom = new SingleRoom(roomNum);
	                    else if (type == 2)
	                        newRoom = new DoubleRoom(roomNum);
	                    else if (type == 3)
	                        newRoom = new SuiteRoom(roomNum);

	                    if (newRoom != null && hotel.addRoom(newRoom))
	                        System.out.println("Room added successfully.");
	                    else
	                        System.out.println("Failed to add room.");
	                    break;

	                //showing available rooms
	                case 2:
	                    hotel.showAvailableRooms();
	                    break;

	                //adding a guest
	                case 3:
	                    System.out.print("Enter guest name: ");
	                    String name = input.nextLine();

	                    System.out.print("Enter guest ID: ");
	                    String id = input.nextLine();

	                    Guest g = new Guest(name, id);

	                    if (hotel.addGuest(g))
	                        System.out.println("Guest added successfully.");
	                    else
	                        System.out.println("Guest list is full.");
	                    break;

	                //making a reservation
	                case 4:
	                    System.out.print("Enter guest ID: ");
	                    String guestId = input.nextLine();

	                    Guest guest = hotel.searchGuest(guestId);

	                    if (guest == null) {
	                        System.out.println("Guest not found.");
	                        break;
	                    }

	                    System.out.print("Enter room number: ");
	                    roomNum = input.nextInt();
	                    input.nextLine();

	                    Room room = hotel.searchRoom(roomNum);

	                    if (room == null || !room.isAvailable()) {
	                        System.out.println("Room not available.");
	                        break;
	                    }

	                    Reservation r = new Reservation(room);

	                    System.out.print("Enter check-in date: ");
	                    r.setCheckInDate(input.nextLine());

	                    System.out.print("Enter check-out date: ");
	                    r.setCheckOutDate(input.nextLine());

	                    if (guest.addReservation(r))
	                        System.out.println("Reservation successful.");
	                    else
	                        System.out.println("Guest already has a reservation.");
	                    break;

	                //canceling a reservation
	                case 5:
	                    System.out.print("Enter guest ID: ");
	                    guestId = input.nextLine();

	                    guest = hotel.searchGuest(guestId);

	                    if (guest != null && guest.cancelReservation())
	                        System.out.println("Reservation cancelled.");
	                    else
	                        System.out.println("No reservation found.");
	                    break;

	                //checking out
	                case 6:
	                    System.out.print("Enter guest ID: ");
	                    guestId = input.nextLine();

	                    guest = hotel.searchGuest(guestId);

	                    if (guest != null && guest.getReservation() != null) {
	                        guest.checkout();
	                        System.out.println("Check-out completed.");
	                    } else {
	                        System.out.println("No reservation found.");
	                    }
	                    break;

	                //searching for a guest
	                case 7:
	                    System.out.print("Enter guest ID: ");
	                    guestId = input.nextLine();

	                    guest = hotel.searchGuest(guestId);

	                    if (guest != null)
	                        System.out.println(guest);
	                    else
	                        System.out.println("Guest not found.");
	                    break;

	                //removing a guest
	                case 8:
	                    System.out.print("Enter guest ID: ");
	                    guestId = input.nextLine();

	                    if (hotel.removeGuest(guestId))
	                        System.out.println("Guest removed.");
	                    else
	                        System.out.println("Guest not found.");
	                    break;

	                //exiting
	                case 9:
	                    System.out.println("Exiting system...");
	                    break;

	                //for a wrong choice
	                default:
	                    System.out.println("Invalid choice.");
	            }

	        } while (choice != 9);

	        input.close();
	    }
}

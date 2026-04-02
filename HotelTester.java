package Phase1;

import java.util.*;

public class HotelTester {

	public static Hotel hotel = new Hotel(100, 50);
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		Room room1 = new DoubleRoom(1);
		Room room2 = new DoubleRoom(2);
		Room room3 = new SingleRoom(3);
		Room room4 = new SingleRoom(4);
		Room room5 = new SuiteRoom(5);
		Room room6 = new SuiteRoom(6);

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

			switch (choice) {
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
			default:
				System.out.println("Invalid choice!");
			}
		} while (choice != 0);

	}// end main

	public static void addReservation() {

		if (hotel.hasAvailableRooms() == true) {

			System.out.println("Enter your Name: ");
			scanner.nextLine();
			String GuestName = scanner.nextLine();

			System.out.println("Enter your ID : ");
			String GuestId = scanner.nextLine();

			while (GuestId.equals("") || GuestId.charAt(0) != '1') {

				if (GuestId.equals(""))
					System.out.println("Sorry, ID cannot be empty! ");
				else
					System.out.println("Invalid ID! The ID must start with number 1. ");

				System.out.println("Enter your ID : ");
				GuestId = scanner.nextLine();
			}

			Guest guest = hotel.searchGuest(GuestId);

			if (guest != null && guest.getReservation() != null) {
				System.out.println("This guest already has a reservation. ");
			}

			else {

				System.out.println("Starting reservation process...");

				hotel.showAvailableRooms();

				System.out.println("Enter the room number you want to reserve:");
				int RoomNumber = scanner.nextInt();

				Room room = hotel.searchRoom(RoomNumber);

				while (room == null || room.isAvailable() == false) {

					if (room == null)
						System.out.println("Invalid Room Number, We have not this room");
					else
						System.out.println("Sorry, room is not available ");

					System.out.println("Enter The number of the rooms: ");
					RoomNumber = scanner.nextInt();

					room = hotel.searchRoom(RoomNumber);
				}

				if (guest == null) {
					guest = new Guest(GuestName, GuestId);
					hotel.addGuest(guest);
				}

				Reservation reservation = new Reservation(room);

				guest.addReservation(reservation);
				System.out.println("Reservation added successfully!");
			}

		}

		else
			System.out.println("Sorry all rooms are currently booked! no rooms available");

	}// end method addReservation

	public static void cancelReservation() {

		System.out.println("Enter your ID :");

		scanner.nextLine();
		String ID = scanner.nextLine();

		Guest guest = hotel.searchGuest(ID);

		if (guest == null) {
			System.out.println("Guest not found!");
		} else {
			Reservation reservation = guest.getReservation();

			if (reservation == null)
				System.out.println("This guest does not have a reservation.");
			else {
				guest.cancelReservation();
				System.out.println("Reservation cancelled successfully");
			}

		}

	}// end method cancelReservation

	public static void ShowAvailableRooms() {
		hotel.showAvailableRooms();
	}// end method showAvailableRooms

	public static void FindGuestRoom() {
		System.out.println("Enter your ID : ");
		scanner.nextLine();
		String id = scanner.nextLine();

		Guest guest = hotel.searchGuest(id);

		if (guest == null)
			System.out.println("Invalid ID. No guest found with this ID. ");
		else {

			Reservation reservation = guest.getReservation();

			if (reservation == null)
				System.out.println("Guest does not have reservation.  ");
			else {
				System.out.println("| Guest name: " + guest.getName());
				System.out.print("| Guest Room number: " + reservation.getRoom().getRoomNumber());
				System.out.println(" | Guest room type: " + reservation.getRoom().getRoomType());
			}

		}

	}// end method FindGuestRoom

	// main menu
	public static void MainMenu() {
		System.out.println("===============================================================");
		System.out.println("**  Grand Hayat Hotel  **");
		System.out.println("===============================================================");
		System.out.println("1- Book a room / Add reservation");
		System.out.println("2- Cancel reservation");
		System.out.println("3- Check-in");
		System.out.println("4- Check-out");
		System.out.println("5- Show available rooms");
		System.out.println("6- Find the guest's room number");
		System.out.println("0- Exit");
		System.out.println("===============================================================");
		System.out.print("Enter your choice: ");
	}// end method MainMenu

	// Make sure that the date format is correct
	public static boolean isValidDate(String date) {
		if (date.length() != 10)
			return false;
		if (date.charAt(4) != '-')
			return false;
		if (date.charAt(7) != '-')
			return false;
		for (int i = 0; i < 10; i++) {
			if (i == 4 || i == 7)
				continue;
			if (date.charAt(i) < '0' || date.charAt(i) > '9')
				return false;
		}
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));
		if (year < 2025 || year > 2027)
			return false;
		if (month < 1 || month > 12)
			return false;
		if (day < 1 || day > 31)
			return false;
		if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30)
			return false;
		if (month == 2) {
			boolean leap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
			if (day > (leap ? 29 : 28))
				return false;
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
	}// end method isAfter

	public static void checkIn() {

		System.out.print("Enter guest ID: ");
		scanner.nextLine();
		String id = scanner.nextLine();

		Guest g = hotel.searchGuest(id);

		if (g == null) {
			System.out.println("Invalid ID / does not exist.");
			return;
		}

		Reservation r = g.getReservation();

		if (r == null) {
			System.out.println("Guest has no reservation.");
			return;
		}

		if (r.getCheckInDate() != null && r.getCheckOutDate() == null) {
			System.out.println("Guest is already checked in.");
			return;
		}

		String date;

		do {
			System.out.print("Enter check-in date (YYYY-MM-DD): ");
			date = scanner.nextLine();

			if (!isValidDate(date)) {
				System.out.println("Invalid date. Try again.");
			}

		} while (!isValidDate(date));

		r.setCheckInDate(date);

		System.out.println("Check-in successful.");

	}// end method checkIn

	public static void checkOut() {

		System.out.print("Enter guest ID: ");
		scanner.nextLine();
		String id = scanner.nextLine();

		Guest g = hotel.searchGuest(id);

		if (g == null) {
			System.out.println("Invalid ID / does not exist / not checked in.");
			return;
		}

		Reservation r = g.getReservation();

		if (r == null) {
			System.out.println("Guest has no reservation.");
			return;
		}

		String checkInDate = r.getCheckInDate();

		if (checkInDate == null) {
			System.out.println("Guest has not checked in yet.");
			return;
		}

		String checkOutDate;

		do {
			System.out.print("Enter check-out date (YYYY-MM-DD): ");
			checkOutDate = scanner.nextLine();

			if (!isValidDate(checkOutDate)) {
				System.out.println("Invalid date format.");
			} else if (!isAfter(checkOutDate, checkInDate)) {
				System.out.println("Check-out must be after check-in.");
			}

		} while (!isValidDate(checkOutDate) || !isAfter(checkOutDate, checkInDate));

		r.setCheckOutDate(checkOutDate);

		System.out.println("Guest Info:");
		System.out.println("Name: " + g.getName());
		System.out.println("ID: " + g.getID());
		System.out.println("Check-in: " + checkInDate);
		System.out.println("Check-out: " + checkOutDate);

		g.checkOutReservation();

		if (hotel.removeGuest(id)) {
			System.out.println("Guest removed successfully. Goodbye!");
		} else {
			System.out.println("Error: could not remove guest.");
		}

		System.out.println("Check-out completed.");
	}// end method checkOut

}// end class
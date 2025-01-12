import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ReservationScanner {

        public static Reservation scanReservation() {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter reservation details:");

            // Get Room ID
            int idRoom = getIntInput(scanner, "Room ID: ");

            // Get User ID
            int idUser = getIntInput(scanner, "User ID: ");

            // Get Start Time
            LocalTime startDate = getTimeInput(scanner, "Start time (HH:mm): ");

            // Get End Time
            LocalTime endDate = getTimeInput(scanner, "End time (HH:mm): ");

            // Get Reservation Date
            LocalDate date = getDateInput(scanner, "Reservation date (YYYY-MM-DD): ");

            // Create Reservation object with input values
            Reservation reservation = new Reservation(idRoom, idUser, startDate, endDate, date);

            System.out.println("\nReservation details:");
            System.out.println(reservation);

            return reservation;
        }

        // Method to handle integer inputs (Room ID, User ID)
        private static int getIntInput(Scanner scanner, String prompt) {
            int input = 0;
            boolean valid = false;
            while (!valid) {
                try {
                    System.out.print(prompt);
                    input = Integer.parseInt(scanner.nextLine());  // Read and parse integer input
                    valid = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            }
            return input;
        }

        // Method to handle time input (Start Time, End Time)
        private static LocalTime getTimeInput(Scanner scanner, String prompt) {
            LocalTime time = null;
            boolean valid = false;
            while (!valid) {
                try {
                    System.out.print(prompt);
                    String timeString = scanner.nextLine();
                    time = LocalTime.parse(timeString);  // Parse time string to LocalTime
                    valid = true;
                } catch (Exception e) {
                    System.out.println("Invalid time format. Please enter the time in HH:mm format.");
                }
            }
            return time;
        }

        // Method to handle date input (Reservation Date)
        private static LocalDate getDateInput(Scanner scanner, String prompt) {
            LocalDate date = null;
            boolean valid = false;
            while (!valid) {
                try {
                    System.out.print(prompt);
                    String dateString = scanner.nextLine();
                    date = LocalDate.parse(dateString);  // Parse date string to LocalDate
                    valid = true;
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
                }
            }
            return date;
        }
    }




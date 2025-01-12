import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ReservationScanner {
    public static Reservation scanReservation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter reservation details:");

        System.out.print("Room ID: ");
        int idRoom = scanner.nextInt();

        System.out.print("User ID: ");
        int idUser = scanner.nextInt();

        System.out.print("Start time (HH:mm): ");
        scanner.nextLine(); // consume newline character
        String startTime = scanner.nextLine();
        LocalTime startDate = LocalTime.parse(startTime);

        System.out.print("End time (HH:mm): ");
        String endTime = scanner.nextLine();
        LocalTime endDate = LocalTime.parse(endTime);

        System.out.print("Reservation date (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateString);

        // Create Reservation object with input values
        Reservation reservation = new Reservation(idRoom, idUser, startDate, endDate, date);

        System.out.println("\nReservation details:");
        System.out.println(reservation);

        scanner.close();
        return reservation;
    }
    }


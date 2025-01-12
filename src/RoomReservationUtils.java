import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class RoomReservationUtils {

    public static boolean verifyDisponibility(List<Reservation> reservations, Reservation reservationFromInput) {
         // Filter reservations for the same room and date
            ReservationServiceClient reservationServiceClient = new ReservationServiceClient();
            List<Reservation> resFetched = reservationServiceClient.getReservationsByID(String.valueOf(reservationFromInput.getIdRoom()));

            // Filter for reservations on the same date and room
            List<Reservation> filteredReservations = new ArrayList<>();
            for (Reservation r : resFetched) {
                if (r.getIdRoom() == reservationFromInput.getIdRoom() &&
                        r.getDate().equals(reservationFromInput.getDate())) {
                    filteredReservations.add(r);
                }
            }

            // Sort the filtered reservations by start date
            filteredReservations.sort(Comparator.comparing(Reservation::getStartDate));
            System.out.println("Filtered Reservations sorted: " + filteredReservations);

            // Check for overlapping times
            for (Reservation existingReservation : filteredReservations) {
                if (
                        reservationFromInput.getStartDate().isBefore(existingReservation.getEndDate()) &&
                                reservationFromInput.getEndDate().isAfter(existingReservation.getStartDate())
                ) {
                    return false; // Overlap detected
                }
            }

            return true; // No overlap, reservation is available
        }


        public static Reservation inputReservation() {
       return ReservationScanner.scanReservation();
    }

    public static LocalDate scannerDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your desired date: ");
        String dateString = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateString);
        return date;
    }

    public static int scannerCapacity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Care este capacitatea dorita? ");
        int cap = scanner.nextInt();
        return cap;
    }

    public static int scannerID() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your desired ID: ");
        int idIn = scanner.nextInt();
        return idIn;
    }
}

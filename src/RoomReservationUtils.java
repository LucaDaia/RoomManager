import java.util.Comparator;
import java.util.List;

public class RoomReservationUtils {

    public static boolean verifyDisponibility(List<Reservation> reservations, Reservation reservationFromInput) {

        //filter

        //sort by start date
        reservations.sort(Comparator.comparing(Reservation::getStartDate));
        System.out.println("Reservations sorted: " + reservations);

       for(int i=1;i<reservations.size();i++) {
           if(reservationFromInput.getStartDate().isBefore(reservations.get(i).getEndDate()) ||
                   reservationFromInput.getEndDate().isAfter(reservations.get(i).getStartDate())) {
               return false;
           } else return true;

       }

        return true;
    }

    public static Reservation inputReservation() {
       return ReservationScanner.scanReservation();
    }
}

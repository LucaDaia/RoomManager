import java.util.List;

public class UserFacade implements UserFacadeInt{

    @Override
    public void reserveRoom() {
        String roomsFetched;
        try {
           roomsFetched = Fetcher.fetchRooms(URLs.urlRoomApi);
        } catch (Exception e) {
            throw new RuntimeException("Couldnt reserve room");
        }
        System.out.println();

        ReservationServiceClient reservationServiceClient = new ReservationServiceClient();
        List<Reservation> reservationList = reservationServiceClient.getReservations();
        System.out.println(reservationList);

        Reservation inputReservation = RoomReservationUtils.inputReservation();
//        if(RoomReservationUtils.verifyDisponibility(reservationList, inputReservation) == true) {
//            System.out.println("Room can be reserved");
//            //post request to reservations
//        } else {
//            System.out.println("Room cannot be reserved!");
//        }


        AvailableRoomSpecification availableRoomSpecification = new AvailableRoomSpecification(reservationList, inputReservation.getDate(), inputReservation.getIdRoom());
        List<Reservation> filteredReservations = availableRoomSpecification.filterReservations();
        System.out.println("Rezervări disponibile: " + filteredReservations);

    }
}

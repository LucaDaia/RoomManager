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

        Reservation r = RoomReservationUtils.inputReservation();
        if(RoomReservationUtils.verifyDisponibility(reservationList, r) == true) {
            System.out.println("Room can be reserved");
            //post request to reservations
        } else {
            System.out.println("Room cannot be reserved!");
        }


    }
}

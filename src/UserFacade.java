import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserFacade implements UserFacadeInt{

    @Override
    public void reserveRoom() {
//        String roomsFetched;
//        try {
//           roomsFetched = Fetcher.fetchRooms(URLs.urlRoomApi);
//        } catch (Exception e) {
//            throw new RuntimeException("Couldnt reserve room");
//        }
//        System.out.println();

        ReservationServiceClient reservationServiceClient = new ReservationServiceClient();
        List<Reservation> reservationList = reservationServiceClient.getReservations();
        System.out.println(reservationList);
/// --------------------------------------------

        RoomServiceClient roomServiceClient = new RoomServiceClient();
        List<Room> roomsList = roomServiceClient.getRooms();

        int inputCapacity = RoomReservationUtils.scannerCapacity();
        CapacityRoomSpecification capacityRoomSpecification = new CapacityRoomSpecification(roomsList, inputCapacity);
        List<Room> filteredRooms = capacityRoomSpecification.isSatisfiedBy();

        System.out.println("Urmatoarele sali au capacitatea necesara: ");
        System.out.println(filteredRooms);


//        LocalDate dateReservation = RoomReservationUtils.scannerDate();

        Reservation inputReservation = RoomReservationUtils.inputReservation();
//        reservationServiceClient.postReservation(inputReservation);
        if(RoomReservationUtils.verifyDisponibility(reservationList, inputReservation) == true) {
            System.out.println("Room can be reserved");
            reservationServiceClient.postReservation(inputReservation);

            //post request to reservations
        } else {
            System.out.println("Room cannot be reserved in that time period!");
        }



//        AvailableRoomSpecification availableRoomSpecification = new AvailableRoomSpecification(reservationList, inputReservation.getDate(), inputReservation.getIdRoom());
//        List<Reservation> filteredReservations = availableRoomSpecification.isSatisfiedBy();
//        System.out.println("Rezervări disponibile: " + filteredReservations);

    }

    @Override
    public void showRooms() {
        RoomServiceClient roomServiceClient = new RoomServiceClient();
        System.out.println(roomServiceClient.getRooms());
    }

    @Override
    public void showReservations() {
        ReservationServiceClient reservationServiceClient = new ReservationServiceClient();
        System.out.println(reservationServiceClient.getReservations());
    }

    @Override
    public void filterByRoom() {

    }

    @Override
    public void filterByCapacity() {
        int capIn = RoomReservationUtils.scannerCapacity();

        RoomServiceClient roomServiceClient = new RoomServiceClient();
        List<Room> roomsList = roomServiceClient.getRooms();
        CapacityRoomSpecification capacityRoomSpecification = new CapacityRoomSpecification(roomsList, capIn);
        System.out.println(capacityRoomSpecification.isSatisfiedBy());
    }

    @Override
    public void filterByDateAndId() {
        LocalDate dateIn = RoomReservationUtils.scannerDate();
        int idIn = RoomReservationUtils.scannerID();
        ReservationServiceClient reservationServiceClient = new ReservationServiceClient();
        List <Reservation> reservationList = reservationServiceClient.getReservations();
        AvailableRoomSpecification availableRoomSpecification = new AvailableRoomSpecification(reservationList, dateIn, idIn);
        System.out.println(availableRoomSpecification.isSatisfiedBy());
    }

    @Override
    public void cloneReservation() {
        System.out.println("------------------------------------");
        System.out.print("📌 Enter the ID of the reservation: ");
        Scanner scanner = new Scanner(System.in);
        int originalId = scanner.nextInt();
        scanner.nextLine();

        ReservationServiceClient reservationServiceClient = new ReservationServiceClient();
        List<Reservation> reservations = reservationServiceClient.getReservations();

        Reservation original = reservations.stream()
                .filter(r -> r.getIdReservation() == originalId)
                .findFirst()
                .orElse(null);

        if (original == null) {
            System.out.println("❌ Wrong id.");
            return;
        }

        System.out.println("Reservation found: " + original);

        System.out.print("Enter the new reservation date (YYYY-MM-DD): ");
        LocalDate newDate = LocalDate.parse(scanner.nextLine());

        Reservation clonedReservation = original.clone();
        clonedReservation.setDate(newDate);

        if(RoomReservationUtils.verifyDisponibility(reservations, original) == true) {
            System.out.println("Room can be reserved");
            reservationServiceClient.postReservation(original);

        } else {
            System.out.println("Room cannot be reserved in that time period!");
        }
        reservationServiceClient.postReservation(clonedReservation);
        System.out.println("✅ Reservation duplicated succesfully!");
    }
}

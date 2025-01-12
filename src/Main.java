import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
//        RoomServiceClient roomServiceClient = new RoomServiceClient();
//        roomServiceClient.getRooms();
//
        UserFacade userFacade = new UserFacade();
//        userFacade.reserveRoom();

       Scanner scanner = new Scanner(System.in);



        ClientApp clientApp = ClientApp.getInstance();

        while (true) {
            clientApp.showMenu();
            System.out.print("Pick an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumă newline

            switch (choice) {
                case 1 -> userFacade.showReservations();
                case 2 -> userFacade.reserveRoom();
                case 3 -> userFacade.filterByCapacity();
                case 4 -> userFacade.filterByDateAndId();
//                case 5 -> cloneReservation(scanner);
                case 6 -> userFacade.showRooms();
                case 7 ->
                {
                    System.out.println("📌 Aplicația s-a închis.");
                    return;
                }
                default -> System.out.println("❌ Opțiune invalidă. Alege din nou.");
            }
        }

    }
}
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientApp {
    private static final List<Reservation> reservations = new ArrayList<>();
    private static int idCounter = 100;

    // Singleton instance
    private static ClientApp instance;

    // Private constructor to prevent direct instantiation
    private ClientApp() {
    }

    // Public method to access the singleton instance
    public static synchronized ClientApp getInstance() {
        if (instance == null) {
            instance = new ClientApp();
        }
        return instance;
    }

    public void showMenu() {
        System.out.println("\n====================================");
        System.out.println("📌 Reservation Manager");
        System.out.println("====================================");
        System.out.println("1. Show all reservations");
        System.out.println("2. Add a new reservation");
        System.out.println("3. Filter the room by capacity");
        System.out.println("4. Filter the reservations by date");
        System.out.println("5. Duplicate an existing reservation");
        System.out.println("6. Show all rooms");;
        System.out.println("7. Quit");
    }

    public void showReservations() {
        System.out.println("------------------------------------");
        System.out.println("📌 Lista rezervărilor existente:");
        if (reservations.isEmpty()) {
            System.out.println("❌ Nu există rezervări.");
        } else {
            reservations.forEach(System.out::println);
        }
    }



//    private void cloneReservation(Scanner scanner) {
//        System.out.println("------------------------------------");
//        System.out.print("📌 Introduceți ID-ul rezervării pe care doriți să o clonați: ");
//        int originalId = scanner.nextInt();
//        scanner.nextLine();
//
//        Reservation original = reservations.stream()
//                .filter(r -> r.getId() == originalId)
//                .findFirst()
//                .orElse(null);
//
//        if (original == null) {
//            System.out.println("❌ Rezervare inexistentă.");
//            return;
//        }
//
//        System.out.print("Introduceți noua dată a rezervării (YYYY-MM-DD): ");
//        LocalDate newDate = LocalDate.parse(scanner.nextLine());
//
//        Reservation clonedReservation = original.clone();
//        clonedReservation.setDate(newDate);
//        clonedReservation.setId(idCounter++);
//
//        reservations.add(clonedReservation);
//        System.out.println("✅ Rezervare clonată cu succes!");
//        System.out.println(clonedReservation);
//    }
}

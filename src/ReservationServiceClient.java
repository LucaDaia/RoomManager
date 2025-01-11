import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationServiceClient {

    private List<Reservation> reservationsFetchedList = new ArrayList<>();

    public List<Reservation> getReservations() {
        String apiUrl = URLs.urlReservationApi; // Replace with your backend's URL
        try {
            String jsonResponse = Fetcher.fetchRooms(apiUrl);

            // Remove leading and trailing brackets to get individual JSON objects
            jsonResponse = jsonResponse.substring(1, jsonResponse.length() - 1);

            // Split JSON objects by "},{" delimiter
            String[] reservationObjects = jsonResponse.split("},\\{");

            for (String reservationObject : reservationObjects) {
                // Clean up JSON formatting
                reservationObject = reservationObject.replace("{", "").replace("}", "");
                String[] keyValuePairs = reservationObject.split(",");

                Reservation reservation = new Reservation();
                for (String pair : keyValuePairs) {
                    String[] keyValue = pair.split(":");
                    String key = keyValue[0].replace("\"", "").trim();
                    String value = keyValue[1].replace("\"", "").trim();

                    switch (key) {
                        case "idReservation":
                            reservation.setIdReservation(Integer.parseInt(value));
                            break;
                        case "idRoom":
                            reservation.setIdRoom(Integer.parseInt(value));
                            break;
                        case "idUser":
                            reservation.setIdUser(Integer.parseInt(value));
                            break;
                        case "startDate":
                            // Check if value has only an hour (e.g., "14") and append ":00:00"
                            if (value.length() == 2) {
                                value += ":00:00";  // Append default minutes and seconds
                            }
                            reservation.setStartDate(LocalTime.parse(value));
                            break;
                        case "endDate":
                            // Same for endDate if it's only an hour
                            if (value.length() == 2) {
                                value += ":00:00";  // Append default minutes and seconds
                            }
                            reservation.setEndDate(LocalTime.parse(value));
                            break;
                        case "date":
                            reservation.setDate(LocalDate.parse(value));
                            break;
                        default:
                            // Handle unexpected keys if necessary
                            System.err.println("Unknown key: " + key);
                    }
                }
                reservationsFetchedList.add(reservation);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.reservationsFetchedList;
    }
}

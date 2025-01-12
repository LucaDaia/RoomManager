import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReservationServiceClient {

    private List<Reservation> reservationsFetchedList = new ArrayList<>();

    public List<Reservation> getReservations() {
        String apiUrl = URLs.urlReservationApi; // Replace with your backend's URL
        try {
            String jsonResponse = Fetcher.fetch(apiUrl);

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

    public void postReservation(Reservation reservation) {
        try {
            // Open connection to the API
            URL url = new URL(URLs.urlReservationApi);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configure the connection
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Serialize Reservation object into JSON
            String jsonInput = toJson(reservation);

            // Write JSON payload to the request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Handle response
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Reservation created successfully!");
            } else {
                System.out.println("Failed to create reservation. Response Code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Convert Reservation object to JSON
    private String toJson(Reservation reservation) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return String.format("""
            {
                "idRoom": %d,
                "idUser": %d,
                "startDate": "%s",
                "endDate": "%s",
                "date": "%s"
            }
            """,

                reservation.getIdRoom(),
                reservation.getIdUser(),
                reservation.getStartDate().format(timeFormatter),
                reservation.getEndDate().format(timeFormatter),
                reservation.getDate().format(dateFormatter)
        );
    }

    public List<Reservation> getReservationsByID(String idRoom) {
        String apiUrl = URLs.urlReservationApi + "/" + idRoom; // Replace with your backend's URL
        List<Reservation> reservations = new ArrayList<>();

        try {
            // Fetch JSON response from the API
            String jsonResponse = Fetcher.fetch(apiUrl);

            // Remove leading and trailing brackets if the response is a JSON array
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
                            if (value.length() == 2) {
                                value += ":00:00"; // Append default minutes and seconds
                            }
                            reservation.setStartDate(LocalTime.parse(value));
                            break;
                        case "endDate":
                            if (value.length() == 2) {
                                value += ":00:00"; // Append default minutes and seconds
                            }
                            reservation.setEndDate(LocalTime.parse(value));
                            break;
                        case "date":
                            reservation.setDate(LocalDate.parse(value));
                            break;
                        default:
                            System.err.println("Unknown key: " + key);
                    }
                }
                reservations.add(reservation);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reservations; // Return the list of reservations
    }
}

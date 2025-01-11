import java.util.ArrayList;
import java.util.List;

public class RoomServiceClient {

    private List<Room> roomsFetchedList = new ArrayList<>();

    public List<Room> getRooms() {
        String apiUrl = URLs.urlRoomApi; // Replace with your backend's URL
        try {
            String jsonResponse = Fetcher.fetchRooms(apiUrl);

            // Remove leading and trailing brackets to get individual JSON objects
            jsonResponse = jsonResponse.substring(1, jsonResponse.length() - 1);

            // Split JSON objects by "},{" delimiter
            String[] roomObjects = jsonResponse.split("},\\{");

            for (String roomObject : roomObjects) {
                // Clean up JSON formatting
                roomObject = roomObject.replace("{", "").replace("}", "");
                String[] keyValuePairs = roomObject.split(",");

                Room room = new Room();
                for (String pair : keyValuePairs) {
                    String[] keyValue = pair.split(":");
                    String key = keyValue[0].replace("\"", "").trim();
                    String value = keyValue[1].replace("\"", "").trim();

                    switch (key) {
                        case "id":
                            room.setId(Integer.parseInt(value));
                            break;
                        case "roomName":
                            room.setRoomName(value);
                            break;
                        case "location":
                            room.setLocation(value);
                            break;
                        case "capacity":
                            room.setCapacity(Integer.parseInt(value));
                            break;
                        default:
                            // Handle unexpected keys if necessary
                            System.err.println("Unknown key: " + key);
                    }
                }
                roomsFetchedList.add(room);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.roomsFetchedList;
    }
}
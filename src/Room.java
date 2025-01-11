public class Room {
    private int id;
    private String roomName;
    private String location;
    private int capacity;

    public Room(int id, String roomName, String location, int capacity) {
        this.id = id;
        this.roomName = roomName;
        this.location = location;
        this.capacity = capacity;
    }

    public Room() {
    }

        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

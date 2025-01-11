public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        RoomServiceClient roomServiceClient = new RoomServiceClient();
        roomServiceClient.getRooms();

        UserFacade userFacade = new UserFacade();
        userFacade.reserveRoom();

    }
}
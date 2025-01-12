import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation implements Prototype<Reservation>{
    private int idReservation;
    private int idRoom;
    private int idUser;
    private LocalTime startDate;
    private LocalTime endDate;
    private LocalDate date;

    public Reservation(int idReservation, int idRoom, int idUser, LocalTime startDate, LocalTime endDate, LocalDate date) {
        this.idReservation = idReservation;
        this.idRoom = idRoom;
        this.idUser = idUser;
        this.startDate = startDate;
        this.endDate = endDate;
        this.date = date;
    }

    public Reservation(int idRoom, int idUser, LocalTime startDate, LocalTime endDate, LocalDate date) {
        this.idRoom = idRoom;
        this.idUser = idUser;
        this.startDate = startDate;
        this.endDate = endDate;
        this.date = date;
    }

    public Reservation() {
    }

        private Reservation(Reservation original) {
            this.idReservation = 0;
            this.idRoom = original.getIdRoom();
            this.startDate = original.getStartDate();
            this.endDate = original.getEndDate();
            this.date = original.getDate();
        }


    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public LocalTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalTime startDate) {
        this.startDate = startDate;
    }

    public LocalTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalTime endDate) {
        this.endDate = endDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "\nReservation Details:\n" +
                "ID: " + idReservation + "\n" +
                "Room ID: " + idRoom + "\n" +
                "User ID: " + idUser + "\n" +
                "Start Time: " + startDate + "\n" +
                "End Time: " + endDate + "\n" +
                "Date: " + date + "\n";
    }

    @Override
    public Reservation clone() {
        return new Reservation(this);
    }
}

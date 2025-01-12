import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class AvailableRoomSpecification implements Specification<Reservation> {

    private final List<Reservation> reservationList;
    private final LocalDate date;
    private final Integer idRoom;


    public AvailableRoomSpecification(List<Reservation> reservationList, LocalDate date, Integer idRoom) {
        this.reservationList = reservationList;
        this.date = date;
        this.idRoom = idRoom;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    @Override
    public List<Reservation> isSatisfiedBy() {
        return reservationList.stream()
                .filter(r -> r.getDate().equals(this.date) && Objects.equals(r.getIdRoom(), this.idRoom))
                .toList();
    }




}

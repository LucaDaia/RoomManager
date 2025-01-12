import java.util.List;
import java.util.Objects;

public class CapacityRoomSpecification implements Specification<Room>{
    private final List<Room> roomsList;
    private final int capacity;

    public CapacityRoomSpecification(List<Room> roomsList, int capacity) {
        this.roomsList = roomsList;
        this.capacity = capacity;
    }


    @Override
    public List<Room> isSatisfiedBy() {
        return roomsList.stream()
                .filter(r -> Objects.equals(r.getCapacity(), this.capacity))
                .toList();
    }
}

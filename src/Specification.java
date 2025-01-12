import java.util.List;

public interface Specification<T> {
    List<T> isSatisfiedBy();

}
public interface Specification<T> {
    boolean isSatisfiedBy(T t);

    default Specification<T> and(Specification<T> other) {
        return item -> this.isSatisfiedBy(item) && other.isSatisfiedBy(item);
    }

    default Specification<T> or(Specification<T> other) {
        return item -> this.isSatisfiedBy(item) || other.isSatisfiedBy(item);
    }

    default Specification<T> not() {
        return item -> !this.isSatisfiedBy(item);
    }
}
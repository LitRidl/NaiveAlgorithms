package li.rid.study.algorithms;

import java.util.Objects;

public class Pair<T extends Comparable<T>> implements Comparable<Pair<T>> {
    T first;
    T second;

    Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public static <K extends Comparable<K>> Pair<K> of(K first, K second) {
        return new Pair<K>(first, second);
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" + first + ", " + second + '}';
    }

    @Override
    public int compareTo(Pair<T> other) {
        int first = this.first.compareTo(other.first);
        int second = this.second.compareTo(other.second);
        if (first == 0) {
            return second;
        }
        return first;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?> pair = (Pair<?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}

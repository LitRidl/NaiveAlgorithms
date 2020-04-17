package li.rid.study.algorithms.util;

import java.util.Objects;

public class Range<T> {
    T min, max;

    public Range(T min, T max) {
        this.min = min;
        this.max = max;
    }

    public static <T> Range<T> of(T min, T max) {
        return new Range<>(min, max);
    }

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range<?> range = (Range<?>) o;
        return getMin().equals(range.getMin()) && getMax().equals(range.getMax());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMin(), getMax());
    }

    @Override
    public String toString() {
        return "Range(" + min + ", " + max + ')';
    }
}

package li.rid.study.algorithms.util;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.function.BiPredicate;

public final class OrderDetector {
    private OrderDetector() {
        throw new AssertionError();
    }

    // Order checking predicates
    private static <T extends Comparable<? super T>> boolean isIncreasing(T el, T elNext) {
        return elNext.compareTo(el) > 0;
    }

    private static <T extends Comparable<? super T>> boolean isDecreasing(T el, T elNext) {
        return elNext.compareTo(el) < 0;
    }

    private static <T extends Comparable<? super T>> boolean isNonIncreasing(T el, T elNext) {
        return elNext.compareTo(el) <= 0;
    }

    private static <T extends Comparable<? super T>> boolean isNonDecreasing(T el, T elNext) {
        return elNext.compareTo(el) >= 0;
    }

    // Arrays
    private static <T extends Comparable<? super T>> boolean checkOrder(T[] a, BiPredicate<T, T> trueCondition) {
        for (int i = 0; i < a.length - 1; i++) {
            if (!trueCondition.test(a[i], a[i + 1])) {
                return false;
            }
        }
        return true;
    }

    public static <T extends Comparable<? super T>> boolean isIncreasing(T[] a) {
        return checkOrder(a, OrderDetector::isIncreasing);
    }

    public static <T extends Comparable<? super T>> boolean isDecreasing(T[] a) {
        return checkOrder(a, OrderDetector::isDecreasing);
    }

    public static <T extends Comparable<? super T>> boolean isNonIncreasing(T[] a) {
        return checkOrder(a, OrderDetector::isNonIncreasing);
    }

    public static <T extends Comparable<? super T>> boolean isNonDecreasing(T[] a) {
        return checkOrder(a, OrderDetector::isNonDecreasing);
    }

    public static <T extends Comparable<? super T>> boolean isMonotonic(T[] a) {
        return isNonIncreasing(a) || isNonDecreasing(a);
    }

    // Lists implementing RandomAccess
    private static <T extends Comparable<? super T>, L extends List<T> & RandomAccess> boolean checkOrder(L l, BiPredicate<T, T> trueCondition) {
        for (int i = 0; i < l.size() - 1; i++) {
            if (!trueCondition.test(l.get(i), l.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    public static <T extends Comparable<? super T>, L extends List<T> & RandomAccess> boolean isIncreasing(L l) {
        return checkOrder(l, OrderDetector::isIncreasing);
    }

    public static <T extends Comparable<? super T>, L extends List<T> & RandomAccess> boolean isDecreasing(L l) {
        return checkOrder(l, OrderDetector::isDecreasing);
    }

    public static <T extends Comparable<? super T>, L extends List<T> & RandomAccess> boolean isNonIncreasing(L l) {
        return checkOrder(l, OrderDetector::isNonIncreasing);
    }

    public static <T extends Comparable<? super T>, L extends List<T> & RandomAccess> boolean isNonDecreasing(L l) {
        return checkOrder(l, OrderDetector::isNonDecreasing);
    }

    public static <T extends Comparable<? super T>, L extends List<T> & RandomAccess> boolean isMonotonic(L l) {
        return isNonIncreasing(l) || isNonDecreasing(l);
    }

    // Arbitrary Iterables
    private static <T extends Comparable<? super T>, C extends Iterable<T>> boolean checkOrder(C c, BiPredicate<T, T> trueCondition) {
        Iterator<T> it = c.iterator();
        if (!it.hasNext()) {
            return true;
        }
        for (T el = it.next(), elNext; it.hasNext(); el = elNext) {
            elNext = it.next();
            if (!trueCondition.test(el, elNext)) {
                return false;
            }
        }
        return true;
    }

    public static <T extends Comparable<? super T>, C extends Iterable<T>> boolean isIncreasing(C c) {
        return checkOrder(c, OrderDetector::isIncreasing);
    }

    public static <T extends Comparable<? super T>, C extends Iterable<T>> boolean isDecreasing(C c) {
        return checkOrder(c, OrderDetector::isDecreasing);
    }

    public static <T extends Comparable<? super T>, C extends Iterable<T>> boolean isNonIncreasing(C c) {
        return checkOrder(c, OrderDetector::isNonIncreasing);
    }

    public static <T extends Comparable<? super T>, C extends Iterable<T>> boolean isNonDecreasing(C c) {
        return checkOrder(c, OrderDetector::isNonDecreasing);
    }

    public static <T extends Comparable<? super T>, C extends Iterable<T>> boolean isMonotonic(C c) {
        return isNonIncreasing(c) || isNonDecreasing(c);
    }
}

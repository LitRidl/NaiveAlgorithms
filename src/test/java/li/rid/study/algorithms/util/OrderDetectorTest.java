package li.rid.study.algorithms.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

enum SequenceType {
    INCREASING,
    DECREASING,
    NONINCREASING,
    NONDECREASING,
    MONOTONIC,
    NONMONOTONIC,
    ANY
}

final class OrderTest<T> {
    List<T> testData;
    SequenceType sequenceType;

    public OrderTest(List<T> testData, SequenceType sequenceType) {
        this.testData = testData;
        this.sequenceType = sequenceType;
    }

    public static <T> OrderTest<T> of(List<T> testData, SequenceType sequenceType) {
        return new OrderTest<>(testData, sequenceType);
    }

    public List<T> getTestData() {
        return testData;
    }

    public SequenceType getSequenceType() {
        return sequenceType;
    }

    @Override
    public String toString() {
        return testData + " is expected to be " + sequenceType;
    }
}

@DisplayName("OrderDetector class for arrays, Lists and Iterables of Comparable items")
final class OrderDetectorTest {
    private static final List<OrderTest<Integer>> TESTS = Arrays.asList(
            OrderTest.of(Arrays.asList(), SequenceType.ANY),
            OrderTest.of(Arrays.asList(0), SequenceType.ANY),
            OrderTest.of(Arrays.asList(2), SequenceType.ANY),
            OrderTest.of(Arrays.asList(1, 2), SequenceType.INCREASING),
            OrderTest.of(Arrays.asList(3, 3), SequenceType.MONOTONIC),
            OrderTest.of(Arrays.asList(5, 1), SequenceType.DECREASING),
            OrderTest.of(Arrays.asList(1, 5), SequenceType.INCREASING),
            OrderTest.of(Arrays.asList(1, 1, 1), SequenceType.MONOTONIC),
            OrderTest.of(Arrays.asList(1, 6, 8), SequenceType.INCREASING),
            OrderTest.of(Arrays.asList(1, 2, 3, 4, 7), SequenceType.INCREASING),
            OrderTest.of(Arrays.asList(1, 2, 3, 4, 0), SequenceType.NONMONOTONIC),
            OrderTest.of(Arrays.asList(5, 1, 2, 3, 4), SequenceType.NONMONOTONIC),
            OrderTest.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), SequenceType.INCREASING),
            OrderTest.of(Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1), SequenceType.DECREASING),
            OrderTest.of(Arrays.asList(1, 2, 3, 4, 6, 7, 8, 9, 9), SequenceType.NONDECREASING),
            OrderTest.of(Arrays.asList(4, 3, 2, 1, -2, -4, -4, -4, -5), SequenceType.NONINCREASING),
            OrderTest.of(Arrays.asList(2, 3, 6, 1, 7, 8, 10, 25, 71, 90, 51), SequenceType.NONMONOTONIC)
    );

    @Nested
    @DisplayName("Order detection methods for arrays")
    class ArrayTests {
        private List<DynamicTest> checkOrder(Predicate<SequenceType> typeCondition, Function<Integer[], Boolean> result) {
            List<DynamicTest> tests = new ArrayList<>();
            for (OrderTest<Integer> test : TESTS) {
                tests.add(DynamicTest.dynamicTest(
                        String.valueOf(test),
                        () -> {
                            SequenceType t = test.getSequenceType();

                            Integer[] a = new Integer[test.getTestData().size()];
                            test.getTestData().toArray(a);

                            assertEquals(typeCondition.test(t) || t == SequenceType.ANY, result.apply(a));
                        }
                ));
            }
            return tests;
        }

        @TestFactory
        @DisplayName("Increasing sequences must be identified correctly")
        public List<DynamicTest> isIncreasing() {
            return checkOrder(t -> t == SequenceType.INCREASING, OrderDetector::isIncreasing);
        }

        @TestFactory
        @DisplayName("Decreasing sequences must be identified correctly")
        public List<DynamicTest> isDecreasing() {
            return checkOrder(t -> t == SequenceType.DECREASING, OrderDetector::isDecreasing);
        }

        @TestFactory
        @DisplayName("Non-increasing sequences include themselves, decreasing ones and same-element sequences")
        public List<DynamicTest> isNonIncreasing() {
            return checkOrder(t -> t == SequenceType.NONINCREASING || t == SequenceType.DECREASING || t == SequenceType.MONOTONIC, OrderDetector::isNonIncreasing);
        }

        @TestFactory
        @DisplayName("Non-decreasing sequences include themselves, increasing ones and same-element sequences")
        public List<DynamicTest> isNonDecreasing() {
            return checkOrder(t -> t == SequenceType.NONDECREASING || t == SequenceType.INCREASING || t == SequenceType.MONOTONIC, OrderDetector::isNonDecreasing);
        }

        @TestFactory
        @DisplayName("Monotonic sequences include (non-)increasing, (non-)decreasing and same-element sequences")
        public List<DynamicTest> isMonotonic() {
            return checkOrder(t -> t != SequenceType.NONMONOTONIC, OrderDetector::isMonotonic);
        }
    }

    @Nested
    @DisplayName("Order detection methods for Lists implementing RandomAccess")
    class ListTests {
        private List<DynamicTest> checkOrder(Predicate<SequenceType> typeCondition, Function<List<Integer>, Boolean> result) {
            List<DynamicTest> tests = new ArrayList<>();
            for (OrderTest<Integer> test : TESTS) {
                tests.add(DynamicTest.dynamicTest(
                        String.valueOf(test),
                        () -> {
                            SequenceType t = test.getSequenceType();
                            ArrayList<Integer> l = new ArrayList<>(test.getTestData());

                            assertEquals(typeCondition.test(t) || t == SequenceType.ANY, result.apply(l));
                        }
                ));
            }
            return tests;
        }

        @TestFactory
        @DisplayName("Increasing sequences must be identified correctly")
        public List<DynamicTest> isIncreasing() {
            return checkOrder(t -> t == SequenceType.INCREASING, OrderDetector::isIncreasing);
        }

        @TestFactory
        @DisplayName("Decreasing sequences must be identified correctly")
        public List<DynamicTest> isDecreasing() {
            return checkOrder(t -> t == SequenceType.DECREASING, OrderDetector::isDecreasing);
        }

        @TestFactory
        @DisplayName("Non-increasing sequences include themselves, decreasing ones and same-element sequences")
        public List<DynamicTest> isNonIncreasing() {
            return checkOrder(t -> t == SequenceType.NONINCREASING || t == SequenceType.DECREASING || t == SequenceType.MONOTONIC, OrderDetector::isNonIncreasing);
        }

        @TestFactory
        @DisplayName("Non-decreasing sequences include themselves, increasing ones and same-element sequences")
        public List<DynamicTest> isNonDecreasing() {
            return checkOrder(t -> t == SequenceType.NONDECREASING || t == SequenceType.INCREASING || t == SequenceType.MONOTONIC, OrderDetector::isNonDecreasing);
        }

        @TestFactory
        @DisplayName("Monotonic sequences include (non-)increasing, (non-)decreasing and same-element sequences")
        public List<DynamicTest> isMonotonic() {
            return checkOrder(t -> t != SequenceType.NONMONOTONIC, OrderDetector::isMonotonic);
        }
    }

    @Nested
    @DisplayName("Order detection methods for arbitrary iterables")
    class CollectionTests {
        private List<DynamicTest> checkOrder(Predicate<SequenceType> typeCondition, Function<Iterable<Integer>, Boolean> result) {
            List<DynamicTest> tests = new ArrayList<>();
            for (OrderTest<Integer> test : TESTS) {
                tests.add(DynamicTest.dynamicTest(
                        String.valueOf(test),
                        () -> {
                            SequenceType t = test.getSequenceType();
                            Iterable<Integer> l = test.getTestData();

                            assertEquals(typeCondition.test(t) || t == SequenceType.ANY, result.apply(l));
                        }
                ));
            }
            return tests;
        }

        @TestFactory
        @DisplayName("Increasing sequences must be identified correctly")
        public List<DynamicTest> isIncreasing() {
            return checkOrder(t -> t == SequenceType.INCREASING, OrderDetector::isIncreasing);
        }

        @TestFactory
        @DisplayName("Decreasing sequences must be identified correctly")
        public List<DynamicTest> isDecreasing() {
            return checkOrder(t -> t == SequenceType.DECREASING, OrderDetector::isDecreasing);
        }

        @TestFactory
        @DisplayName("Non-increasing sequences include themselves, decreasing ones and same-element sequences")
        public List<DynamicTest> isNonIncreasing() {
            return checkOrder(t -> t == SequenceType.NONINCREASING || t == SequenceType.DECREASING || t == SequenceType.MONOTONIC, OrderDetector::isNonIncreasing);
        }

        @TestFactory
        @DisplayName("Non-decreasing sequences include themselves, increasing ones and same-element sequences")
        public List<DynamicTest> isNonDecreasing() {
            return checkOrder(t -> t == SequenceType.NONDECREASING || t == SequenceType.INCREASING || t == SequenceType.MONOTONIC, OrderDetector::isNonDecreasing);
        }

        @TestFactory
        @DisplayName("Monotonic sequences include (non-)increasing, (non-)decreasing and same-element sequences")
        public List<DynamicTest> isMonotonic() {
            return checkOrder(t -> t != SequenceType.NONMONOTONIC, OrderDetector::isMonotonic);
        }
    }
}
package li.rid.study.algorithms.sorting;

import li.rid.study.algorithms.util.DataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

abstract class SortingAlgorithmTest {
    // Should be set by subclasses inside static block like this: { this.sorter = new BubbleSort(); }
    protected SortingAlgorithm sorter;

    @Test
    @DisplayName("Should sort empty arrays")
    void shouldSortEmptyArrays() {
        Integer[] given = {}, expected = {};
        sorter.sort(given);
        assertArrayEquals(expected, given);
    }

    @Test
    @DisplayName("Should sort one-element arrays")
    void shouldSortOneElementArrays() {
        for (int i = -10; i <= 10; i++) {
            Integer[] given = {i}, expected = {i};
            sorter.sort(given);
            assertArrayEquals(expected, given);
        }
    }

    @Test
    @DisplayName("Should sort two-element arrays (including arrays of same elements)")
    void shouldSortTwoElementArrays() {
        for (int i = -3; i <= 3; i++) {
            for (int j = -3; j <= 3; j++) {
                Integer[] given = {i, j}, expected = {Math.min(i, j), Math.max(i, j)};
                sorter.sort(given);
                assertArrayEquals(expected, given);
            }
        }
    }

    @Test
    @DisplayName("Should sort small and medium-sized same-element arrays")
    void shouldSortSameElementArrays() {
        for (int i = 2; i < 100; i++) {
            Integer[] given = new Integer[i], expected = new Integer[i];
            Arrays.fill(given, i);
            Arrays.fill(expected, i);
            sorter.sort(given);
            assertArrayEquals(expected, given);
        }
    }

    @Test
    @DisplayName("Should sort small and medium-sized ordered arrays")
    void shouldSortOrderedArrays() {
        for (int i = 2; i < 100; i++) {
            Integer[] given = new Integer[i], expected = new Integer[i];
            for (int j = 0; j < i; j++) {
                given[j] = j;
                expected[j] = j;
            }
            sorter.sort(given);
            assertArrayEquals(expected, given);
        }
    }

    @Test
    @DisplayName("Should sort small and medium-sized reverse-ordered arrays")
    void shouldSortReverseOrderedArrays() {
        for (int i = 2; i < 100; i++) {
            Integer[] given = new Integer[i], expected = new Integer[i];
            for (int j = 0; j < i; j++) {
                given[j] = expected.length - j - 1;
                expected[j] = j;
            }
            sorter.sort(given);
            assertArrayEquals(expected, given);
        }
    }

    @Test
    @DisplayName("Should sort small and medium-sized random arrays (generated sequentially)")
    void shouldSortRandomArrays() {
        for (int i = 2; i < 200; i++) {
            Integer[] expected = DataGenerator.randomIntegersArray(i, -100, 100);
            Integer[] given = expected.clone();
            Arrays.sort(expected);
            sorter.sort(given);
            assertArrayEquals(expected, given);
        }
    }

    @Test
    @DisplayName("Should sort small and medium-sized random arrays (generated with random length)")
    void shouldSortRandomLengthArrays() {
        for (int i = 2; i < 100; i++) {
            int length = ThreadLocalRandom.current().nextInt(0, 3000);
            Integer[] expected = DataGenerator.randomIntegersArray(length, -1000, 1000);
            Integer[] given = expected.clone();
            Arrays.sort(expected);
            sorter.sort(given);
            assertArrayEquals(expected, given);
        }
    }

    @Test
    @DisplayName("Should sort small and medium-sized random ordered arrays with a few elements swapped")
    void shouldSortRandomArraysWithFewSwappedElements() {
        for (int i = 0; i < 200; i++) {
            int length = ThreadLocalRandom.current().nextInt(2, 10);
            Integer[] expected = DataGenerator.randomOrderedArray(length, -400, 400);
            Integer[] given = expected.clone();
            int swapCount = ThreadLocalRandom.current().nextInt(1, 20);
            for (int j = 0; j < swapCount; j++) {
                // swapPos1 may be equal to swapPos2! If undesired, use do { ... } while (swapPos1 == swapPos2);
                int swapPos1 = ThreadLocalRandom.current().nextInt(0, given.length);
                int swapPos2 = ThreadLocalRandom.current().nextInt(0, given.length);
                Integer tmp = given[swapPos1];
                given[swapPos1] = given[swapPos2];
                given[swapPos2] = tmp;
            }
            sorter.sort(given);
            assertArrayEquals(expected, given);
        }
    }

}

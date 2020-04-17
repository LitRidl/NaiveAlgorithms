package li.rid.study.algorithms.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DataGenerator class methods")
class DataGeneratorTest {
    @Test
    @DisplayName("randomIntegersArray() should create an array of specified length")
    void randomIntegersArrayLengthShouldBeCorrect() {
        for (int length = 0; length < 100; length++) {
            assertEquals(DataGenerator.randomIntegersArray(length, 0, 100).length, length);
        }
    }

    @Test
    @DisplayName("randomIntegersArray() should create an array with numbers in specified range")
    void randomIntegersArrayNumbersShouldBeInRange() {
        for (int length = 0; length < 100; length++) {
            Integer min = 0, max = length / 2;
            Integer[] a = DataGenerator.randomIntegersArray(length, min, max + 1);
            for (Integer el : a) {
                assertTrue(el >= min && el <= max);
            }
        }
    }

    @Test
    @DisplayName("randomOrderedArray() with non-negative step should produce non-decreasing arrays")
    void randomOrderedArrayWithNonNegativeStepProducesNonDecreasingArray() {
        for (int length = 0; length < 100; length++) {
            for (int step = 0; step < 20; step++) {
                assertTrue(OrderDetector.isNonDecreasing(DataGenerator.randomOrderedArray(length, 0, step)));
            }
        }
    }

    @Test
    @DisplayName("randomOrderedArray() with negative step should produce non-increasing arrays")
    void randomOrderedArrayWithNegativeStepProducesNonIncreasingArray() {
        for (int length = 0; length < 100; length++) {
            for (int step = 0; step >= -20; step--) {
                assertTrue(OrderDetector.isNonIncreasing(DataGenerator.randomOrderedArray(length, 0, step)));
            }
        }
    }

    @Test
    @DisplayName("randomOrderedArray() should produce arrays with numbers conforming to step range")
    void randomOrderedArrayStepIsInRange() {
        for (int length = 0; length < 100; length++) {
            for (int step = 0; step < 20; step++) {
                Integer[] a = DataGenerator.randomOrderedArray(length, 0, step);
                for (int i = 0; i < a.length - 1; i++) {
                    assertTrue(a[i + 1] >= a[i] && a[i + 1] - a[i] <= step);
                }
            }
        }
    }

    @Test
    @DisplayName("shuffle() should work on empty arrays")
    void shuffleShouldWorkOnEmptyArray() {
        Integer[] given = {}, expected = {};
        DataGenerator.shuffle(given);
        assertArrayEquals(expected, given);
    }

    @Test
    @DisplayName("shuffle() should work on one-element arrays")
    void shuffleShouldWorkOnOneElementArray() {
        for (int i = -5; i < 5; i++) {
            Integer[] given = {i}, expected = {i};
            DataGenerator.shuffle(given);
            assertArrayEquals(expected, given);
        }
    }

    @Test
    @DisplayName("shuffle() should preserve all elements of the original array and their count (only order changes)")
    void shuffleShouldPreserveAllElements() {
        for (int i = 1; i < 100; i++) {
            Integer[] given = DataGenerator.randomIntegersArray(i, -100, 100);
            Integer[] expected = given.clone();
            DataGenerator.shuffle(given);
            Arrays.sort(given);
            Arrays.sort(expected);
            assertArrayEquals(expected, given);
        }
    }
}
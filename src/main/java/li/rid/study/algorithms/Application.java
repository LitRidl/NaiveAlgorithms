package li.rid.study.algorithms;

import li.rid.study.algorithms.sorting.CombSort;
import li.rid.study.algorithms.sorting.SortingAlgorithm;
import li.rid.study.algorithms.util.DataGenerator;
import li.rid.study.algorithms.util.OrderDetector;
import li.rid.study.algorithms.util.Range;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Application {
    private <T extends Comparable<T>> void testOnPredefinedTests(SortingAlgorithm sorter, T[][] tests) {
        System.out.println("Testing on predefined array tests...");
        for (T[] test : tests) {
            sorter.sort(test);
            String status = OrderDetector.isNonDecreasing(test) ? "✓" : "✗";
            System.out.println(status + " " + Arrays.toString(test));
        }
    }

    private void testOnRandomTests(SortingAlgorithm sorter, int testsCount, Range<Integer> arrayLength, Range<Integer> arrayValues) {
        System.out.println("Testing on randomly generated array tests... Please wait...");
        int testsPassed = 0;
        for (int i = 0; i < testsCount; i++) {
            int length = ThreadLocalRandom.current().nextInt(arrayLength.getMin(), arrayLength.getMax());
            Integer[] test = DataGenerator.randomIntegersArray(length, arrayValues.getMin(), arrayValues.getMax());
            sorter.sort(test);
            if (OrderDetector.isNonDecreasing(test)) {
                testsPassed++;
            }
        }
        String status = (testsPassed == testsCount) ? "✓" : "✗";
        String message = testsPassed + " of " + testsCount + " tests passed, " + (testsCount - testsPassed) + " failed";
        System.out.println(status + " " + message);
    }

    private void launch() {
        Integer[][] predefinedTests = {
                {},
                {0},
                {2},
                {1, 2},
                {3, 3},
                {5, 1},
                {1, 6, 8},
                {1, 2, 3, 4, 0},
                {5, 1, 2, 3, 4},
                {1, 2, 4, 2, 1, 5, 2},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {9, 8, 7, 6, 5, 4, 3, 2, 1},
                {1, 2, 3, 4, 6, 7, 5, 8, 9},
                {4, 3, 2, 1, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 10, 9, 8, 7, 6, 5},
                {2, 3, 6, 1, 7, 8, 10, 25, 71, 90, 51}
        };

        SortingAlgorithm sorter = new CombSort();

        // SelectionSort sorter = new SelectionSort();
        // LinkedList<Integer> sorted = sorter.sort(new LinkedList<Integer>(Arrays.asList(a)));
        // sorted.toArray(a);

        testOnPredefinedTests(sorter, predefinedTests);
        System.out.println();

        long startTime = System.currentTimeMillis();
        testOnRandomTests(sorter, 10_000, Range.of(0, 1000), Range.of(-2000, 2000));
        long timeElapsed = System.currentTimeMillis() - startTime;
        System.out.println("Random tests execution time: " + timeElapsed + "ms");
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.launch();
    }
}

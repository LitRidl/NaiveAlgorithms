package li.rid.study.algorithms.sorting;

import org.junit.jupiter.api.DisplayName;

@DisplayName("Java's built-in sort")
final class JavaNativeSortTest extends SortingAlgorithmTest {
    {
        this.sorter = new JavaNativeSort();
    }
}
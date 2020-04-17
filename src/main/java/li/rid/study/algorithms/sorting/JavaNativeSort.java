package li.rid.study.algorithms.sorting;

import java.util.Arrays;

public final class JavaNativeSort implements SortingAlgorithm {
    @Override
    public <T extends Comparable<? super T>> void sort(T[] a) {
        Arrays.sort(a);
    }
}

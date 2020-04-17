package li.rid.study.algorithms.sorting;

public interface SortingAlgorithm {
    <T extends Comparable<? super T>> void sort(T[] a);
}

package li.rid.study.algorithms.sorting;

public final class BubbleSort implements SortingAlgorithm {
    public <T extends Comparable<? super T>> void sort(T[] a) {
        for (int lastUnsortedPosition = a.length - 1; lastUnsortedPosition > 0; ) {
            int newPosition = 0;
            for (int i = 0; i < lastUnsortedPosition; i++) {
                if (a[i].compareTo(a[i + 1]) > 0) {
                    T tmp = a[i + 1];
                    a[i + 1] = a[i];
                    a[i] = tmp;
                    newPosition = i + 1;
                }
            }
            lastUnsortedPosition = newPosition;
        }
    }
}

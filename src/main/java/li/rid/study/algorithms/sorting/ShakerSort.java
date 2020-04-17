package li.rid.study.algorithms.sorting;

public final class ShakerSort implements SortingAlgorithm {
    public <T extends Comparable<? super T>> void sort(T[] a) {
        for (int lastUnsortedR = a.length - 1, lastUnsortedL = 0; lastUnsortedL < lastUnsortedR; ) {
            int newLastUnsortedR = 0;
            for (int j = lastUnsortedL; j < lastUnsortedR; j++) {
                if (a[j + 1].compareTo(a[j]) < 0) {
                    T tmp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = tmp;
                    newLastUnsortedR = j + 1;
                }
            }
            lastUnsortedR = newLastUnsortedR;

            int newLastUnsortedL = a.length - 1;
            for (int j = lastUnsortedR - 1; j >= lastUnsortedL; j--) {
                if (a[j].compareTo(a[j + 1]) > 0) {
                    T tmp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = tmp;
                    newLastUnsortedL = j + 1;
                }
            }
            lastUnsortedL = newLastUnsortedL;
        }
    }
}

package li.rid.study.algorithms.sorting;

public final class CombSort implements SortingAlgorithm {
    public <T extends Comparable<? super T>> void sort(T[] a) {
        int gap = a.length;
        boolean swapped = true;
        while (gap > 1 || swapped) {
            if (gap > 1) {
                gap = (int) (gap / 1.3);
            }

            swapped = false;
            for (int i = 0; i + gap < a.length; i++) {
                if (a[i].compareTo(a[i + gap]) > 0) {
                    T tmp = a[i];
                    a[i] = a[i + gap];
                    a[i + gap] = tmp;
                    swapped = true;
                }
            }
        }
    }
}

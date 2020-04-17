package li.rid.study.algorithms.sorting;

import java.util.Collections;
import java.util.LinkedList;

public final class SelectionSort implements SortingAlgorithm {
    @Override
    public <T extends Comparable<? super T>> void sort(T[] a) {
        for (int firstUnsortedPos = 0; firstUnsortedPos < a.length - 1; firstUnsortedPos++) {
            int minElementPos = firstUnsortedPos;
            for (int i = firstUnsortedPos + 1; i < a.length; i++) {
                if (a[i].compareTo(a[minElementPos]) < 0) {
                    minElementPos = i;
                }
            }
            T tmp = a[firstUnsortedPos];
            a[firstUnsortedPos] = a[minElementPos];
            a[minElementPos] = tmp;
        }
    }

    public <T extends Comparable<? super T>> LinkedList<T> sort(LinkedList<T> oldList) {
        LinkedList<T> sortedList = new LinkedList<>();
        while (!oldList.isEmpty()) {
            T minElement = Collections.min(oldList);
            sortedList.add(minElement);
            oldList.removeFirstOccurrence(minElement);
        }
        return sortedList;
    }
}

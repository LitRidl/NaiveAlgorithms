package li.rid.study.algorithms;

import java.util.Arrays;

final public class SortingAlgorithms {
    private SortingAlgorithms() {
        throw new AssertionError("Instantiating utility class is prohibited. Use it's static methods");
    }

    public static <T extends Comparable<T>> void javaNativeSort(T[] a) {
        Arrays.sort(a);
    }

    public static <T extends Comparable<T>> void selectionSort(T[] a) {
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

    public static <T extends Comparable<T>> void bubbleSort(T[] a) {

    }
}

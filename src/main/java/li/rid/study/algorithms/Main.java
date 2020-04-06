package li.rid.study.algorithms;

import java.util.Arrays;

public class Main {
    private void launch() {
        Integer[] a = {2, 3, 6, 1, 7, 8, 10, 25, 71, 90, 51};

        System.out.println("Original array: ");
        System.out.println(Arrays.toString(a));

        SortingAlgorithms.javaNativeSort(a);

        System.out.println("Sorted array: ");
        System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        Main applicationLauncher = new Main();
        applicationLauncher.launch();
    }
}

package li.rid.study.algorithms.sorting;

import javax.lang.model.SourceVersion;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SortingAlgorithmFactory {
    final static private Map<String, SortingAlgorithm> ALGORITHMS = new HashMap<String, SortingAlgorithm>();

    static {
        List<SortingAlgorithm> sortingAlgorithms = new ArrayList<>(Arrays.asList(
                new JavaNativeSort(),
                new SelectionSort()
        ));
        for (SortingAlgorithm algorithm : sortingAlgorithms) {
            ALGORITHMS.put(algorithm.getClass().getSimpleName(), algorithm);
        }
    }

    public SortingAlgorithm byAlgorithmName(String name) {
        return ALGORITHMS.get(name);
    }

    private static int[] randomOrderedArray(int arraySize, int minNumber, int maxStep) {
        int[] a = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(minNumber, minNumber + maxStep);
            minNumber = a[i];
        }
        return a;
    }

    public SortingAlgorithm byClassName(String name) {
        if (!SourceVersion.isIdentifier(name) || SourceVersion.isKeyword(name)) {
            return null;
        }
        String packageName = SortingAlgorithm.class.getPackage().getName();
        try {
            return (SortingAlgorithm) Class.forName(packageName + name).getConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}

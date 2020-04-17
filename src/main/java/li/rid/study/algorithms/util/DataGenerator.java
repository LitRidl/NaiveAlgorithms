package li.rid.study.algorithms.util;

import java.util.concurrent.ThreadLocalRandom;

public final class DataGenerator {
    private DataGenerator() {
        throw new AssertionError();
    }

    public static Integer[] randomIntegersArray(int length, Integer minValue, Integer maxValue) {
        Integer[] a = new Integer[length];
        for (int i = 0; i < a.length; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(minValue, maxValue);
        }
        return a;
    }

    public static Integer[] randomOrderedArray(int length, Integer startValue, int step) {
        Integer[] a = new Integer[length];
        for (int i = 0; i < a.length; i++) {
            if (step >= 0) {
                a[i] = ThreadLocalRandom.current().nextInt(startValue, startValue + step + 1);
            } else {
                a[i] = ThreadLocalRandom.current().nextInt(startValue + step, startValue + 1);
            }
            startValue = a[i];
        }
        return a;
    }

    public static <T> void shuffle(T[] a) {
        for (int i = a.length - 1; i >= 0; i--) {
            int j = ThreadLocalRandom.current().nextInt(i + 1);

            T tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }
}

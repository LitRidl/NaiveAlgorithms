package li.rid.study.algorithms;

import com.google.common.collect.Comparators;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SortingAlgorithmsTest {

    @DisplayName("Java's native Arrays.sort() should sort an array")
    @Test
    void javaNativeSort() {
        final int min = 0, max = 100;

        List<Integer> integersRange = IntStream.range(min, max + 1).boxed().collect(Collectors.toList());
        List<Integer> randomIntegersList = new ArrayList<>(integersRange);
        Collections.shuffle(randomIntegersList);

        Integer[] array = new Integer[randomIntegersList.size()];
        randomIntegersList.toArray(array);

        SortingAlgorithms.javaNativeSort(array);

        assertArrayEquals(integersRange.toArray(), array);
    }
}

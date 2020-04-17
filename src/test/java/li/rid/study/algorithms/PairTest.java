package li.rid.study.algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

final class PairTest {
    private List<Pair<Integer>> pairs;

    @BeforeEach
    public void preparePairs() {
        pairs = Arrays.asList(Pair.of(0, 0), Pair.of(1, 2), Pair.of(1, 3), Pair.of(2, 2), Pair.of(1, 2));
    }

    @DisplayName("Pairs are equal if their corresponding elements are equal")
    @Test
    void testEquals() {
        assertEquals(pairs.get(1), pairs.get(1));
        assertEquals(pairs.get(1), pairs.get(4));
        assertNotEquals(pairs.get(0), pairs.get(3));
        assertNotEquals(pairs.get(1), pairs.get(2));
    }

    @DisplayName("Pairs are ordered as follows: first Pair.first values are compared, then Pair.second")
    @Test
    void compareTo() {
        assertEquals(pairs.get(1).compareTo(pairs.get(4)), 0);
        assertTrue(pairs.get(0).compareTo(pairs.get(1)) < 0);
        assertTrue(pairs.get(1).compareTo(pairs.get(0)) > 0);
        assertTrue(pairs.get(1).compareTo(pairs.get(3)) < 0);
    }
}
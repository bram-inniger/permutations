package be.inniger.permutation;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PermutationsTest {

    private static final Logger LOG = LoggerFactory.getLogger(PermutationsTest.class);

    @Test
    void cannotPermuteOverZeroElements() {
        Iterator<List<Object>> it = Permutations.asIterator(List.of());

        assertAll(
                () -> assertTrue(it.hasNext()),
                () -> assertEquals(List.of(), it.next()),
                () -> assertFalse(it.hasNext()));
    }

    @Test
    void canPermuteOverOneElements() {
        Iterator<List<Integer>> it = Permutations.asIterator(List.of(42));

        assertAll(
                () -> assertTrue(it.hasNext()),
                () -> assertEquals(List.of(42), it.next()),
                () -> assertFalse(it.hasNext()));
    }

    @Test
    void canPermuteOverTwoElements() {
        Iterator<List<String>> it = Permutations.asIterator(List.of("foo", "bar"));

        assertAll(
                () -> assertTrue(it.hasNext()),
                () -> assertEquals(List.of("foo", "bar"), it.next()),
                () -> assertTrue(it.hasNext()),
                () -> assertEquals(List.of("bar", "foo"), it.next()),
                () -> assertFalse(it.hasNext()));
    }

    @Test
    void canPermuteOverThreeElements() {
        Iterator<List<Integer>> it = Permutations.asIterator(List.of(1, 2, 3));

        assertAll(
                () -> assertTrue(it.hasNext()),
                () -> assertEquals(List.of(1, 2, 3), it.next()),
                () -> assertTrue(it.hasNext()),
                () -> assertEquals(List.of(2, 1, 3), it.next()),
                () -> assertTrue(it.hasNext()),
                () -> assertEquals(List.of(3, 1, 2), it.next()),
                () -> assertTrue(it.hasNext()),
                () -> assertEquals(List.of(1, 3, 2), it.next()),
                () -> assertTrue(it.hasNext()),
                () -> assertEquals(List.of(2, 3, 1), it.next()),
                () -> assertTrue(it.hasNext()),
                () -> assertEquals(List.of(3, 2, 1), it.next()),
                () -> assertFalse(it.hasNext()));
    }

    @Test
    void canPermuteOverSets() {
        Iterator<List<String>> it = Permutations.asIterator(Set.of("bar", "baz"));

        var permutations = new ArrayList<>();
        while (it.hasNext()) {
            var permutation = it.next();
            LOG.debug("Adding permutation: {}", permutation);
            permutations.add(permutation);
        }

        assertAll(
                () -> assertEquals(2, permutations.size()),
                () -> assertTrue(permutations.contains(List.of("baz", "bar"))),
                () -> assertTrue(permutations.contains(List.of("bar", "baz"))));
    }

    @Test
    void throwsOnRemove() {
        Iterator<List<Integer>> it = Permutations.asIterator(List.of(1, 2, 3));

        assertAll(
                () -> assertThrows(UnsupportedOperationException.class, it::remove));
    }
}
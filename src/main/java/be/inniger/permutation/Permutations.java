package be.inniger.permutation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Given a collection of elements E, generate all its permutations iteratively
 *
 * Based on See <a href="https://www.quickperm.org/">QuickPerm</a>
 *
 *    The Countdown QuickPerm Algorithm:
 *
 *    let a[] represent an arbitrary list of objects to permute
 *    let N equal the length of a[]
 *    create an integer array p[] of size N+1 to control the iteration
 *    initialize p[0] to 0, p[1] to 1, p[2] to 2, ..., p[N] to N
 *    initialize index variable i to 1
 *    while (i < N) do {
 *       decrement p[i] by 1
 *       if i is odd, then let j = p[i] otherwise let j = 0
 *       swap(a[j], a[i])
 *       let i = 1
 *       while (p[i] is equal to 0) do {
 *          let p[i] = i
 *          increment i by 1
 *       } // end while (p[i] is equal to 0)
 *    } // end while (i < N)
 *
 * @param <E> Type of element
 */
public class Permutations<E> implements Iterator<List<E>> {

    private final List<E> a;
    private final int n;
    private final int[] p;

    private int i = 1;
    private boolean first = true;

    private Permutations(Collection<E> elements) {
        this.a = new ArrayList<>(elements);
        this.n = a.size();
        this.p = IntStream.rangeClosed(0, n).toArray();
    }

    public static <E> Iterator<List<E>> asIterator(Collection<E> elements) {
        return new Permutations<>(elements);
    }

    @Override
    public boolean hasNext() {
        // FIXME, get rid of the `first` hack
        return first || i < n;
    }

    @Override
    public List<E> next() {
        // FIXME, get rid of the `first` hack
        if (first) {
            first = false;
            return List.copyOf(a);
        }

        p[i]--;
        int j = i % 2 == 1 ?
                p[i] :
                0;

        swap(i, j);

        i = 1;
        while (p[i] == 0) {
            p[i] = i;
            i++;
        }

        return List.copyOf(a);
    }

    private void swap(int i, int j) {
        E tmp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, tmp);
    }
}

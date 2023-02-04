package mikhailmineev.graph.strategy;

import java.util.Deque;
import java.util.NavigableSet;
import java.util.stream.Stream;

public interface Pollable<T> {

    /**
     * Adds element to the beginning of collection if that is supported and collection is not sorted.
     * Otherwise, adds it into the end.
     *
     * @see java.util.Deque#push(Object)
     * @see java.util.NavigableSet#add(Object)
     * @param entry entry to be added
     */
    void push(T entry);

    /**
     * Adds element to the end of collection if that is supported and collection is not sorted.
     *
     * @see java.util.Deque#add(Object)
     * @see java.util.NavigableSet#add(Object)
     * @param entry entry to be added
     */
    void add(T entry);

    /**
     * Get element from the start of the collection and remove it.
     *
     * @see java.util.Deque#poll() (Object)
     * @see java.util.NavigableSet#pollFirst()
     * @return first element in the collection
     */
    T first();

    /**
     * Create stream of the collection.
     *
     * @return stream of the collection
     */
    Stream<T> stream();

    /**
     * Delete an element from the collection.
     *
     * @param entry entry to be removed
     */
    void remove(T entry);

    static <T> Pollable<T> of(NavigableSet<T> delegate) {
        return new PollableNavigableSet<>(delegate);
    }

    static <T> Pollable<T> of(Deque<T> delegate) {
        return new PollableDeque<>(delegate);
    }

}

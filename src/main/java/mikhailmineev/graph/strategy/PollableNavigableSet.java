package mikhailmineev.graph.strategy;

import java.util.NavigableSet;
import java.util.stream.Stream;

public record PollableNavigableSet<T>(NavigableSet<T> delegate) implements Pollable<T> {

    @Override
    public void push(T entry) {
        delegate.add(entry);
    }

    @Override
    public void add(T entry) {
        delegate.add(entry);
    }

    @Override
    public T first() {
        return delegate.pollFirst();
    }

    @Override
    public Stream<T> stream() {
        return delegate.stream();
    }

    @Override
    public void remove(T entry) {
        delegate.remove(entry);
    }
}

package mikhailmineev.graph.strategy;

import java.util.Deque;
import java.util.stream.Stream;

public record PollableDeque<T>(Deque<T> delegate) implements Pollable<T> {

    @Override
    public void push(T entry) {
        delegate.push(entry);
    }

    @Override
    public void add(T entry) {
        delegate.add(entry);
    }

    @Override
    public T first() {
        return delegate.poll();
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

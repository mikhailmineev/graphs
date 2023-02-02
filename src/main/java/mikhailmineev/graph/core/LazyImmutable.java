package mikhailmineev.graph.core;

import java.util.function.Function;

public class LazyImmutable<T> {

    private Function<T, T> delegateWrapper;
    private volatile T delegate;

    private final Object lock = new Object();

    public LazyImmutable(Function<T, T> delegateWrapper) {
        this.delegateWrapper = delegateWrapper;
    }

    public void setDelegate(T delegate) {
        if (this.delegate != null) {
            throw new IllegalStateException("Lazy delegate already initialized");
        }
        synchronized (lock) {
            if (this.delegate != null) {
                throw new IllegalStateException("Lazy delegate already initialized");
            }
            if (delegateWrapper != null) {
                this.delegate = delegateWrapper.apply(delegate);
                delegateWrapper = null;
            } else {
                this.delegate = delegate;
            }
        }
    }

    public T getDelegate() {
        if (delegate != null) {
            return delegate;
        }
        synchronized (lock) {
            if (delegate != null) {
                return delegate;
            } else {
                throw new IllegalStateException("Lazy delegate not yet initialized");
            }
        }
    }

    @Override
    public String toString() {
        return delegate == null ? "Not yet instantiated" : delegate.toString();
    }

}

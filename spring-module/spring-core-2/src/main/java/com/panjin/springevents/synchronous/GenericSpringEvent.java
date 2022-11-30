package com.panjin.springevents.synchronous;

/**
 * 泛型支持
 * @author panjin
 */
public class GenericSpringEvent<T> {

    private final T what;
    protected final boolean success;

    public GenericSpringEvent(final T what, final boolean success) {
        this.what = what;
        this.success = success;
    }

    public T getWhat() {
        return what;
    }

    public boolean isSuccess() {
        return success;
    }

}

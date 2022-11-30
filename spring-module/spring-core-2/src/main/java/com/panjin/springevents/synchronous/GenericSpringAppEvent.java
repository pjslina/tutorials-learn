package com.panjin.springevents.synchronous;

import org.springframework.context.ApplicationEvent;

/**
 * @author panjin
 */
public class GenericSpringAppEvent<T> extends ApplicationEvent {

    private final T what;

    public GenericSpringAppEvent(final Object source, final T what) {
        super(source);
        this.what = what;
    }

    public T getWhat() {
        return what;
    }

}

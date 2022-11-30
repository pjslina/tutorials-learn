package com.panjin.springevents.synchronous;

/**
 * @author panjin
 */
public class GenericStringSpringEvent extends GenericSpringEvent<String> {

    GenericStringSpringEvent(final String what, final boolean success) {
        super(what, success);
    }

}

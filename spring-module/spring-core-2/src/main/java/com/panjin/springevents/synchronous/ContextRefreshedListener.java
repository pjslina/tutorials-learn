package com.panjin.springevents.synchronous;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author panjin
 */
@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    // for tests
    private boolean hitContextRefreshedHandler = false;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent cse) {
        System.out.println("Handling context re-freshed event. ");
        hitContextRefreshedHandler = true;
    }

    boolean isHitContextRefreshedHandler() {
        return hitContextRefreshedHandler;
    }
}
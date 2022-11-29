package com.panjin;

import org.springframework.stereotype.Component;

/**
 * @author panjin
 */
@Component
public class Service {

    @LogExecutionTime
    public void serve() throws InterruptedException {
        Thread.sleep(2000);
    }
}

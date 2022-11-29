package com.panjin.order;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * The default value is Ordered.LOWEST_PRECEDENCE
 * @author panjin
 */
@Component
@Order()
public class Average implements Rating {

    @Override
    public int getRating() {
        return 3;
    }
}

package com.panjin.mq.client;

import com.panjin.mq.annotation.MqListener;
import org.springframework.stereotype.Service;

/**
 * @author panjin
 */
@Service
public class PersonService {

    @MqListener(topic = "person")
    public String getPerson() {
        return "this is test my person";
    }
}

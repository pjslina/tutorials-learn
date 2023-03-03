package com.panjin.spring.cloud.stream.rabbit.model;

import java.io.Serializable;

/**
 * @author panjin
 */
public class LogMessage implements Serializable {

    private static final long serialVersionUID = 7539541973053310896L;
    private String message;

    public LogMessage() {

    }

    public LogMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}

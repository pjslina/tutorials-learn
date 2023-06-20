package com.panjin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @author panjin
 */
@RestController
public class I18nController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/message")
    public String showMessage() {
        return messageSource.getMessage("a.b.c", new Object[]{"joke", "kroen"}, Locale.getDefault());
    }
}

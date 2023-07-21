package com.panjin.jpamysqldm.user;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * @author panjin
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public UserDO save(@RequestBody UserDO userDO) {
        userDO.setCreatedTime(LocalDateTime.now());
        userDO.setUpdatedTime(ZonedDateTime.now());
        return userService.save(userDO);
    }

    @GetMapping("/user/{id}")
    public UserDO findById(@PathVariable Long id) {
        return userService.findById(id);
    }
}

package com.panjin.jpamysqldm.user;

import org.springframework.stereotype.Service;

/**
 * @author panjin
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDO save(UserDO userDO) {
        return userRepository.save(userDO);
    }

    public UserDO findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}

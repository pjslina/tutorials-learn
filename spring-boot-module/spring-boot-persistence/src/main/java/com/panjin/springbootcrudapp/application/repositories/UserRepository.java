package com.panjin.springbootcrudapp.application.repositories;

import com.panjin.springbootcrudapp.application.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author panjin
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    List<User> findByName(String name);
    
}

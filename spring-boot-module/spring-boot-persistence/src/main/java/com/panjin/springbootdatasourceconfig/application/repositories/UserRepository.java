package com.panjin.springbootdatasourceconfig.application.repositories;

import com.panjin.springbootdatasourceconfig.application.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author panjin
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
